// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.app.logic.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import com.tenpay.client.ClientResponseHandler;
import com.tenpay.client.TenpayHttpClient;
import com.tenpay.util.MD5Util;
import com.youthen.framework.common.StringUtils;
import com.youthen.framework.common.annotation.BusinessLogic;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.framework.common.exception.ObjectNotFoundException;
import com.youthen.framework.util.BeanUtils;
import com.youthen.lj.app.bean.AppActionUser;
import com.youthen.lj.app.bean.AppActive;
import com.youthen.lj.app.bean.AppBuilding;
import com.youthen.lj.app.bean.AppKbn;
import com.youthen.lj.app.bean.AppLoginUser;
import com.youthen.lj.app.bean.AppMerchant;
import com.youthen.lj.app.bean.NewMessage;
import com.youthen.lj.app.bean.Result;
import com.youthen.lj.app.bean.RoomInfo;
import com.youthen.lj.app.bean.ScoreHistory;
import com.youthen.lj.app.logic.LjAppLogic;
import com.youthen.lj.constant.AppConfig;
import com.youthen.lj.service.LjActionCommentService;
import com.youthen.lj.service.LjActionUserService;
import com.youthen.lj.service.LjFeeHistoryService;
import com.youthen.lj.service.LjMerchantService;
import com.youthen.lj.service.LjMsgTipService;
import com.youthen.lj.service.LjNoticeService;
import com.youthen.lj.service.LjRepairCplnService;
import com.youthen.lj.service.LjRoomInfoService;
import com.youthen.lj.service.LjScoreHistoryService;
import com.youthen.lj.service.LjUserBuildingService;
import com.youthen.lj.service.dto.LjActionUserDto;
import com.youthen.lj.service.dto.LjFeeHistoryDto;
import com.youthen.lj.service.dto.LjMerchantDto;
import com.youthen.lj.service.dto.LjNoticeDto;
import com.youthen.lj.service.dto.LjRepairCplnDto;
import com.youthen.lj.utils.JsonUtils;
import com.youthen.lj.utils.LjHttpSoap;
import com.youthen.master.common.BusinessCheckException;
import com.youthen.master.persistence.dao.LoginUserDao;
import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.master.service.KbnService;
import com.youthen.master.service.LoginUserService;
import com.youthen.master.service.dto.KbnDto;
import com.youthen.master.service.dto.LoginUserDto;

/**
 * 理家物业APP调用接口服务类。
 * 
 * @author Administrator
 * @author Modifier By LiXin
 * @version $Revision: $<br>
 *          $Date: $
 */
@BusinessLogic("ljAppLogic")
public class LjAppLogicImpl implements LjAppLogic {

    private static Logger logger = Logger.getLogger(LjAppLogicImpl.class.getName());

    @Autowired
    private LoginUserService userService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Autowired
    LjNoticeService ljNoticeService;

    @Autowired
    LjRepairCplnService ljRepairCplnService;

    @Autowired
    LjActionCommentService ljActionCommentService;

    @Autowired
    private LoginUserDao loginUserDao;

    @Autowired
    LjUserBuildingService ljUserBuildingService;

    @Autowired
    LjFeeHistoryService ljFeeHistoryService;

    @Autowired
    LjMsgTipService ljMsgTipService;

    @Autowired
    LjScoreHistoryService ljScoreHistoryService;

    @Autowired
    LjRoomInfoService ljRoomInfoService;

    @Autowired
    KbnService kbnService;

    @Autowired
    LjMerchantService ljMerchantService;

    @Autowired
    LjActionUserService ljActionUserService;

    JsonConfig jsonConfig = new JsonConfig();

    public final int SCORE_REG = 100;
    public final int SCORE_LOGIN = 10;
    public final int SCORE_SIGN = 10;

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#reg(java.lang.String, java.lang.String, java.lang.String)
     */

    @Override
    public AppLoginUser reg(final String mobile, final String pwd1, final String aPwd2) {
        LoginUserDto loginUserDto = this.userService.getUserByMobileNum(mobile);

        if (loginUserDto == null) {

            final LoginUser loginUser = new LoginUser();
            loginUserDto = new LoginUserDto();
            loginUserDto.setUserId(mobile);
            loginUserDto.setMobile(mobile);
            loginUserDto.setName("");
            loginUserDto.setDepartmentId(1L);
            loginUserDto.setCompanyId(1L);
            loginUserDto.setPassword(this.passwordEncoder.encodePassword(pwd1, null));

            // 新增用户
            final List<LoginUserDto> users = new ArrayList<LoginUserDto>();
            final LoginUserDto aUser = new LoginUserDto();
            aUser.setUserId(mobile);
            aUser.setDepartmentId(1L);
            aUser.setCompanyId(1L);
            final Long[] sysIds = {2L};
            aUser.setSystemIds(sysIds);// LJ APP系统

            final Long[] roleIds = {1L};
            aUser.setRoleIds(roleIds);// LJ APP 系统用户

            users.add(aUser);
            try {
                this.userService.addUser(loginUserDto, users);
            } catch (final BusinessCheckException e) {
                e.printStackTrace();
            } catch (final DuplicateKeyException e) {
                e.printStackTrace();
            } catch (final ObjectNotFoundException e) {
                e.printStackTrace();
            }

            final AppLoginUser appLoginUser = new AppLoginUser();
            appLoginUser.setMobile(mobile);
            appLoginUser.setScore(this.SCORE_REG);

            return appLoginUser;
        }
        return null;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#login(java.lang.String, java.lang.String)
     */

    @Override
    public AppLoginUser login(final String aMobile, final String aPwd) {
        return null;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#index()
     */

    @Override
    public List<AppActive> index() {
        return null;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#logout(java.lang.String)
     */

    @Override
    public void logout(final String aMobile) {
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#forgotPwd(java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String)
     */

    @Override
    public void forgotPwd(final String aMobile, final String aVerifyCode, final String aPwd, final String aPwd2) {
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#changePwd(java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String)
     */

    @Override
    public void changePwd(final String aMobile, final String aOldPwd, final String aPwd, final String aPwd2) {
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#editUserInfo(java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String, com.youthen.lj.app.bean.AppBuilding[], com.youthen.lj.app.bean.AppPark[])
     */

    @Override
    public void editUserInfo(final String aMobile, final String aName, final String aNickName, final String aImage,
            final AppBuilding[] aBuilding) {
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#getUserInfo(java.lang.String)
     */

    @Override
    public AppLoginUser getUserInfo(final String aMobile) {
        return null;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#getVerifyCode(java.lang.String)
     */

    @Override
    public String getVerifyCode(final String aMobile) {
        return null;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#getNoticeOrActiveList(java.lang.String, java.lang.String,
     *      java.lang.String)
     */

    @Override
    public List<AppActive> getNoticeOrActiveList(final String aPageSize, final String aGotoPage, final String aType) {
        final LjNoticeDto noticeDto = new LjNoticeDto();
        noticeDto.setPageSize(Integer.parseInt(aPageSize));
        noticeDto.setGotoPage(Integer.parseInt(aGotoPage));
        noticeDto.setStatus(1);
        noticeDto.setType(Integer.parseInt(aType));
        final List<LjNoticeDto> noticeList = this.ljNoticeService.getLjNoticeList(noticeDto);
        final List<AppActive> appActiveList = new ArrayList<AppActive>();
        for (final LjNoticeDto ljNotice : noticeList) {
            final AppActive appActive = new AppActive();
            BeanUtils.copyAllNullableProperties(ljNotice, appActive);
            appActiveList.add(appActive);
        }
        return appActiveList;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#getNoticeOrActiveDetail(java.lang.String)
     */

    @Override
    public AppActive getNoticeOrActiveDetail(final String aId) {
        final LjNoticeDto noticeDto = this.ljNoticeService.getById(Long.valueOf(aId));
        final AppActive appActive = new AppActive();
        BeanUtils.copyAllNullableProperties(noticeDto, appActive);
        return appActive;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#myRepairOrComplainList(java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String)
     */

    @Override
    public List<LjRepairCplnDto> myRepairOrComplainList(final String aMobile, final String aType, final String aStatus,
            final String aYear,
            final String aMonth) {
        return null;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#myRepairOrComplainDetail(java.lang.String, java.lang.String)
     */

    @Override
    public LjRepairCplnDto myRepairOrComplainDetail(final String aMobile, final String aId) {
        return null;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#closeMyRepairOrComplain(java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */

    @Override
    public void closeMyRepairOrComplain(final String aMobile, final String aId, final String aSpeed,
            final String aService,
            final String aCommentContent, final String aVerifyCode) {
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#feedBack(java.lang.String, java.lang.String, java.lang.String)
     */

    @Override
    public Long feedBack(final String aMobile, final String aContent, final String aVerifyCode) {
        return null;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#aboutUs()
     */

    @Override
    public String aboutUs() {
        return null;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#repairOrCpln(java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String,
     *      net.sf.json.JSONArray)
     */

    @Override
    public Long repairOrCpln(final String aMobile, final String aTel, final String aContent,
            final String aRepairObject, final String aRoomCode,
            final String aTitle, final String aContacterTel, final String aContacter, final String aType,
            final JSONArray aImages) {
        return null;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#myScoreHistory(java.lang.String, java.lang.String, java.lang.String)
     */

    @Override
    public List<ScoreHistory> myScoreHistory(final String aMobile, final String aGotoPage, final String aPageSize) {
        return null;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#myNewMessages(java.lang.String)
     */

    @Override
    public List<NewMessage> myNewMessages(final String aMobile) {
        return null;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#sign(java.lang.String)
     */

    @Override
    public String sign(final String aMobile) {
        return null;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#upload(java.io.File[])
     */

    @Override
    public List<String> upload(final File[] aImages) {
        return null;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#addComment(java.lang.String, java.lang.String, java.lang.String,
     *      java.lang.String)
     */

    @Override
    public Long addComment(final String aId, final String aType, final String aMobile, final String aContent) {
        return null;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#noticeAndactive()
     */

    @Override
    public List noticeAndactive(final String gotoPage, final String pageSize) {
        return null;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#createVerifyCode(java.lang.String)
     */

    @Override
    public String createVerifyCode(final String aMobile) {
        return null;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#cancelRepairOrCpl(java.lang.String, java.lang.String)
     */

    @Override
    public void cancelRepairOrCpl(final String aMobile, final String aId) {
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#getRoomInfo(java.lang.String)
     */

    @Override
    public RoomInfo getRoomInfo(final String aRoomCode) {
        String lastPeriod = null;
        String price = null;
        try {
            final LjHttpSoap hs = new LjHttpSoap();
            final String xmlText = hs.getSoapInputStream("getHouseInfo", new String[] {aRoomCode});
            final ClientResponseHandler handler = new ClientResponseHandler();
            handler.setLjContent(xmlText);
            final String res = handler.getAllParameters().get("Body").toString();
            final Document doc = DocumentHelper.parseText(res);
            final Element user = doc.getRootElement();
            final Element result = user.element("getHouseInfoResult");
            JSONObject json = JsonUtils.stringToObject(result.getText());
            json = JsonUtils.stringToObject(json.get("data").toString());
            final JSONArray js = (JSONArray) json.get("CustomerInfo");
            if ("[]".equals(js.toString())) {

            } else {
                json = (JSONObject) js.get(0);
                lastPeriod = (String) json.get("lastPeriod");
                price = (String) json.get("unitPrice");
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        final RoomInfo roomInfo = new RoomInfo();
        roomInfo.setLastPeriod(lastPeriod);
        roomInfo.setPrice(price);
        roomInfo.setRoomCode(aRoomCode);
        return roomInfo;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#addRoom(java.lang.String, java.lang.String, java.lang.String)
     */

    @Override
    public String addRoom(final String aMobile, final String aBuilding, final String aRoom) {
        return null;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#delRoom(java.lang.String, java.lang.String)
     */

    @Override
    public String delRoom(final String aMobile, final String aRoomCode) {
        return null;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#getKbn(java.lang.String)
     */

    @Override
    public AppKbn getKbn(final String aId) {
        final KbnDto kbnDto = this.kbnService.getById(Long.valueOf(aId));
        final AppKbn appKbn = new AppKbn();
        BeanUtils.copyAllNullableProperties(kbnDto, appKbn);
        return appKbn;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#getKbnList(java.lang.String)
     */

    @Override
    public List<AppKbn> getKbnList(final String code) {
        final KbnDto kbnDto = new KbnDto();
        kbnDto.setCode(code);
        final Long smallId = this.kbnService.getKbnListBy(kbnDto).get(0).getId();
        final List<KbnDto> kbnList =
                this.kbnService.getByParentTypeId(smallId);
        final List<AppKbn> appKbnList = new ArrayList<AppKbn>();
        for (final KbnDto kbnDtos : kbnList) {
            final AppKbn appKbn = new AppKbn();
            BeanUtils.copyAllNullableProperties(kbnDtos, appKbn);
            appKbnList.add(appKbn);
        }
        return appKbnList;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#getMerchantList(java.lang.String)
     */

    @Override
    public List<AppMerchant> getMerchantList(final String aSmallTypeId, final String gotoPage, final String pageSize) {
        final LjMerchantDto merchantDto = new LjMerchantDto();
        merchantDto.setSmallTypeId(Long.valueOf(aSmallTypeId));
        merchantDto.setGotoPage(Integer.parseInt(gotoPage));
        merchantDto.setPageSize(Integer.parseInt(pageSize));
        merchantDto.setStatus(1);
        final List<LjMerchantDto> merchantList = this.ljMerchantService.getMerchantList(merchantDto);
        final List<AppMerchant> appMerchantList = new ArrayList<AppMerchant>();
        for (final LjMerchantDto ljMerchant : merchantList) {
            final AppMerchant appMerchant = new AppMerchant();
            BeanUtils.copyAllNullableProperties(ljMerchant, appMerchant);
            appMerchantList.add(appMerchant);
        }
        return appMerchantList;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#getMerchantDetail(java.lang.String)
     */

    @Override
    public AppMerchant getMerchantDetail(final String aId) {
        final LjMerchantDto merchantDto = this.ljMerchantService.getById(Long.valueOf(aId));
        AppMerchant appMerchant = null;
        if (merchantDto != null) {
            appMerchant = new AppMerchant();
            BeanUtils.copyAllNullableProperties(merchantDto, appMerchant);
        }
        return appMerchant;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#getStoreDetail(java.lang.String)
     */

    @Override
    public AppMerchant getStoreDetail(final String code) {
        final KbnDto kbnDto = new KbnDto();
        kbnDto.setCode(code);
        final Long smallId = this.kbnService.getKbnListBy(kbnDto).get(0).getId();
        LjMerchantDto merchantDto = new LjMerchantDto();
        merchantDto.setSmallTypeId(smallId);
        final List<LjMerchantDto> list = this.ljMerchantService.getMerchantList(merchantDto);
        merchantDto = list.get(0);
        final AppMerchant appMerchant = new AppMerchant();
        BeanUtils.copyAllNullableProperties(merchantDto, appMerchant);
        return appMerchant;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#getExpressCompany()
     */

    @Override
    public List<String> getExpressCompany() {
        return null;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#kuaidiPost(java.lang.String, java.lang.String)
     */

    @Override
    public String kuaidiQuery(final String companyCode, final String orderNo) {

        String result = "";
        try {
            final String body = "{\"com\":\"" + companyCode + "\",\"num\":\"" + orderNo + "\"}";
            final String param = body + AppConfig.APP_KUAIDI_KEY + AppConfig.APP_KUAIDI_COM;
            logger.info("**************param:" + param);
            final String sign = MD5Util.MD5Encode(param, "utf-8").toUpperCase();
            final String httpUrl = "http://www.kuaidi100.com/poll/query.do";
            final String postData = "customer=" + AppConfig.APP_KUAIDI_COM + "&sign=" + sign + "&param=" + body;

            final TenpayHttpClient client = new TenpayHttpClient();
            client.callHttpPost(httpUrl, postData);
            final String rs = client.getResContent();
            // result = new String(rs.getBytes("gb2312"), "utf-8");
            result = rs;
        } catch (final Exception e) {
            result = e.getMessage();
            e.printStackTrace();
        }
        logger.info("************** result :" + result);
        return result;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#getUserNoticeOrActiveList(java.lang.String, java.lang.String,
     *      java.lang.String, java.lang.String)
     */

    @SuppressWarnings("boxing")
    @Override
    public List<AppActive> getUserNoticeOrActiveList(final String moblie, final String aType, final String aPageSize,
            final String aGotoPage) {
        final LjNoticeDto noticeDto = new LjNoticeDto();
        if (StringUtils.isNotEmpty(aType)) {
            noticeDto.setType(Integer.valueOf(aType));
        }
        noticeDto.setId(Long.valueOf(moblie));
        noticeDto.setPages(Integer.valueOf(aPageSize));
        noticeDto.setGotoPage(Integer.valueOf(aGotoPage));
        noticeDto.setStatus(1);

        final List<LjNoticeDto> noticeOrActiceList = this.ljNoticeService.getLjNoticeList(noticeDto);
        final List<AppActive> appActiveList = new ArrayList<AppActive>();
        for (final LjNoticeDto notice : noticeOrActiceList) {
            final AppActive appActive = new AppActive();
            BeanUtils.copyAllNullableProperties(notice, appActive);
            appActiveList.add(appActive);
        }
        return appActiveList;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#addTopicOrActive(java.lang.String, net.sf.json.JSONArray,
     *      java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
     */

    @Override
    public Long addTopicOrActive(final String aMobile, final String aTitle, final JSONArray aImages,
            final String aContent, final String aStartDate, final String aEndDate, final String aPlanNum,
            final String aType, final String aPlace) {
        return null;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#participateInActivities(java.lang.String,java.lang.String)
     */

    @Override
    public void participateInActivities(final String aActivityId, final String aUserId) {
        final LjNoticeDto dto = this.ljNoticeService.getById(Long.valueOf(aActivityId));

        int infactNum = dto.getInfactNum();
        final int planNum = dto.getPlanNum();
        if (infactNum < planNum) {
            infactNum++;
            final LjActionUserDto actionUserDto = new LjActionUserDto();
            actionUserDto.setActionId(Long.valueOf(aActivityId));
            actionUserDto.setUserId(aUserId);
            actionUserDto.setType(1);
            try {
                this.ljActionUserService.insert(actionUserDto);
            } catch (final DuplicateKeyException e) {
                e.printStackTrace();
            }
        }
        dto.setInfactNum(infactNum);
        this.ljNoticeService.update(dto);

    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#getPayStatus(java.lang.String)
     */

    @Override
    public String[] getPayStatus(final String aOrderNo) {
        final LjFeeHistoryDto condtion = new LjFeeHistoryDto();
        condtion.setPayNo(aOrderNo);

        final List<LjFeeHistoryDto> dtoList = this.ljFeeHistoryService.getFeeHistoryList(condtion);
        final String[] result = new String[2];
        if (CollectionUtils.isNotEmpty(dtoList)) {
            final LjFeeHistoryDto dto = dtoList.get(0);

            result[0] = dto.getStatus().toString();
            result[1] = dto.getType().toString();
            return result;
        }

        result[0] = "-1";

        return result;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#kuaidiList()
     */

    @Override
    public List kuaidiList() {
        final List list = this.kbnService.getKbnListByType("EXPRESS_COMPANY");
        return list;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#closeSecondGoods(java.lang.String, java.lang.String)
     */

    @Override
    public void closeSecondGoods(final String aMobile, final String aId) {
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#thumbUp(java.lang.String, java.lang.String)
     */

    @Override
    public void thumbUp(final String aActivityId, final String aUserId) {
        final LjActionUserDto actionUserDto = new LjActionUserDto();
        actionUserDto.setActionId(Long.valueOf(aActivityId));
        actionUserDto.setUserId(aUserId);
        actionUserDto.setType(0);
        try {
            this.ljActionUserService.insert(actionUserDto);
        } catch (final DuplicateKeyException e) {
            e.printStackTrace();
        }
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#isHouseExisted(java.lang.String)
     */

    @Override
    public boolean isHouseExisted(final String aRoomCode) {
        boolean isExisted = false;
        try {
            final LjHttpSoap hs = new LjHttpSoap();
            final String xmlText = hs.getSoapInputStream("getHouseInfo", new String[] {aRoomCode});
            final ClientResponseHandler handler = new ClientResponseHandler();
            handler.setLjContent(xmlText);
            final String res = handler.getAllParameters().get("Body").toString();
            final Document doc = DocumentHelper.parseText(res);
            final Element user = doc.getRootElement();
            final Element result = user.element("getHouseInfoResult");
            JSONObject json = JsonUtils.stringToObject(result.getText());
            json = JsonUtils.stringToObject(json.get("data").toString());
            final JSONArray js = (JSONArray) json.get("CustomerInfo");
            if ("[]".equals(js.toString())) {
                isExisted = false;
            } else {
                isExisted = true;
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return isExisted;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#getHousePrice(java.lang.String)
     */

    @Override
    public double getHousePrice(final String aRoomCode) {
        double price = 0;
        try {
            final LjHttpSoap hs = new LjHttpSoap();
            final String xmlText = hs.getSoapInputStream("getHouseInfo", new String[] {aRoomCode});
            final ClientResponseHandler handler = new ClientResponseHandler();
            handler.setLjContent(xmlText);
            final String res = handler.getAllParameters().get("Body").toString();
            final Document doc = DocumentHelper.parseText(res);
            final Element user = doc.getRootElement();
            final Element result = user.element("getHouseInfoResult");
            JSONObject json = JsonUtils.stringToObject(result.getText());
            json = JsonUtils.stringToObject(json.get("data").toString());
            final JSONArray js = (JSONArray) json.get("CustomerInfo");
            if ("[]".equals(js.toString())) {

            } else {
                json = (JSONObject) js.get(0);
                price = Double.parseDouble((String) json.get("unitPrice"));
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return price;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#getParkPrice(java.lang.String)
     */

    @Override
    public double getParkPrice(final String aParkNo) {
        double price = 0;
        try {
            final LjHttpSoap hs = new LjHttpSoap();
            final String xmlText = hs.getSoapInputStream("getParkInfo", new String[] {aParkNo});
            final ClientResponseHandler handler = new ClientResponseHandler();
            handler.setLjContent(xmlText);
            final String res = handler.getAllParameters().get("Body").toString();
            final Document doc = DocumentHelper.parseText(res);
            final Element user = doc.getRootElement();
            final Element result = user.element("getParkInfoResult");
            JSONObject json = JsonUtils.stringToObject(result.getText());
            json = JsonUtils.stringToObject(json.get("data").toString());
            final JSONArray js = (JSONArray) json.get("ParkInfo");
            if ("[]".equals(js.toString())) {

            } else {
                json = (JSONObject) js.get(0);
                price = (double) json.get("unitprice");
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return price;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#getHouseLastPeriod(java.lang.String)
     */

    @Override
    public String getHouseLastPeriod(final String aRoomCode) {
        String lastPeriod = null;
        try {
            final LjHttpSoap hs = new LjHttpSoap();
            final String xmlText = hs.getSoapInputStream("getHouseInfo", new String[] {aRoomCode});
            final ClientResponseHandler handler = new ClientResponseHandler();
            handler.setLjContent(xmlText);
            final String res = handler.getAllParameters().get("Body").toString();
            final Document doc = DocumentHelper.parseText(res);
            final Element user = doc.getRootElement();
            final Element result = user.element("getHouseInfoResult");
            JSONObject json = JsonUtils.stringToObject(result.getText());
            json = JsonUtils.stringToObject(json.get("data").toString());
            final JSONArray js = (JSONArray) json.get("CustomerInfo");
            if ("[]".equals(js.toString())) {

            } else {
                json = (JSONObject) js.get(0);
                lastPeriod = (String) json.get("lastPeriod");
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return lastPeriod;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#getParkLastPeriod(java.lang.String)
     */

    @Override
    public String getParkLastPeriod(final String aParkNo) {
        String lastPeriod = null;
        try {
            final LjHttpSoap hs = new LjHttpSoap();
            final String xmlText = hs.getSoapInputStream("getParkInfo", new String[] {aParkNo});
            final ClientResponseHandler handler = new ClientResponseHandler();
            handler.setLjContent(xmlText);
            final String res = handler.getAllParameters().get("Body").toString();
            final Document doc = DocumentHelper.parseText(res);
            final Element user = doc.getRootElement();
            final Element result = user.element("getParkInfoResult");
            JSONObject json = JsonUtils.stringToObject(result.getText());
            json = JsonUtils.stringToObject(json.get("data").toString());
            final JSONArray js = (JSONArray) json.get("ParkInfo");
            if ("[]".equals(js.toString())) {

            } else {
                json = (JSONObject) js.get(0);
                lastPeriod = (String) json.get("lastPeriod");
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return lastPeriod;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#getVerifyCode()
     */

    @Override
    public String getVerifyCode2() {
        return null;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#procWYGLJiaoFei(java.lang.String, java.lang.String)
     */

    @Override
    public String procWYGLJiaoFei(final String aRoomCode, final String aLastPeriod) {
        String msg = null;
        try {
            final LjHttpSoap hs = new LjHttpSoap();
            final String xmlText = hs.getSoapInputStream("procWYGLJiaoFei", new String[] {aRoomCode, aLastPeriod});
            final ClientResponseHandler handler = new ClientResponseHandler();
            handler.setLjContent(xmlText);
            final String res = handler.getAllParameters().get("Body").toString();
            final Document doc = DocumentHelper.parseText(res);
            final Element user = doc.getRootElement();
            final Element result = user.element("procWYGLJiaoFeiResult");
            final JSONObject json = JsonUtils.stringToObject(result.getText());
            if (json.get("errorCode").toString().equals("0")) {
                msg = "缴费成功";
            } else {
                msg = "缴费失败," + json.get("errorInfo").toString();
            }

        } catch (final Exception e) {
            e.printStackTrace();
        }
        return msg;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#updateHeadPic(javax.servlet.http.HttpServletRequest)
     */

    @Override
    public String updateHeadPic(final String userId, final ServletInputStream image) {
        return null;
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#delRepariOrCpl(java.lang.String)
     */

    @Override
    public Result delRepariOrCpl(final String aId) {
        return this.ljRepairCplnService.delete(Long.valueOf(aId));
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#deleteTopicOrActive(java.lang.String)
     */

    @Override
    public void deleteTopicOrActive(final String aId) {
        final LjNoticeDto dto = this.ljNoticeService.getById(Long.valueOf(aId));
        dto.setStatus(0);
        this.ljNoticeService.update(dto);
    }

    /**
     * @see com.youthen.lj.app.logic.LjAppLogic#getActionUserList(java.lang.String, java.lang.String)
     */

    @Override
    public List<AppActionUser> getActionUserList(final String aActionId, final String aType) {
        return null;
    }

}
