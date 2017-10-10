// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.app.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cloopen.rest.sdk.CCPRestSmsSDK;
import com.tenpay.ClientRequestHandler;
import com.tenpay.PrepayIdRequestHandler;
import com.tenpay.ResponseHandler;
import com.tenpay.Signature;
import com.tenpay.client.ClientResponseHandler;
import com.tenpay.util.TenpayUtil;
import com.tenpay.util.WXUtil;
import com.youthen.framework.common.DateFormatUtils;
import com.youthen.framework.common.StringUtils;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.framework.common.exception.ObjectNotFoundException;
import com.youthen.framework.common.exception.OptimisticLockStolenException;
import com.youthen.framework.util.BeanUtils;
import com.youthen.framework.util.CommonUtils;
import com.youthen.framework.util.MailSender;
import com.youthen.framework.util.VertifyCodeUtils;
import com.youthen.lj.app.bean.AppActionUser;
import com.youthen.lj.app.bean.AppActive;
import com.youthen.lj.app.bean.AppComment;
import com.youthen.lj.app.bean.AppKbn;
import com.youthen.lj.app.bean.AppLoginUser;
import com.youthen.lj.app.bean.AppMerchant;
import com.youthen.lj.app.bean.NewMessage;
import com.youthen.lj.app.bean.Result;
import com.youthen.lj.app.bean.RoomInfo;
import com.youthen.lj.app.bean.ScoreHistory;
import com.youthen.lj.app.bean.WxUserInfo;
import com.youthen.lj.app.logic.LjAppLogic;
import com.youthen.lj.app.service.LjAppService;
import com.youthen.lj.constant.AppConfig;
import com.youthen.lj.persistence.entity.AccessToken;
import com.youthen.lj.persistence.entity.LjActionComment;
import com.youthen.lj.persistence.entity.LjUserBuilding;
import com.youthen.lj.service.LjActionCommentService;
import com.youthen.lj.service.LjActionUserService;
import com.youthen.lj.service.LjFeeHistoryService;
import com.youthen.lj.service.LjMsgTipService;
import com.youthen.lj.service.LjNoticeService;
import com.youthen.lj.service.LjRepairCplnService;
import com.youthen.lj.service.LjRoomInfoService;
import com.youthen.lj.service.LjScoreHistoryService;
import com.youthen.lj.service.LjUserBuildingService;
import com.youthen.lj.service.dto.LjActionCommentDto;
import com.youthen.lj.service.dto.LjActionUserDto;
import com.youthen.lj.service.dto.LjFeeHistoryDto;
import com.youthen.lj.service.dto.LjMsgTipDto;
import com.youthen.lj.service.dto.LjNoticeDto;
import com.youthen.lj.service.dto.LjRepairCplnDto;
import com.youthen.lj.service.dto.LjRoomInfoDto;
import com.youthen.lj.service.dto.LjScoreHistoryDto;
import com.youthen.lj.utils.JsonUtils;
import com.youthen.lj.utils.UploadUtils;
import com.youthen.master.common.BusinessCheckException;
import com.youthen.master.persistence.dao.LoginUserDao;
import com.youthen.master.persistence.entity.LoginUser;
import com.youthen.master.service.KbnService;
import com.youthen.master.service.LoginUserService;
import com.youthen.master.service.dto.LoginUserDto;
import com.youthen.master.util.CommonUtil;

/**
 * 。
 * 
 * @author Administrator
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Service("ljAppService")
public class LjAppServiceImpl implements LjAppService {

    private static Logger logger = Logger.getLogger(LjAppServiceImpl.class.getName());

    @Autowired
    private LoginUserService userService;

    @Autowired
    private LjFeeHistoryService ljFeeHistoryService;

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
    LjMsgTipService ljMsgTipService;

    @Autowired
    LjScoreHistoryService ljScoreHistoryService;

    @Autowired
    LjRoomInfoService ljRoomInfoService;

    @Autowired
    LjActionUserService ljActionUserService;

    @Autowired
    LjAppLogic ljAppLogic;

    @Autowired
    KbnService kbnService;

    JsonConfig jsonConfig = new JsonConfig();

    private Result checkMobile(final String mobile) {
        final Result result = new Result();
        if (StringUtils.isEmpty(mobile)) {
            result.setMessageCode(Result.FAIL);
            result.setMessage("手机号为空!");
            result.setResultObject(null);
            logger.info("=====================>手机号为空!");
            return result;
        }
        return result;
    }

    /**
     * @see com.youthen.lj.service.LjAppServiceTest#login(java.lang.String)
     */

    @Override
    @Transactional
    public String login(final String aParam, final HttpServletRequest request, final HttpSession session,
            final HttpServletResponse response) {

        logger.info("=========>开始登录");
        Result result = new Result();
        final Map<String, String> paramsMap = JsonUtils.stringToObject(aParam);
        final String mobile = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");
        final String pwd = paramsMap.get("pwd") == null ? "" : (String) paramsMap.get("pwd");

        try {

            result = checkMobile(mobile);
            if (result.getMessageCode() == Result.FAIL) {
                return JSONObject.fromObject(result).toString();
            }

            // 根据用户登录名获取到用户对象
            final LoginUserDto loginUserDto = this.userService.getUserByMobileNum(mobile);

            final String inputPwd = this.passwordEncoder.encodePassword(pwd, null);

            if (loginUserDto != null && inputPwd.equals(loginUserDto.getPassword())) {// 手机号密码都对

                final AppLoginUser appLoginUser = new AppLoginUser();

                if (canAddScore(mobile, AppConfig.ACTION_LOGIN)) {// 当天未登录过,增加积分
                    // 加积分
                    this.addScore(mobile, AppConfig.ACTION_LOGIN, AppConfig.SCORE_LOGIN);
                }

                final LoginUser aUser = updateUserToken(mobile);// 更新Token

                appLoginUser.setMobile(mobile);
                appLoginUser.setName(loginUserDto.getName());
                appLoginUser.setNickName(loginUserDto.getName());
                appLoginUser.setScore(loginUserDto.getScore());

                BeanUtils.copyAllNullableProperties(aUser, loginUserDto);
                // 登录成功 生成cookie
                setCookie(request, response, mobile, loginUserDto);

                // 登录成功
                result.setMessageCode(Result.SUCCESS);
                result.setMessage("登录成功");
                result.setResultObject(appLoginUser);
                session.setAttribute("LOGIN_USER", appLoginUser);
                logger.info("=====================>登录成功  " + JSONObject.fromObject(result).toString());
                return JSONObject.fromObject(result).toString();
            }

            // 登录失败
            result.setMessageCode(Result.FAIL);
            result.setMessage("登录失败,用户名或密码错误！");
            result.setResultObject(null);
            session.setAttribute("LOGIN_USER", null);
            logger.info("=====================>登录失败,用户名或密码错误！param=" + aParam);
            return JSONObject.fromObject(result).toString();

        } catch (final Exception e) {
            logger.info("=========>服务器异常，登陆失败");
            // 登录成功
            result.setMessageCode(Result.FAIL);
            result.setMessage(e.getMessage());

            logger.info("=====================>登录失败,异常" + e.getMessage() + "！param=" + aParam);
            return JSONObject.fromObject(result).toString();
        }
    }

    private void setCookie(final HttpServletRequest request,
            final HttpServletResponse response, final String mobile, final LoginUserDto appLoginUser) {
        final LoginUser tokenUser = this.updateUserToken(mobile);
        final AppLoginUser cookieUser = new AppLoginUser();
        cookieUser.setMobile(mobile);
        cookieUser.setName(appLoginUser.getName());
        cookieUser.setNickName(appLoginUser.getNickName());
        logger.info("===setCookie==================>" + tokenUser.getOpenId());
        cookieUser.setAccessToken(tokenUser.getOpenId());
        cookieUser.setScore(tokenUser.getScore());
        cookieUser.setHeadimgurl(appLoginUser.getImageName());
        removeCookie(request, response);
        final Cookie cookie =
                new Cookie(AppConfig.MANAGER_MEMORY, JSONObject.fromObject(cookieUser).toString());
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
    }

    private LoginUser updateUserToken(final String mobile) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        final long timeStamp = calendar.getTimeInMillis();
        final String token = this.passwordEncoder.encodePassword(mobile + timeStamp, null);// 生成令牌
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 7);// 更新令牌过期时间

        final LoginUser user = this.loginUserDao.getById(mobile);
        user.setOpenId(token);
        user.setExpireTime(calendar.getTime());
        this.loginUserDao.update(user);

        return user;
    }

    /**
     * 给用户增加积分
     * 
     * @param mobile
     * @param form
     * @param socre
     * @return
     */
    @Override
    @Transactional
    public int addScore(final String mobile, final String form, final int socre) {

        // 登录积分
        final LoginUser user = this.loginUserDao.getById(mobile);
        if (user != null) {

            user.setScore(user.getScore() + socre);
            this.loginUserDao.update(user);

            final LjScoreHistoryDto scoreHistoryDto = new LjScoreHistoryDto();
            scoreHistoryDto.setGotFrom(form);
            scoreHistoryDto.setScore(Long.valueOf(socre));
            scoreHistoryDto.setUserId(mobile);
            scoreHistoryDto.setCreaterId(mobile);
            scoreHistoryDto.setCreateTime(new Date());
            try {
                this.ljScoreHistoryService.insert(scoreHistoryDto);
            } catch (final DuplicateKeyException e) {
                e.printStackTrace();
            }
            return user.getScore();
        }
        return 0;

    }

    /**
     * @see com.youthen.lj.service.LjAppServiceTest#reg(java.lang.String)
     */

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public String reg(final String aParam, final HttpSession session, final HttpServletRequest request,
            final HttpServletResponse response) {

        logger.info("=====================>注册开始  params=" + aParam);
        Result result = new Result();
        final Map<String, String> paramsMap = JsonUtils.stringToObject(aParam);
        final String mobile = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");
        final String pwd1 = paramsMap.get("pwd1") == null ? "" : (String) paramsMap.get("pwd1");
        final String pwd2 = paramsMap.get("pwd2") == null ? "" : (String) paramsMap.get("pwd2");
        final String verifyCode = paramsMap.get("verifyCode") == null ? "" : (String) paramsMap.get("verifyCode");
        final String headImageUrl = paramsMap.get("headimgurl") == null ? "" : (String) paramsMap.get("headimgurl");
        final String nickName = paramsMap.get("nickName") == null ? "" : paramsMap.get("nickName").toString();
        final String openId = paramsMap.get("openId") == null ? "" : (String) paramsMap.get("openId");
        final String accessToken = paramsMap.get("accessToken") == null ? "" : (String) paramsMap.get("accessToken");

        try {

            if (StringUtils.isEmpty(mobile)) {// 手机号为空
                result.setMessageCode(Result.FAIL);
                result.setMessage("手机号为空!");
                result.setResultObject(null);
                session.setAttribute("LOGIN_USER", null);
                logger.info("=====================>注册失败，手机号为空! " + aParam);
                return JSONObject.fromObject(result).toString();
            }

            if (!pwd2.equals(pwd1)) {// 两次密码不一致
                result.setMessageCode(Result.FAIL);
                result.setMessage("两次密码不一致!");
                result.setResultObject(null);
                session.setAttribute("LOGIN_USER", null);
                logger.info("=====================>两次密码不一致! " + aParam);
                return JSONObject.fromObject(result).toString();
            }
            result = this.isVertifyCodeOk(mobile, verifyCode, session);
            if (result.getMessageCode() == Result.FAIL) {// 验证码错误
                logger.info("=====================>注册失败，验证码错误!");
                return JSONObject.fromObject(result).toString();
            }

            LoginUserDto loginUserDto = this.userService.getUserByMobileNum(mobile);
            if (loginUserDto == null) {
                System.out.println("/////////昵称" + nickName);
                loginUserDto = new LoginUserDto();
                loginUserDto.setUserId(mobile);
                loginUserDto.setMobile(mobile);
                loginUserDto.setName(mobile);
                loginUserDto.setImageName(headImageUrl);
                loginUserDto.setNickName(nickName);
                loginUserDto.setOpenId(openId);
                loginUserDto.setAccessToken(accessToken);
                loginUserDto.setDepartmentId(1L);
                loginUserDto.setCompanyId(1L);
                loginUserDto.setScore(AppConfig.SCORE_REG);
                loginUserDto.setPassword(this.passwordEncoder.encodePassword(pwd1, null));

                // 新增用户
                final List<LoginUserDto> users = new ArrayList<LoginUserDto>();
                final LoginUserDto aUser = new LoginUserDto();
                aUser.setUserId(mobile);
                aUser.setDepartmentId(1L);
                aUser.setCompanyId(1L);
                final Long[] sysIds = {2L};
                aUser.setSystemIds(sysIds);// LJ APP系统
                aUser.setScore(AppConfig.SCORE_REG);
                final Long[] roleIds = {1L};
                aUser.setRoleIds(roleIds);// LJ APP 系统用户

                users.add(aUser);
                this.userService.addUser(loginUserDto, users);
                final AppLoginUser appLoginUser = new AppLoginUser();
                session.setAttribute("MOBILE", mobile);
                appLoginUser.setScore(AppConfig.SCORE_REG);
                session.setAttribute("OPEN_ID", loginUserDto.getOpenId());
                session.setAttribute("LOGIN_USER", appLoginUser);

                // 注册加积分
                addScore(mobile, AppConfig.ACTION_REG, AppConfig.SCORE_REG);

                // 注册成功 生成cookie
                setCookie(request, response, mobile, loginUserDto);

                result.setMessageCode(Result.SUCCESS);
                result.setMessage("注册成功！");
                result.setResultObject(appLoginUser);// 跳到首页的数据
                logger.info("=====================>注册成功!" + JSONObject.fromObject(result).toString());
                return JSONObject.fromObject(result).toString();

            }

            // 手机号已注册
            result.setMessageCode(Result.FAIL);
            result.setMessage("手机号已被注册！");
            logger.info("=====================>注册失败，手机号已被注册！" + aParam);
            return JSONObject.fromObject(result).toString();

        } catch (final Exception e) {
            e.printStackTrace();
            result.setMessageCode(Result.FAIL);
            result.setMessage(e.getMessage());
            logger.info("=====================>注册失败，异常：" + e.getMessage() + " params=" + aParam);
            return JSONObject.fromObject(result).toString();
        }
    }

    /**
     * @see com.youthen.lj.service.LjAppServiceTest#forgotPwd(java.lang.String)
     */

    @Override
    @Transactional
    public String forgotPwd(final String aParam, final HttpSession session) {

        logger.info("=====================>注册开始  params=" + aParam);
        final String successInf = "=====================>忘记密码，重置密码成功";
        final Map paramsMap = JsonUtils.stringToObject(aParam);
        Result result = new Result();

        final String mobile = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");
        final String verifyCode = paramsMap.get("verifyCode") == null ? "" : (String) paramsMap.get("verifyCode");
        final String pwd1 = paramsMap.get("pwd1") == null ? "" : (String) paramsMap.get("pwd1");
        final String pwd2 = paramsMap.get("pwd2") == null ? "" : (String) paramsMap.get("pwd2");

        result = checkMobile(mobile);
        if (result.getMessageCode() == Result.FAIL) {
            return JSONObject.fromObject(result).toString();
        }

        result = this.isVertifyCodeOk(mobile, verifyCode, session);

        if (result.SUCCESS == result.getMessageCode()) {

            if (StringUtils.isEmpty(pwd1)) {
                result.setMessageCode(Result.FAIL);
                result.setMessage("密码不能为空");

                logger.info("=====================>忘记密码失败 ，密码不能为空" + " params=" + aParam);
                return JSONObject.fromObject(result).toString();
            }

            if (StringUtils.isEmpty(pwd2)) {
                result.setMessageCode(Result.FAIL);
                result.setMessage("重复密码不能为空");
                logger.info("=====================>忘记密码失败 ，重复密码不能为空" + " params=" + aParam);
                return JSONObject.fromObject(result).toString();
            }

            if (!pwd2.equals(pwd1)) {
                result.setMessageCode(Result.FAIL);
                result.setMessage("二次密码不一致");
                logger.info("=====================>忘记密码失败 ，二次密码不一致" + " params=" + aParam);
                return JSONObject.fromObject(result).toString();
            }

            final LoginUserDto loginUserDto = this.userService.getUserByMobileNum(mobile);

            if (loginUserDto == null) {
                result.setMessageCode(Result.FAIL);
                result.setMessage("手机号不存在");
                logger.info("=====================>忘记密码失败 ，手机号不存在" + " params=" + aParam);
                return JSONObject.fromObject(result).toString();
            }
            try {
                loginUserDto.setPassword(this.passwordEncoder.encodePassword(pwd1, null));
                this.userService.profileUpdateUser(loginUserDto);
                result.setMessageCode(Result.SUCCESS);
                result.setMessage("密码修改成功");
                logger.info(successInf + " " + JSONObject.fromObject(result).toString());
                return JSONObject.fromObject(result).toString();
            } catch (final Exception e) {
                e.printStackTrace();
                result.setMessageCode(Result.FAIL);
                result.setMessage(e.getMessage());
                logger.info("=====================>忘记密码失败 ，异常：" + e.getMessage() + " params=" + aParam);
            }

        }

        return JSONObject.fromObject(result).toString();
    }

    @Override
    public Result sendSMS(final String mobile, final String content, final String template) {
        final Result retResult = new Result();
        HashMap<String, Object> result = null;

        // 初始化SDK
        final CCPRestSmsSDK restAPI = new CCPRestSmsSDK();

        // *初始化服务器地址和端口
        restAPI.init("app.cloopen.com", "8883");

        // *初始化主帐号和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN *
        // *ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取*
        // *参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN。 *
        restAPI.setAccount("aaf98f8951f7362501520c421c042211", "611bf3f48555496e8edc2ef9904d8931");

        // *初始化应用ID *
        // *测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID *
        // *应用ID的获取：登陆官网，在“应用-应用列表”，点击应用名称，看应用详情获取APP ID*
        restAPI.setAppId("8a48b5515388ec150153bb078dac4c12");

        // ******************************注释****************************************************************
        // *调用发送模板短信的接口发送短信 *
        // *参数顺序说明： *
        // *第一个参数:是要发送的手机号码，可以用逗号分隔，一次最多支持100个手机号 *
        // *第二个参数:是模板ID，在平台上创建的短信模板的ID值；测试的时候可以使用系统的默认模板，id为1。 *
        // *系统默认模板的内容为“【云通讯】您使用的是云通讯短信模板，您的验证码是{1}，请于{2}分钟内正确输入”*
        // *第三个参数是要替换的内容数组。 *
        // **************************************************************************************************
        result = restAPI.sendTemplateSMS(mobile, template, new String[] {content, "1"});

        logger.info("SDKTestGetSubAccounts result=" + result);
        if ("000000".equals(result.get("statusCode"))) {
            // 正常返回输出data包体信息（map）
            final HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
            final Set<String> keySet = data.keySet();
            for (final String key : keySet) {
                final Object object = data.get(key);
                logger.info(key + " = " + object);
            }

            retResult.setMessageCode(Result.SUCCESS);
            retResult.setMessage("短信验证码发送成功");
            return retResult;
        }
        // 异常返回输出错误码和错误信息
        logger.info("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
        retResult.setMessageCode(Result.FAIL);
        retResult.setMessage("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
        return retResult;

    }

    /**
     * @see com.youthen.lj.service.LjAppService#getVerifyCode(java.lang.String, HttpSession session)
     */

    @Override
    public String getVerifyCode(final String aParam, final HttpSession session) {

        Result result = new Result();
        final Map paramsMap = JsonUtils.stringToObject(aParam);
        final String mobile = paramsMap.get("mobile") == null ? "" : paramsMap.get("mobile").toString();

        result = checkMobile(mobile);
        if (result.getMessageCode() == Result.FAIL) {
            return JSONObject.fromObject(result).toString();
        }

        if (StringUtils.isEmpty(mobile)) {
            result.setMessageCode(Result.FAIL);
            result.setMessage("手机号码为空！");
            return JSONObject.fromObject(result).toString();
        }

        final String sRand = CommonUtils.getVertifyCode(AppConfig.VERIFY_CODE_LENGTH);

        session.setAttribute(mobile + "_VERTIFY_CODE", sRand);
        session.setAttribute(mobile + "_TIME", new Date());

        result = this.sendSMS(mobile, sRand, "76276");

        if (Result.SUCCESS == result.getMessageCode()) {

            result.setMessageCode(Result.SUCCESS);
            result.setMessage("获取验证码成功！");
            result.setResultObject(sRand);
            logger.info("=====================>获取验证码成功！ " + JSONObject.fromObject(result).toString());
        }
        return JSONObject.fromObject(result).toString();
    }

    /**
     * 判断验证码是否超时和正确。
     * 。
     * 
     * @param mobile
     * @param aVertifyCode
     * @param session
     * @return
     */
    private Result isVertifyCodeOk(final String mobile, final String aVertifyCode, final HttpSession session) {
        final String vertifyCode = (String) session.getAttribute(mobile + "_VERTIFY_CODE");
        final Date gotVertifyCodeTime = (Date) session.getAttribute(mobile + "_TIME");

        final Result result = new Result();
        if (!StringUtils.isEmpty(vertifyCode) && vertifyCode.equalsIgnoreCase(aVertifyCode)) {
            final Date now = new Date();
            final long time = now.getTime() - gotVertifyCodeTime.getTime();// 时间差
            if (time >= 60000) {
                result.setMessageCode(Result.FAIL);
                result.setMessage("验证码超时，请重新获取验证码！");
                session.setAttribute(mobile + "_VERTIFY_CODE", null);
            } else {
                result.setMessageCode(Result.SUCCESS);
                result.setMessage("验证码输入正确！");
            }

        } else {
            result.setMessageCode(Result.FAIL);
            result.setMessage("验证码不一致！");
            logger.info("=====================>验证码不一致！");

        }
        return result;

    }

    /**
     * 判断用户是否绑定
     * 
     * @see com.youthen.lj.app.service.LjAppService#isUserBinded(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.String)
     */
    @Override
    public String isUserBinded(final HttpServletRequest aReq,
            final HttpServletResponse aResponse, final String openId) {
        final Result result = new Result();
        System.out.println("============/////openId" + openId);
        if (StringUtils.isNotEmpty(openId)) {

            final LoginUser user = this.loginUserDao.getByOpenId(openId);
            if (user != null) {
                final LoginUserDto bindParam = new LoginUserDto();
                bindParam.setAccessToken(user.getAccessToken());
                bindParam.setMobile(user.getMobile());
                bindParam.setOpenId(user.getOpenId());
                setCookie(aReq, aResponse, user.getMobile(), bindParam);

                result.setMessageCode(Result.SUCCESS);
                result.setResultObject(user);
            } else {
                result.setMessageCode(Result.FAIL);
                result.setMessage("未绑定账号！");
            }
        } else {
            result.setMessage("opendId为空！");
            result.setMessageCode(Result.FAIL);
        }
        return JsonUtils.objectToString(result);
    }

    /**
     * 获取微信中的用户信息
     * 。
     * 
     * @param aAccessToken
     * @param openId
     * @return
     */
    public Result getWxUserInfo(final String aAccessToken, final String openId) {
        final String url =
                "https://api.weixin.qq.com/sns/userinfo?access_token=" + aAccessToken + "&openid=" + openId
                        + "&lang=zh_CN";
        logger.info("====>URL==>" + url);

        final Result rs = new Result();
        try {
            final URL getUrl = new URL(url);
            final HttpURLConnection http = (HttpURLConnection) getUrl.openConnection();
            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);

            http.connect();
            final InputStream is = http.getInputStream();

            final InputStream inputStream = http.getInputStream();
            final InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            final BufferedReader in = new BufferedReader(inputStreamReader);
            final String jsonUserStr = in.readLine().toString();
            final JSONObject json = JSONObject.fromObject(jsonUserStr);

            final WxUserInfo at = new WxUserInfo();
            at.setOpenid(json.getString("openid"));
            at.setCity(json.getString("city"));
            at.setNickname(json.getString("nickname"));
            at.setHeadimgurl(json.getString("headimgurl"));
            at.setSex(json.getString("sex"));
            logger.info("---->nickName " + json.getString("nickname"));
            rs.setMessageCode(Result.SUCCESS);
            rs.setResultObject(at);
        } catch (final MalformedURLException e) {
            e.printStackTrace();
            rs.setMessageCode(Result.FAIL);
            rs.setResultObject(e.getMessage());
        } catch (final IOException e) {
            e.printStackTrace();
            rs.setMessageCode(Result.SUCCESS);
            rs.setResultObject(e.getMessage());
        }

        logger.info("====>Rs==>" + JsonUtils.objectToString(rs));
        return rs;

    }

    /**
     * @see com.youthen.lj.service.LjAppServiceTest#getNoticeOrActiveList(java.lang.String)
     */

    @Override
    public String getNoticeOrActiveList(final String params) {

        final Map paramsMap = JsonUtils.stringToObject(params);
        final String pageSize = paramsMap.get("pageSize") == null ? "15" : paramsMap.get("pageSize").toString();
        final String gotoPage = paramsMap.get("gotoPage") == null ? "1" : paramsMap.get("gotoPage").toString();
        final String type = paramsMap.get("type") == null ? "" : paramsMap.get("type").toString();

        final LjNoticeDto condition = new LjNoticeDto();
        if (StringUtils.isNotEmpty(type)) {
            condition.setType(Integer.valueOf(type));
        }
        condition.setPageSize(Integer.valueOf(pageSize));
        condition.setGotoPage(Integer.valueOf(gotoPage));
        condition.setStatus(1);
        final List<LjNoticeDto> resultList = this.ljNoticeService.getLjNoticeList(condition);
        final List<AppActive> list = new ArrayList<AppActive>();
        if (resultList != null) {
            for (final LjNoticeDto dto : resultList) {
                final AppActive active = this.noticeToActive(dto);
                list.add(active);
            }
        }

        final Result result = new Result();
        result.setMessageCode(Result.SUCCESS);
        result.setMessage("获取社区通知和活动列表成功！");
        result.setResultObject(list);
        logger.info("=====================>获取社区通知和活动列表成功！！" + JSONObject.fromObject(result).toString());
        return JSONObject.fromObject(result).toString();
    }

    private AppActive noticeToActive(final LjNoticeDto ljNoticeDto) {
        final AppActive active = new AppActive();

        final String[] ignoreProperties = {"comments", "startDate", "startDate", "endDate", "createTime", "status"};
        BeanUtils.copyAllNullableProperties(ljNoticeDto, active, ignoreProperties);

        if (StringUtils.isNotEmpty(ljNoticeDto.getCreaterId())) {
            final LoginUser user = this.loginUserDao.getUserByUserId(ljNoticeDto.getCreaterId());
            if (user != null) {
                active.setCreaterNickName(user.getNickName());
                active.setCreaterImage(user.getImageName());
            }

        }
        if (ljNoticeDto.getCreateTime() != null) {
            active.setCreateTime(DateFormatUtils.format(ljNoticeDto.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
        }

        if (ljNoticeDto.getStartDate() != null) {
            active.setStartDate(DateFormatUtils.format(ljNoticeDto.getStartDate(), "yyyy-MM-dd"));
        }

        if (ljNoticeDto.getEndDate() != null) {
            active.setEndDate(DateFormatUtils.format(ljNoticeDto.getEndDate(), "yyyy-MM-dd"));
        }
        active.setStatus(ljNoticeDto.getStatus().toString());
        final List<AppComment> list = new ArrayList<AppComment>();
        if (ljNoticeDto.getComments() != null) {
            for (final LjActionComment cmt : ljNoticeDto.getComments()) {
                final AppComment comment = new AppComment();
                final String[] ignoreProperties2 = {"createTime"};
                BeanUtils.copyAllNullableProperties(cmt, comment, ignoreProperties2);

                if (StringUtils.isNotEmpty(cmt.getCreateId())) {
                    final LoginUser cmtUser = this.loginUserDao.getUserByUserId(cmt.getCreateId());
                    if (cmtUser != null) {
                        comment.setCreaterNickName(cmtUser.getNickName());
                        comment.setCreaterImage(cmtUser.getImageName());
                    }

                }

                if (cmt.getCreateTime() != null) {
                    comment.setCreateTime(DateFormatUtils.format(cmt.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
                }

                list.add(comment);
            }
        }
        active.setComments(list);

        active.setGotoPage(ljNoticeDto.getGotoPage());
        active.setPageSize(ljNoticeDto.getPageSize());

        final LjActionUserDto actionUserDto = new LjActionUserDto();
        actionUserDto.setActionId(ljNoticeDto.getId());
        actionUserDto.setType(0);
        active.setGoodNum(this.ljActionUserService.getActionUserList(actionUserDto).size());
        return active;
    }

    /**
     * @see com.youthen.lj.service.LjAppServiceTest#getNoticeOrActiveDetail(java.lang.String)
     */

    @Override
    public String getNoticeOrActiveDetail(final String aParam) {
        // final LjNoticeDto condition = new LjNoticeDto();

        final Map paramsMap = JsonUtils.stringToObject(aParam);
        final String id = paramsMap.get("id") == null ? "" : paramsMap.get("id").toString();
        final Result result = new Result();

        if (StringUtils.isNotEmpty(id)) {
            final LjNoticeDto ljNoticeDto = this.ljNoticeService.getById(Long.valueOf(id));
            final AppActive active = this.noticeToActive(ljNoticeDto);
            if (active.getType() == 3) {
                final LjActionUserDto actionUserDto = new LjActionUserDto();
                actionUserDto.setActionId(active.getId());
                actionUserDto.setType(1);
                final List<LjActionUserDto> ActionUserList = this.ljActionUserService.getActionUserList(actionUserDto);
                final List<AppActionUser> appActionUser = new ArrayList<AppActionUser>();
                for (final LjActionUserDto entity : ActionUserList) {
                    final AppActionUser actionUser = new AppActionUser();
                    actionUser.setUserId(entity.getUserId());
                    appActionUser.add(actionUser);
                }
                active.setActionUsers(appActionUser);
            }
            result.setMessageCode(Result.SUCCESS);
            result.setMessage("获取社区通知和活动详细成功！");
            result.setResultObject(active);
            logger.info("=====================>获取社区通知和活动详细成功！！" + JSONObject.fromObject(result).toString());
            return JSONObject.fromObject(result).toString();
        }

        result.setMessageCode(Result.FAIL);
        result.setMessage("获取社区通知和活动详细失败！");
        return JSONObject.fromObject(result).toString();

    }

    /**
     * @see com.youthen.lj.service.LjAppServiceTest#myRepairOrComplainList(java.lang.String)
     */

    @Override
    public String myRepairOrComplainList(final String aParam) {

        logger.info("=====================>我的报修或投诉列表开始  params=" + aParam);
        final Calendar now = Calendar.getInstance();

        Result result = new Result();
        final Map<String, String> paramsMap = JsonUtils.stringToObject(aParam);
        final String mobile = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");
        final String type = paramsMap.get("type") == null ? "" : (String) paramsMap.get("type");
        final String status = paramsMap.get("status") == null ? "" : (String) paramsMap.get("status");
        final String year =
                paramsMap.get("year") == null ? "" + now.get(Calendar.YEAR) : (String) paramsMap.get("year");
        final String month =
                paramsMap.get("month") == null ? "" + (now.get(Calendar.MONTH) + 1) : (String) paramsMap.get("month");
        final String pageSize = paramsMap.get("pageSize") == null ? "15" : (String) paramsMap.get("pageSize");
        final String gotoPage = paramsMap.get("gotoPage") == null ? "1" : (String) paramsMap.get("gotoPage");

        result = checkMobile(mobile);
        if (result.getMessageCode() == Result.FAIL) {
            return JSONObject.fromObject(result).toString();
        }

        if (StringUtils.isEmpty(mobile)) {
            result.setMessage("获取我的报修或投诉列表失败！");
            result.setMessageCode(result.FAIL);
            logger.info("=====================>我的报修或投诉列表失败" + aParam);
            return JSONObject.fromObject(result).toString();
        }

        final LjRepairCplnDto condition = new LjRepairCplnDto();
        condition.setType(Integer.valueOf(type));
        condition.setPages(Integer.parseInt(pageSize));
        condition.setGotoPage(Integer.parseInt(gotoPage));
        if (StringUtils.isNotEmpty(status)) {
            condition.setStatus(Integer.valueOf(status));
        }
        condition.setYear(year);
        condition.setMonth(month);
        condition.setReporterId(mobile);
        final List<LjRepairCplnDto> cplDtoList = this.ljRepairCplnService.getRepairCplnList(condition);

        result.setMessage("获取我的报修或投诉列表成功！");
        result.setMessageCode(result.SUCCESS);
        result.setResultObject(cplDtoList);
        logger.info("=====================>我的报修或投诉列表结束" + JSONObject.fromObject(result).toString());
        return JSONObject.fromObject(result).toString();
    }

    /**
     * @see com.youthen.lj.service.LjAppServiceTest#myRepairOrComplainDetail(java.lang.String)
     */

    @Override
    public String myRepairOrComplainDetail(final String aParam) {
        logger.info("=====================>查看我的报修或投诉=" + aParam);
        Result result = new Result();
        final Map<String, String> paramsMap = JsonUtils.stringToObject(aParam);
        final String mobile = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");
        final String id = paramsMap.get("id") == null ? "" : (String) paramsMap.get("id");

        result = checkMobile(mobile);
        if (result.getMessageCode() == Result.FAIL) {
            return JSONObject.fromObject(result).toString();
        }

        if (StringUtils.isNotEmpty(id)) {
            final LjRepairCplnDto dto = this.ljRepairCplnService.getById(Long.valueOf(id));
            if (mobile.equalsIgnoreCase(dto.getReporterId())) {
                result.setMessageCode(Result.SUCCESS);
                result.setMessage("查看我的报修或投诉成功！");
                result.setResultObject(dto);
                logger.info("=====================>查看我的报修或投诉成功！！" + JSONObject.fromObject(result).toString());
                return JSONObject.fromObject(result).toString();
            }
        }

        result.setMessageCode(Result.FAIL);
        result.setMessage("查看我的报修或投诉失败！");
        logger.info("=====================>查看我的报修或投诉结束" + aParam);
        return JSONObject.fromObject(result).toString();
    }

    /**
     * @see com.youthen.lj.service.LjAppServiceTest#CloseMyRepairOrComplain(java.lang.String)
     */

    @Override
    @Transactional
    public String closeMyRepairOrComplain(final String aParam, final HttpSession session) {
        logger.info("=====================>开始关闭我的报修或投诉  params=" + aParam);
        Result result = new Result();
        final Map<String, String> paramsMap = JsonUtils.stringToObject(aParam);
        final String mobile = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");
        final String id = paramsMap.get("id") == null ? "" : (String) paramsMap.get("id");
        final String speed = paramsMap.get("speed") == null ? "" : (String) paramsMap.get("speed");
        final String service = paramsMap.get("service") == null ? "" : (String) paramsMap.get("service");
        final String commentContent =
                paramsMap.get("commentContent") == null ? "" : (String) paramsMap.get("commentContent");

        final String verifyCode = paramsMap.get("verifyCode") == null ? "" : (String) paramsMap.get("verifyCode");

        result = checkMobile(mobile);
        if (result.getMessageCode() == Result.FAIL) {
            return JSONObject.fromObject(result).toString();
        }

        result = this.canSubmit(verifyCode, session);
        if (result.getMessageCode() == 0) {
            logger.info("=====================>关闭我的报修或投诉 失败  params=" + aParam + result.getMessage());
            return JSONObject.fromObject(result).toString();
        }

        if (StringUtils.isNotEmpty(id)) {
            final LjRepairCplnDto dto = this.ljRepairCplnService.getById(Long.valueOf(id));
            if (mobile.equalsIgnoreCase(dto.getReporterId())) {

                dto.setFinishTime(new Date());
                dto.setUpdId(mobile);
                dto.setStatus(4);
                dto.setSpeedMark(Integer.valueOf(speed));
                dto.setServiceMark(Integer.valueOf(service));
                dto.setCommentContent(commentContent);
                this.ljRepairCplnService.update(dto);

                result.setMessageCode(Result.SUCCESS);
                result.setMessage("关闭我的报修或投诉成功！");
                logger.info("=====================>关闭我的报修或投诉成功！" + JSONObject.fromObject(result).toString());
                return JSONObject.fromObject(result).toString();
            }
        }

        result.setMessageCode(Result.FAIL);
        result.setMessage("关闭我的报修或投诉失败！");
        logger.info("=====================>关闭我的报修或投诉失败！");
        return JSONObject.fromObject(result).toString();
    }

    /**
     * @see com.youthen.lj.service.LjAppServiceTest#myPayHistoryList(java.lang.String)
     */

    @Override
    public String myPayHistoryList(final String aParam) {

        logger.info("=====================>我的付费历史列表开始  params=" + aParam);
        Result result = new Result();
        final Map<String, String> paramsMap = JsonUtils.stringToObject(aParam);

        final String mobile = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");
        final String year = paramsMap.get("year") == null ? "" : (String) paramsMap.get("year");
        final String month = paramsMap.get("month") == null ? "" : (String) paramsMap.get("month");
        final String status = paramsMap.get("status") == null ? "1" : (String) paramsMap.get("status");
        final String type = paramsMap.get("type") == null ? "" : (String) paramsMap.get("type");

        final String gotoPage = paramsMap.get("gotoPage") == null ? "1" : (String) paramsMap.get("gotoPage");
        final String pageSize = paramsMap.get("pageSize") == null ? "40" : (String) paramsMap.get("pageSize");

        result = checkMobile(mobile);
        if (result.getMessageCode() == Result.FAIL) {
            return JSONObject.fromObject(result).toString();
        }

        final LjFeeHistoryDto condition = new LjFeeHistoryDto();
        condition.setUserId(mobile);
        condition.setGotoPage(Integer.valueOf(gotoPage));
        condition.setPageSize(Integer.valueOf(pageSize));

        if (StringUtils.isNotEmpty(month)) {
            condition.setFeeMonth(Integer.valueOf(month));
        }

        if (StringUtils.isNotEmpty(type)) {
            condition.setType(Integer.valueOf(type));
        }

        condition.setType(10);

        if (StringUtils.isNotEmpty(status)) {
            condition.setStatus(Integer.parseInt(status));
        }
        if (StringUtils.isNotEmpty(mobile)) {

            String roomCodes = "";
            final List<RoomInfo> userRoom = this.ljUserBuildingService.getUserRoom(mobile);
            for (int i = 0; i < userRoom.size(); i++) {
                roomCodes = roomCodes + userRoom.get(i).getRoomCode() + ",";
            }
            if (roomCodes.length() > 0) {
                roomCodes = roomCodes.substring(0, roomCodes.length() - 1);
            }
            // final List<AppUserPark> userCar = this.ljUserCarService.getUserPark(mobile);
            // for (int i = 0; i < userCar.size(); i++) {
            // parkNos = parkNos + userCar.get(i).getParkNo() + ",";
            // }
            // if (parkNos.length() > 0) {
            // parkNos = parkNos.substring(0, parkNos.length() - 1);
            // }
            // final List<LjFeeHistoryDto> payHistoryList = this.ljAppLogic.getPayHistoryList(mobile, roomCodes,
            // parkNos);
            // if (payHistoryList.size() < 1) {

            final List<LjFeeHistoryDto> feeHistoryList = this.ljFeeHistoryService.getFeeHistoryList(condition);
            if (feeHistoryList != null && feeHistoryList.size() != 0) {
                for (final LjFeeHistoryDto dto : feeHistoryList) {
                    dto.setUser(null);
                }
                result.setResultObject(feeHistoryList);

                // } else {
                // result.setResultObject(payHistoryList);
                // }

                result.setMessageCode(Result.SUCCESS);
                result.setMessage("获取我的付费历史列表成功！");
                logger.info("=====================>我的付费历史列表结束！" + JSONObject.fromObject(result).toString());
                return JSONObject.fromObject(result).toString();
            }

        }
        result.setMessageCode(Result.SUCCESS);
        result.setMessage("获取我的付费历史列表失败！");
        logger.info("=====================>获取我的付费历史列表失败！");
        return JSONObject.fromObject(result).toString();
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#changePwd(java.lang.String, javax.servlet.http.HttpSession)
     */

    @Override
    @Transactional
    public String changePwd(final String params, final HttpSession session) {

        logger.info("=====================>修改密码开始  params=" + params);
        final String successInf = "=====================>修改密码成功";
        final Map paramsMap = JsonUtils.stringToObject(params);
        Result result = new Result();

        final String mobile = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");
        final String oldPwd = paramsMap.get("oldPwd") == null ? "" : (String) paramsMap.get("oldPwd");
        final String pwd1 = paramsMap.get("pwd1") == null ? "" : (String) paramsMap.get("pwd1");
        final String pwd2 = paramsMap.get("pwd2") == null ? "" : (String) paramsMap.get("pwd2");

        result = checkMobile(mobile);
        if (result.getMessageCode() == Result.FAIL) {
            return JSONObject.fromObject(result).toString();
        }

        if (StringUtils.isEmpty(mobile)) {
            result.setMessageCode(Result.FAIL);
            result.setMessage("手机号为空");
            logger.info("=====================>手机号为空！  params=" + params);
            return JSONObject.fromObject(result).toString();
        }

        if (StringUtils.isEmpty(oldPwd)) {
            result.setMessageCode(Result.FAIL);
            result.setMessage("密码错误");
            logger.info("=====================>密码错误！  params=" + params);
            return JSONObject.fromObject(result).toString();
        }

        if (StringUtils.isEmpty(pwd1)) {
            result.setMessageCode(Result.FAIL);
            result.setMessage("密码不能为空");
            logger.info("=====================>密码不能为空！  params=" + params);
            return JSONObject.fromObject(result).toString();
        }

        if (StringUtils.isEmpty(pwd2)) {
            result.setMessageCode(Result.FAIL);
            result.setMessage("重复密码不能为空");
            logger.info("=====================>重复密码不能为空！  params=" + params);
            return JSONObject.fromObject(result).toString();
        }

        if (!pwd2.equals(pwd1)) {
            result.setMessageCode(Result.FAIL);
            result.setMessage("二次密码不一致");
            logger.info("=====================>二次密码不一致！  params=" + params);
            return JSONObject.fromObject(result).toString();
        }

        if (result.SUCCESS == result.getMessageCode()) {

            // 根据用户登录名获取到用户对象
            final LoginUserDto loginUserDto = this.userService.getUserByMobileNum(mobile);

            final String inputPwd = this.passwordEncoder.encodePassword(oldPwd, null);

            if (loginUserDto == null) {
                result.setMessageCode(Result.FAIL);
                result.setMessage("手机号不存在");
                logger.info("=====================>手机号不存在！");
                return JSONObject.fromObject(result).toString();
            }

            if (!inputPwd.equals(loginUserDto.getPassword())) {
                result.setMessageCode(Result.FAIL);
                result.setMessage("原密码输入错误！");
                logger.info("=====================>原密码输入错误！  params=" + params);
                return JSONObject.fromObject(result).toString();
            }

            try {
                loginUserDto.setPassword(this.passwordEncoder.encodePassword(pwd1, null));
                this.userService.profileUpdateUser(loginUserDto);
                result.setMessageCode(Result.SUCCESS);
                result.setMessage("密码修改成功");
                logger.info("=====================>密码修改成功！");
                logger.info(successInf + " " + JSONObject.fromObject(result).toString());
                return JSONObject.fromObject(result).toString();
            } catch (final Exception e) {
                result.setMessageCode(Result.FAIL);
                result.setMessage(e.getMessage());
                logger.info("=====================>密码修改失败！异常：" + e.getMessage());
            }

        }

        return JSONObject.fromObject(result).toString();
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#feedBack(java.lang.String)
     */

    @Override
    @Transactional
    public String feedBack(final String params, final HttpSession session) {
        logger.info("=====================>意见反馈开始  params=" + params);
        final String successInf = "=====================>意见反馈成功！";
        final Map paramsMap = JsonUtils.stringToObject(params);
        Result result = new Result();
        final String mobile = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");
        final String content = paramsMap.get("content") == null ? "" : (String) paramsMap.get("content");
        final String verifyCode = paramsMap.get("verifyCode") == null ? "" : (String) paramsMap.get("verifyCode");

        result = checkMobile(mobile);
        if (result.getMessageCode() == Result.FAIL) {
            return JSONObject.fromObject(result).toString();
        }

        result = this.canSubmit(verifyCode, session);
        if (result.getMessageCode() == 0) {
            logger.info("=====================>意见反馈失败  params=" + params + result.getMessage());
            return JSONObject.fromObject(result).toString();
        }

        if (StringUtils.isEmpty(content)) {
            result.setMessageCode(Result.FAIL);
            result.setMessage("请输入意见反馈！");
            logger.info("=====================>意见反馈失败  params=" + params);
            return JSONObject.fromObject(result).toString();
        }

        final LjNoticeDto noticeDto = new LjNoticeDto();
        noticeDto.setTheContent(content);
        noticeDto.setCreaterId(mobile);
        noticeDto.setType(4);// 意见反馈
        noticeDto.setUpdId(mobile);
        try {
            final Long id = this.ljNoticeService.insert(noticeDto);
            result.setMessageCode(Result.SUCCESS);
            result.setMessage("意见反馈成功！");
            result.setResultObject(id);
            logger.info(successInf + " " + JSONObject.fromObject(result).toString());
            return JSONObject.fromObject(result).toString();
        } catch (final DuplicateKeyException e) {
            e.printStackTrace();
            result.setMessageCode(Result.FAIL);
            result.setMessage(e.getMessage());
            logger.info("=====================>意见反馈失败！异常：" + e.getMessage() + "  params=" + params);
            return JSONObject.fromObject(result).toString();
        }

    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#aboutUs(java.lang.String)
     */

    @Override
    public String aboutUs(final String params) {

        logger.info("=====================>意见反馈开始  params=" + params);
        final String successInf = "=====================>关于我们成功！";
        final String failInf = "=====================>关于我们失败！";
        final Map paramsMap = JsonUtils.stringToObject(params);
        final Result result = new Result();

        final LjNoticeDto condtion = new LjNoticeDto();
        condtion.setType(5);
        condtion.setStatus(1);
        final List<LjNoticeDto> dtoList = this.ljNoticeService.getLjNoticeList(condtion);
        if (CollectionUtils.isNotEmpty(dtoList)) {
            result.setMessageCode(Result.SUCCESS);
            result.setMessage("获取关于我们成功！");
            result.setResultObject(dtoList.get(0).getTheContent());
            logger.info(successInf + " " + JSONObject.fromObject(result).toString());
            return JSONObject.fromObject(result).toString();
        }
        result.setMessageCode(Result.FAIL);
        result.setMessage("获取关于我们失败！后台无数据，请联系系统管理员增加！");
        logger.info(failInf);
        return JSONObject.fromObject(result).toString();
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#editUserInfo(java.lang.String)
     */

    @Override
    @Transactional
    public String editUserInfo(final String aParams) {

        logger.info("=====================>修改用户信息  params=" + aParams);
        final Map paramsMap = JsonUtils.stringToObject(aParams);
        Result result = new Result();

        final String mobile = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");
        final String name = paramsMap.get("name") == null ? "" : (String) paramsMap.get("name");
        final String nickName = paramsMap.get("nickName") == null ? "" : (String) paramsMap.get("nickName");
        final String image = paramsMap.get("image") instanceof JSONNull ? "" : (String) paramsMap.get("image");
        final String sex = paramsMap.get("sex") instanceof JSONNull ? "" : (String) paramsMap.get("sex");
        final String birthday = paramsMap.get("birthday") instanceof JSONNull ? "" : (String) paramsMap.get("birthday");

        result = checkMobile(mobile);
        if (result.getMessageCode() == Result.FAIL) {
            return JSONObject.fromObject(result).toString();
        }

        final JSONArray buildings = (JSONArray) paramsMap.get("building");
        // final JSONArray parks = (JSONArray) paramsMap.get("parks");
        if (buildings != null) {
            for (int i = 0; i < buildings.size(); i++) {
                final String buildingNum = buildings.getJSONObject(i).getString("buildingNum");
                final String roomNum = buildings.getJSONObject(i).getString("roomCode");
                final String roomCode = "MLY-" + buildingNum + "-" + roomNum;
                final LjUserBuilding room = new LjUserBuilding();
                room.setUserId(mobile);
                final LjRoomInfoDto condition = new LjRoomInfoDto();
                condition.setCode(roomCode);

                /*
                 * final boolean isHouseExisted = this.ljAppLogic.isHouseExisted(roomCode);
                 * if (!isHouseExisted) {
                 * result.setMessageCode(Result.FAIL);
                 * result.setMessage("修改用户信息失败，单元编码" + roomCode + "不存在！");
                 * logger.info("====》修改用户信息失败，单元编码" + roomCode + "不存在！");
                 * return JSONObject.fromObject(result).toString();
                 * }
                 */

                // final List<LjRoomInfoDto> list = this.ljRoomInfoService.getRoomInfoList(condition);
                // if (list != null && list.size() == 0) {
                // result.setMessageCode(Result.FAIL);
                // result.setMessage("修改用户信息失败，单元编码" + roomCode + "不存在！");
                // logger.info("====》修改用户信息失败，单元编码" + roomCode + "不存在！");
                // return JSONObject.fromObject(result).toString();
                // }
                for (int j = i + 1; j < buildings.size(); j++) {
                    final String buildingNum2 = buildings.getJSONObject(j).getString("buildingNum");
                    final String roomNum2 = buildings.getJSONObject(j).getString("roomCode");
                    final String roomCode2 = "MLY-" + buildingNum2 + "-" + roomNum2;
                    if (roomCode2.equalsIgnoreCase(roomCode)) {
                        result.setMessageCode(Result.FAIL);
                        result.setMessage("单元编码" + roomCode + "重复绑定！");
                        logger.info("====》修改用户信息失败，单元编码" + roomCode + "重复绑定！");
                        return JSONObject.fromObject(result).toString();
                    }
                }
            }
            this.ljUserBuildingService.delUserRoom(mobile, "");
            for (int i = 0; i < buildings.size(); i++) {
                final String buildingNum = buildings.getJSONObject(i).getString("buildingNum");
                final String roomNum = buildings.getJSONObject(i).getString("roomCode");
                final String roomCode = "MLY-" + buildingNum + "-" + roomNum;
                final LjUserBuilding room = new LjUserBuilding();
                room.setUserId(mobile);
                final LjRoomInfoDto condition = new LjRoomInfoDto();
                condition.setCode(roomCode);
                final List<LjRoomInfoDto> list = this.ljRoomInfoService.getRoomInfoList(condition);
                if (list != null && list.size() > 0) {
                    room.setRoomInfoId(list.get(0).getId().toString());
                    this.ljUserBuildingService.insertUserRoom(room);
                } else {
                    result.setMessageCode(Result.FAIL);
                    result.setMessage("修改用户信息失败，单元编码" + roomCode + "不存在！");
                    logger.info("=====================修改用户信息失败！ 修改用户信息失败，单元编码" + roomCode + "不存在！");
                    return JSONObject.fromObject(result).toString();
                }
            }
        }
        final LoginUser user = this.loginUserDao.getById(mobile);

        if (user != null) {

            user.setSex(sex);
            if (StringUtils.isNotEmpty(nickName)) {
                user.setNickName(nickName);
            }

            if (StringUtils.isNotEmpty(name)) {
                user.setName(name);
            }

            if (StringUtils.isNotEmpty(image)) {
                user.setImageName(image);
            }
            this.loginUserDao.update(user);
        }
        result.setMessageCode(Result.SUCCESS);
        result.setMessage("修改用户信息成功！");
        logger.info("=====================修改用户信息成功！ " + JSONObject.fromObject(result).toString());
        return JSONObject.fromObject(result).toString();
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#repairOrCpln(java.lang.String)
     */

    @Override
    @Transactional
    public String repairOrCpln(final String aParams, final HttpSession session) {
        Result result = new Result();
        try {

            logger.info("=====================>报修，投诉开始  params=" + aParams);
            final String successInf = "=====================>报修或投诉！";
            final Map paramsMap = JsonUtils.stringToObject(aParams);

            final String mobile = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");
            final String tel = paramsMap.get("tel") == null ? "" : (String) paramsMap.get("tel");
            final String content = paramsMap.get("content") == null ? "" : (String) paramsMap.get("content");
            final String repairObject =
                    paramsMap.get("repairObject") == null ? "" : (String) paramsMap.get("repairObject");
            final String roomCode = paramsMap.get("roomCode") == null ? "" : (String) paramsMap.get("roomCode");
            final String title = paramsMap.get("title") == null ? "" : (String) paramsMap.get("title");
            final String contacterTel =
                    paramsMap.get("contacterTel") == null ? "" : (String) paramsMap.get("contacterTel");
            final String contacter = paramsMap.get("nickName") == null ? "" : (String) paramsMap.get("nickName");
            final String type = paramsMap.get("type") == null ? "0" : (String) paramsMap.get("type");
            final String serviceTime =
                    paramsMap.get("serviceTime") == null ? "" : (String) paramsMap.get("serviceTime");

            final JSONArray images = (JSONArray) paramsMap.get("images");

            result = checkMobile(mobile);
            if (result.getMessageCode() == Result.FAIL) {
                return JSONObject.fromObject(result).toString();
            }

            final String verifyCode = paramsMap.get("verifyCode") == null ? "" : (String) paramsMap.get("verifyCode");
            result = this.canSubmit(verifyCode, session);
            if (result.getMessageCode() == 0) {
                logger.info("=====================>关闭我的报修或投诉 失败  params=" + aParams + result.getMessage());
                return JSONObject.fromObject(result).toString();
            }

            final LjRepairCplnDto cplDto = new LjRepairCplnDto();

            cplDto.setReporterId(mobile);
            cplDto.setReportTime(new Date());
            cplDto.setTitle(title);
            cplDto.setTheContent(content);
            cplDto.setRoomCode(roomCode);
            cplDto.setType(Integer.valueOf(type));
            cplDto.setRepairItem(repairObject);
            cplDto.setContacter(contacter);
            cplDto.setContacterTel(contacterTel);
            cplDto.setStatus(0);
            cplDto.setServiceTime(serviceTime);

            if (images != null) {
                for (int i = 0; i < images.size(); i++) {
                    final JSONObject names = images.getJSONObject(i);
                    if (names != null) {
                        final String name = names.get("name").toString();
                        if (i == 0) {
                            cplDto.setImage1(name);
                        }
                        if (i == 1) {
                            cplDto.setImage2(name);
                        }
                        if (i == 2) {
                            cplDto.setImage3(name);
                        }
                        if (i == 3) {
                            cplDto.setImage4(name);
                        }
                        if (i == 4) {
                            cplDto.setImage5(name);
                        }
                    }
                }
            }
            final Long id = this.ljRepairCplnService.insert(cplDto);

            if (id > 0) {
                final MailSender sender = new MailSender();

                String mailContent = "";
                String subject = "";
                final String email = "mlybaoxiu@163.com";

                if (cplDto.getType().intValue() == 0) {
                    subject = "亲~有新的报修了！";
                    mailContent += "报修地址：" + cplDto.getRoomCode();
                    mailContent += "报修人手机：" + mobile;
                    mailContent += "联系人：" + cplDto.getContacter();
                    mailContent += "联系人电话：" + cplDto.getContacterTel();
                    mailContent += "上门时间：" + cplDto.getServiceTime();
                    mailContent += "报修物品：" + cplDto.getRepairItem();
                    mailContent += "报修原因：" + cplDto.getTheContent();
                } else {
                    subject = "亲~有新的投诉了！";
                    mailContent += "投诉人房屋编号：" + cplDto.getRoomCode();
                    mailContent += "投诉人手机：" + mobile;
                    mailContent += "投诉人内容：" + cplDto.getTheContent();
                }

                // sender.send(email, subject, mailContent);

                result.setMessage("报修或投诉成功！");
                result.setMessageCode(Result.SUCCESS);
                result.setResultObject(id);
                logger.info("报修或投诉成功！" + JSONObject.fromObject(result).toString());
                return JSONObject.fromObject(result).toString();
            } else {
                result.setMessage("报修或投诉成功！");
                result.setMessageCode(Result.SUCCESS);
                result.setResultObject(id);
                logger.info("报修或投诉成功！" + JSONObject.fromObject(result).toString());
                return JSONObject.fromObject(result).toString();
            }

        } catch (final Exception e) {
            e.printStackTrace();
            result.setMessage("报修或投诉失败！");
            result.setMessageCode(Result.FAIL);
            logger.info("报修或投诉成功！");
            return JSONObject.fromObject(result).toString();
        }
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#getUserInfo(java.lang.String)
     */

    @Override
    public String getUserInfo(final String aParams) {
        logger.info("=====================>取得用户信息  params=" + aParams);
        final Map paramsMap = JsonUtils.stringToObject(aParams);
        Result result = new Result();

        final String mobile = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");

        result = checkMobile(mobile);
        if (result.getMessageCode() == Result.FAIL) {
            return JSONObject.fromObject(result).toString();
        }

        // 根据用户登录名获取到用户对象
        final LoginUserDto loginUserDto = this.userService.getUserByMobileNum(mobile);

        if (loginUserDto != null) {// 手机号密码都对

            final AppLoginUser loginUser = new AppLoginUser();
            BeanUtils.copyAllNullableProperties(loginUserDto, loginUser);
            loginUser.setHeadimgurl(loginUserDto.getImageName());
            loginUser.setNickName(loginUserDto.getNickName());
            System.out.println("//////////////////////头像" + loginUserDto.getImageName() + "   ================昵称"
                    + loginUserDto.getNickName() + " /////===");
            final LjMsgTipDto condition = new LjMsgTipDto();
            condition.setUserId(mobile);
            condition.setStatus(0);
            final List list = this.ljMsgTipService.getMsgTipList(condition);

            if (list != null) {
                loginUser.setNewMsgCnt(list.size());
            }

            final List<RoomInfo> roomCodes = this.ljUserBuildingService.getUserRoom(mobile);
            loginUser.setRooms(roomCodes);

            if (!canAddScore(mobile, AppConfig.ACTION_SIGN)) {
                loginUser.setHasAsigned("1");
            }

            final LjScoreHistoryDto dto = new LjScoreHistoryDto();
            dto.setUserId(mobile);
            final int days = this.ljScoreHistoryService.getScoreHistoryCount(dto);
            loginUser.setAsignDays(String.valueOf(days));

            final Date now = new Date();
            final String nowStr = DateFormatUtils.format(now, "yyyy-MM-dd");

            int todayScore = 0;
            final List<LjScoreHistoryDto> scoreList = this.ljScoreHistoryService.getScoreHistoryList(dto);
            for (final LjScoreHistoryDto aDto : scoreList) {
                final Date date = aDto.getCreateTime();
                final String dateStr = DateFormatUtils.format(date, "yyyy-MM-dd");
                if (nowStr.equalsIgnoreCase(dateStr)) {
                    todayScore += aDto.getScore();
                }
            }
            loginUser.setTodayScore(todayScore);

            // 取得用户信息成功
            result.setMessageCode(Result.SUCCESS);
            result.setMessage("取得用户信息成功");
            result.setResultObject(loginUser);

            logger.info("=====================>取得用户信息成功" + JSONObject.fromObject(result).toString());
            this.jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
            return JSONObject.fromObject(result).toString();
        }

        result.setMessageCode(Result.FAIL);
        result.setMessage("取得用户信息失败！");
        logger.info("=====================取得用户信息失败！");
        return JSONObject.fromObject(result).toString();
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#myScoreHistory(java.lang.String)
     */

    @Override
    public String myScoreHistory(final String aParams) {
        logger.info("=====================>取得用户积分历史 params=" + aParams);
        final Map paramsMap = JsonUtils.stringToObject(aParams);
        Result result = new Result();
        final String mobile = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");
        final String type = paramsMap.get("type") instanceof JSONNull ? "" : (String) paramsMap.get("type");
        final List<ScoreHistory> list = new ArrayList<ScoreHistory>();

        final String gotoPage = paramsMap.get("gotoPage") == null ? "1" : paramsMap.get("gotoPage").toString();
        final String pageSize = paramsMap.get("pageSize") == null ? "9999" : paramsMap.get("pageSize").toString();

        result = checkMobile(mobile);
        if (result.getMessageCode() == Result.FAIL) {
            return JSONObject.fromObject(result).toString();
        }

        final LjScoreHistoryDto dto = new LjScoreHistoryDto();
        dto.setUserId(mobile);
        dto.setGotoPage(Integer.valueOf(gotoPage));
        dto.setPageSize(Integer.valueOf(pageSize));
        dto.setGotFrom(type);
        final List<LjScoreHistoryDto> dtoList = this.ljScoreHistoryService.getScoreHistoryList(dto);

        final List<ScoreHistory> appScoreList = new ArrayList<ScoreHistory>();

        long todayScore = 0;
        final long totalScore = this.loginUserDao.getById(mobile).getScore();

        final Map map = new HashMap();
        final ScoreHistory appScoreHistory = new ScoreHistory();
        for (final LjScoreHistoryDto adto : dtoList) {
            final ScoreHistory entity = new ScoreHistory();
            entity.setGotFrom(adto.getGotFrom());
            entity.setScore(adto.getScore().toString());
            entity.setTime(DateFormatUtils.format(adto.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
            appScoreList.add(entity);

            if (map.get(adto.getGotFrom()) == null) {
                map.put(adto.getGotFrom(), adto.getScore());
            } else {
                Long score = (Long) map.get(adto.getGotFrom());
                score += adto.getScore();
                map.put(adto.getGotFrom(), score);
            }

            final String createTime = DateFormatUtils.format(adto.getCreateTime(), "yyyy-MM-dd");
            final String today = DateFormatUtils.format(new Date(), "yyyy-MM-dd");

            if (today.equals(createTime)) {
                todayScore += adto.getScore();
            }

        }

        appScoreHistory.setOtherScore(map);
        appScoreHistory.setTodayScore(String.valueOf(todayScore));
        appScoreHistory.setTotalScore(String.valueOf(totalScore));

        result.setMessageCode(Result.SUCCESS);
        result.setMessage("取得用户积分历史成功！");

        if (StringUtils.isNotEmpty(type)) {
            result.setResultObject(appScoreList);
        } else {
            result.setResultObject(appScoreHistory);
        }

        logger.info("=====================取得用户积分历史成功！" + JSONObject.fromObject(result).toString());
        return JSONObject.fromObject(result).toString();
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#pay(java.lang.String)
     */

    @Override
    @Transactional
    public String pay(final String aParams, final HttpServletRequest request, final HttpServletResponse response) {
        logger.info("=====================>支付开始  Params=" + aParams);
        Result result = new Result();
        final Map paramsMap = JsonUtils.stringToObject(aParams);
        final String mobile = paramsMap.get("mobile") instanceof JSONNull ? "" : (String) paramsMap.get("mobile");
        final String type = paramsMap.get("type") instanceof JSONNull ? "" : (String) paramsMap.get("type");

        final String openId = paramsMap.get("openid") instanceof JSONNull ? "" : (String) paramsMap.get("openid");

        final String payType = paramsMap.get("payType") instanceof JSONNull ? "" : (String) paramsMap.get("payType");
        final String months = paramsMap.get("months") instanceof JSONNull ? "" : (String) paramsMap.get("months");
        final String cuponId = paramsMap.get("cuponId") instanceof JSONNull ? "" : (String) paramsMap.get("cuponId");
        final String roomCode = paramsMap.get("roomCode") instanceof JSONNull ? "" : (String)
                paramsMap.get("roomCode");
        final String total = paramsMap.get("total") instanceof JSONNull ? "" : (String) paramsMap.get("total");
        final Integer score = paramsMap.get("score") instanceof JSONNull ? 0 : (Integer) paramsMap.get("score");

        String from = "";
        if (StringUtils.isNotEmpty(openId)) {
            from = "webchat";
        }

        result = checkMobile(mobile);
        if (result.getMessageCode() == Result.FAIL) {
            return JSONObject.fromObject(result).toString();
        }

        if (StringUtils.isNotEmpty(type)) {
            if ("0".equals(type)) {
                if (StringUtils.isEmpty(roomCode)) {
                    result.setMessageCode(Result.FAIL);
                    result.setMessage("支付失败，房屋号为空！");

                }
            }

        }
        final LjRoomInfoDto roomInfo = this.ljRoomInfoService.getRoomInfo(roomCode);

        final LjFeeHistoryDto feeHistory = new LjFeeHistoryDto();
        feeHistory.setUserId(mobile);
        feeHistory.setType(Integer.parseInt(type));
        feeHistory.setRoomCode(roomCode);

        if (StringUtils.isNotEmpty(months)) {
            final Date date = DateFormatUtils.stringToDate(roomInfo.getLastPeriod(), "yyyyMM");
            final Date date2 = DateFormatUtils.addMonth(date, Integer.valueOf(months));
            final String lastPeriod = DateFormatUtils.format(date2, "yyyyMM");
            feeHistory.setLastPeriod(Integer.valueOf(lastPeriod));
        }

        if (StringUtils.isNotEmpty(payType)) {
            feeHistory.setPayType(Integer.valueOf(payType));
        }
        feeHistory.setPayDate(new Date());
        feeHistory.setUserId(mobile);
        feeHistory.setFeeMonth(Integer.valueOf(months));
        feeHistory.setStatus(1);

        double price = 0;
        if (feeHistory.getType() == 0) {
            price = roomInfo.getTotalPrice();
            final double totalMoney = price * Integer.valueOf(months);
            final BigDecimal d = new BigDecimal(totalMoney);
            d.setScale(2, BigDecimal.ROUND_HALF_UP);
            feeHistory.setFee(String.valueOf(d.doubleValue()));
        }
        else {
            final BigDecimal d = new BigDecimal(total);
            d.setScale(2, BigDecimal.ROUND_HALF_UP);
            feeHistory.setFee(String.valueOf(d.doubleValue()));
        }

        if (roomInfo != null) {
            feeHistory.setRoomId(roomInfo.getId());
        }

        if (StringUtils.isNotEmpty(cuponId)) {
            feeHistory.setCuponId(Long.valueOf(cuponId));
        }
        if (score != null && score.longValue() != 0) {
            feeHistory.setUsedScore(Long.valueOf(score));
        }

        return getPrepayId(mobile, feeHistory, roomCode,
                type, from, openId, request, response, result);
    }

    /**
     * 微信下单。
     * 
     * @param mobile
     * @param feeHistory
     * @param roomCode
     * @param type
     * @param request
     * @param response
     * @param result
     * @return String
     */
    @Transactional
    private String getPrepayId(final String mobile, final LjFeeHistoryDto feeHistory, final String roomCode,
            final String type,
            final String from, final String openid,
            final HttpServletRequest request,
            final HttpServletResponse response,
            final Result result) {
        // ---------------生成订单号 开始------------------------
        // 随机数
        final String strRandom = TenpayUtil.buildRandom(6) + "";

        // 订单号，此处用时间加随机数生成，商户根据自己情况调整，只要保持全局唯一就行
        String out_trade_no = DateFormatUtils.formatDate(new Date(), "yyyyMMddHHmmssSS") + strRandom;

        if (feeHistory.getId() != null && StringUtils.isNotEmpty(feeHistory.getPayNo())) {
            out_trade_no = feeHistory.getPayNo();
        }

        // ---------------生成订单号 结束------------------------

        // 获取prepayid的请求类
        final PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);

        // 返回客户端支付参数的请求类
        final ClientRequestHandler clientHandler = new ClientRequestHandler(request, response);

        String prepayId = "";

        final String noncestr = WXUtil.getNonceStr();
        final String timestamp = WXUtil.getTimeStamp();
        logger.info("=====================>noncestr:" + noncestr);

        // 设置获取prepayid支付参数

        final String[] code = roomCode.split("-");
        final String info = "美丽苑" + code[1] + "号" + code[2] + "室 ";
        final String lastPeriod = feeHistory.getLastPeriod() == null ? "" : feeHistory.getLastPeriod().toString();
        final long total_fee = (long) (Double.valueOf(feeHistory.getFee()) * 100);
        // total_fee = 1;
        final String ip = getIpAddress(request);
        String sign = "";
        if ("webchat".equalsIgnoreCase(from)) {// 微信公众号支付

            prepayReqHandler.setParameter("appid", AppConfig.ID_WEBCHAT);
            prepayReqHandler.setParameter("mch_id", AppConfig.MERCHANT_ID_WEBCHAT);
            prepayReqHandler.setParameter("trade_type", "JSAPI");
            prepayReqHandler.setParameter("nonce_str", noncestr);
            prepayReqHandler.setParameter("device_info", "web");
            prepayReqHandler.setParameter("sign_type", "MD5");
            prepayReqHandler.setParameter("body", info + lastPeriod + " 物业管理费"); // 商品描述
            prepayReqHandler.setParameter("detail", info + lastPeriod + " 物业管理费"); // 商品详情
            prepayReqHandler.setParameter("out_trade_no", out_trade_no);// 商户订单号
            prepayReqHandler.setParameter("fee_type", "CNY");// 货币类型
            prepayReqHandler.setParameter("total_fee", String.valueOf(total_fee));// 缴费金额
            prepayReqHandler.setParameter("spbill_create_ip", ip);
            prepayReqHandler.setParameter("notify_url", AppConfig.NOTIFY_URL);
            prepayReqHandler.setParameter("openid", openid);
            prepayReqHandler.setGateUrl(AppConfig.GATEURL);

            prepayReqHandler.setKey(AppConfig.API_KEY_WEBCHAT);
            sign = prepayReqHandler.wxCreateSHA1Sign();
            prepayReqHandler.setParameter("sign", sign);

        } else { // APP支付
            prepayReqHandler.setParameter("appid", AppConfig.ID_APP);
            prepayReqHandler.setParameter("mch_id", AppConfig.MERCHANT_ID_APP);
            prepayReqHandler.setParameter("device_info", "web");
            prepayReqHandler.setParameter("nonce_str", noncestr);
            prepayReqHandler.setParameter("body", info + lastPeriod + " 物业管理费"); // 商品描述
            prepayReqHandler.setParameter("detail", info + lastPeriod + " 物业管理费"); // 商品详情
            prepayReqHandler.setParameter("body", "购物支付"); // 商品描述
            prepayReqHandler.setParameter("detail", "购物支付"); // 商品详情
            prepayReqHandler.setParameter("out_trade_no", out_trade_no);// 商户订单号
            prepayReqHandler.setParameter("fee_type", "CNY");// 货币类型
            prepayReqHandler.setParameter("total_fee", String.valueOf(total_fee));// 缴费金额
            prepayReqHandler.setParameter("spbill_create_ip", ip);
            prepayReqHandler.setParameter("notify_url", AppConfig.NOTIFY_URL);
            prepayReqHandler.setParameter("trade_type", "APP");
            prepayReqHandler.setGateUrl(AppConfig.GATEURL);

            // 生成获取预支付签名
            prepayReqHandler.setKey(AppConfig.API_KEY_APP);
            sign = prepayReqHandler.createSHA1Sign();
            prepayReqHandler.setParameter("sign", sign);
        }

        final String resContent = prepayReqHandler.sendPrepay(from);
        logger.info("=====================>resContent: " + resContent);

        final ClientResponseHandler handler = new ClientResponseHandler();
        if (StringUtils.isNotEmpty(resContent)) {
            try {
                handler.setContent(resContent);
            } catch (final Exception e) {
                e.printStackTrace();
                result.setMessage("支付失败!" + e.getMessage());
                result.setResultObject(null);
                logger.info("=====================>支付失败!" + e.getMessage());
                return JSONObject.fromObject(result).toString();
            }
            // 获取prepayId
            prepayId = handler.getParameter("prepay_id");
        }

        logger.info("=====================>prepayId:" + prepayId);

        // 吐回给客户端的参数
        if (StringUtils.isNotEmpty(prepayId)) {

            // 输出参数列表
            if ("webchat".equalsIgnoreCase(from)) { // 微信公众号

                clientHandler.setParameter("appid", AppConfig.ID_WEBCHAT);
                clientHandler.setParameter("appkey", AppConfig.API_KEY_WEBCHAT);
                clientHandler.setParameter("noncestr", noncestr);
                clientHandler.setParameter("package", "Sign=WXPay");
                clientHandler.setParameter("partnerid", AppConfig.MERCHANT_ID_WEBCHAT);
                clientHandler.setParameter("prepayid", prepayId);
                clientHandler.setParameter("timestamp", timestamp);

                final SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
                parameters.put("appId", AppConfig.ID_WEBCHAT);
                parameters.put("timeStamp", timestamp);
                parameters.put("signType", "MD5");
                parameters.put("nonceStr", noncestr);
                parameters.put("package", "prepay_id=" + prepayId);
                sign = Signature.createSign("UTF-8", parameters);

                clientHandler.setParameter("sign", sign);
            } else { // APP
                clientHandler.setParameter("NonceStr", noncestr);// 随机字符串1111111111
                clientHandler.setParameter("PackageValue", "Sign=WXPay");// packageValue11111111111
                clientHandler.setParameter("PrepayId", prepayId);// 预付ID11111111111
                clientHandler.setParameter("TimeStamp", timestamp);// 时间戳
                clientHandler.setParameter("OrderId", out_trade_no);// 订单编号
                clientHandler.setParameter("opt", "payNotify");// 回调函数参数
                clientHandler.setParameter("secretKey", AppConfig.SECRET_KEY);// 回调函数参数
                clientHandler.setParameter("mobile", mobile);// 回调函数参数
            }

            try {
                feeHistory.setPayNo(out_trade_no);
                feeHistory.setStatus(0);
                feeHistory.setPrepayId(prepayId);

                if (feeHistory.getId() == null || feeHistory.getId() == 0) {
                    this.ljFeeHistoryService.insert(feeHistory);

                } else {
                    this.ljFeeHistoryService.update(feeHistory);
                }
            } catch (final DuplicateKeyException e) {
                e.printStackTrace();
                result.setMessage("支付失败!" + e.getMessage());
                result.setResultObject(null);
                logger.info("=====================>支付失败!" + e.getMessage());
                return JSONObject.fromObject(result).toString();
            }

            String str = "";
            try {
                str = "wxpay://"
                        + URLEncoder.encode(JSONObject.fromObject(clientHandler.getAllParameters()).toString(),
                                "utf-8");

                if ("webchat".equalsIgnoreCase(from)) {
                    str = JSONObject.fromObject(clientHandler.getAllParameters()).toString();
                }

            } catch (final UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            result.setResultObject(str);
            logger.info("=====================统一下单获取prepayId成功!! :" + JSONObject.fromObject(result).toString());

            return JSONObject.fromObject(result).toString();
        }
        result.setMessageCode(Result.FAIL);
        result.setMessage("统一下单获取prepayId失败! " + handler.getParameter("return_msg"));
        result.setResultObject(null);
        logger.info("=====================>统一下单获取prepayId失败!! params:" + handler.getParameter("return_msg"));
        return JSONObject.fromObject(result).toString();

    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#payNotifyUrl(java.lang.String)
     */

    @Override
    public String payNotify(final HttpServletRequest request,
            final HttpServletResponse response) {
        logger.info("**************开始执行支付回调函数payNotify ********:");

        final Result result = new Result();
        try {

            final ClientResponseHandler handler = new ClientResponseHandler();

            final ServletInputStream inputStream = request.getInputStream();
            final Scanner scanner = new Scanner(inputStream, "UTF-8");
            final String text = scanner.useDelimiter("\\A").next();
            scanner.close();
            handler.setContent(text);

            // 商户号
            final String partner = AppConfig.MERCHANT_ID_APP;

            // 密钥
            final String key = AppConfig.API_KEY_APP;

            // 创建支付应答对象
            final ResponseHandler resHandler = new ResponseHandler(request, response);
            resHandler.setParameter(handler.getAllParameters());
            resHandler.setKey(key);

            // 支付成功
            if ("SUCCESS".equals(resHandler.getParameter("result_code"))
                    && "SUCCESS".equals(resHandler.getParameter("return_code"))) {

                final String outTradeNo = resHandler.getParameter("out_trade_no");
                final LjFeeHistoryDto condtion = new LjFeeHistoryDto();
                condtion.setPayNo(outTradeNo);

                final List<LjFeeHistoryDto> dtoList = this.ljFeeHistoryService.getFeeHistoryList(condtion);
                if (CollectionUtils.isNotEmpty(dtoList)) {
                    final LjFeeHistoryDto dto = dtoList.get(0);
                    // if (dto.getType() == 2 && dto.getStatus().intValue() == 0) {// 扣除商品库存
                    // if (StringUtils.isNotEmpty(dto.getGoodsIds())) {
                    // final String[] ids = dto.getGoodsIds().split(",");
                    // for (final String idCnt : ids) {
                    // final String[] id = idCnt.split("_");
                    // final LjGoodsDto goodsDto = this.ljGoodsService.getById(Long.valueOf(id[0]));
                    // goodsDto.setLeftCnt(goodsDto.getLeftCnt() - Long.valueOf(id[1]));
                    //
                    // this.ljGoodsService.update(goodsDto);
                    // }
                    // }
                    //
                    // }

                    // 更新最后缴费期数
                    final LjRoomInfoDto room = this.ljRoomInfoService.getById(dto.getRoomId());
                    room.setLastPeriod(dto.getLastPeriod().toString());
                    this.ljRoomInfoService.update(room);

                    dto.setStatus(1);
                    dto.setTransactionId(resHandler.getParameter("transaction_id"));
                    dto.setUpdTime(CommonUtils.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
                    this.ljFeeHistoryService.update(dto);
                }
                final String str = "<xml>"
                        + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>"
                        + "</xml>";
                return str;

            }
            else {
                logger.info("支付失败");
                result.setMessage("支付失败！" + text);
                result.setMessageCode(Result.FAIL);
            }
        } catch (final Exception e) {
            e.printStackTrace();
            logger.info("微信回调函数异常：" + e.getMessage());
            result.setMessage("微信回调函数异常：" + e.getMessage());
            result.setMessageCode(Result.FAIL);
        }

        logger.info(JSONObject.fromObject(result).toString());
        return JSONObject.fromObject(result).toString();
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#wxpayNotify(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */

    @Override
    public String wxpayNotify(final HttpServletRequest request, final HttpServletResponse response) {
        logger.info("**************开始执行支付回调函数payNotify ********:");

        final Result result = new Result();
        try {

            final ClientResponseHandler handler = new ClientResponseHandler();

            final ServletInputStream inputStream = request.getInputStream();
            final Scanner scanner = new Scanner(inputStream, "UTF-8");
            final String text = scanner.useDelimiter("\\A").next();
            scanner.close();
            handler.setContent(text);

            // 商户号
            final String partner = AppConfig.MERCHANT_ID_WEBCHAT;

            // 密钥
            final String key = AppConfig.API_KEY_WEBCHAT;

            // 创建支付应答对象
            final ResponseHandler resHandler = new ResponseHandler(request, response);
            resHandler.setParameter(handler.getAllParameters());
            resHandler.setKey(key);

            // 支付成功
            if ("SUCCESS".equals(resHandler.getParameter("result_code"))
                    && "SUCCESS".equals(resHandler.getParameter("return_code"))) {

                final String outTradeNo = resHandler.getParameter("out_trade_no");
                final LjFeeHistoryDto condtion = new LjFeeHistoryDto();
                condtion.setPayNo(outTradeNo);

                final List<LjFeeHistoryDto> dtoList = this.ljFeeHistoryService.getFeeHistoryList(condtion);
                if (CollectionUtils.isNotEmpty(dtoList)) {
                    final LjFeeHistoryDto dto = dtoList.get(0);

                    dto.setStatus(1);
                    dto.setTransactionId(resHandler.getParameter("transaction_id"));
                    dto.setUpdTime(CommonUtils.dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
                    this.ljFeeHistoryService.update(dto);
                }

                final String str = "<xml>"
                        + "<return_code><![CDATA[SUCCESS]]></return_code>"
                        + "<return_msg><![CDATA[OK]]></return_msg>"
                        + "</xml>";
                return str;

            }
            else {
                logger.info("支付失败");
                result.setMessage("支付失败！" + text);
                result.setMessageCode(Result.FAIL);
            }
        } catch (final Exception e) {
            e.printStackTrace();
            logger.info("微信回调函数异常：" + e.getMessage());
            result.setMessage("微信回调函数异常：" + e.getMessage());
            result.setMessageCode(Result.FAIL);
        }

        logger.info(JSONObject.fromObject(result).toString());
        return JSONObject.fromObject(result).toString();
    }

    private String getIpAddress(final HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    @Override
    public boolean canAddScore(final String mobile, final String actionName) {

        final LjScoreHistoryDto scoreHistoryDto = new LjScoreHistoryDto();
        scoreHistoryDto.setUserId(mobile);
        final List<LjScoreHistoryDto> list = this.ljScoreHistoryService.getScoreHistoryList(scoreHistoryDto);

        final Date now = new Date();
        final String nowStr = DateFormatUtils.format(now, "yyyy-MM-dd");
        for (final LjScoreHistoryDto dto : list) {
            final Date date = dto.getCreateTime();
            final String dateStr = DateFormatUtils.format(date, "yyyy-MM-dd");
            if (nowStr.equalsIgnoreCase(dateStr) && dto.getGotFrom().equals(actionName)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#sign(java.lang.String)
     */

    @Override
    @Transactional
    public String sign(final String aParams, final HttpSession session) {
        logger.info("=====================>签到  Params=" + aParams);
        Result result = new Result();

        try {

            final Map paramsMap = JsonUtils.stringToObject(aParams);
            final String mobile = paramsMap.get("mobile") == null ? "" : paramsMap.get("mobile").toString();

            result = checkMobile(mobile);
            if (result.getMessageCode() == Result.FAIL) {
                return JSONObject.fromObject(result).toString();
            }

            final Object sessionUser = session.getAttribute("LOGIN_USER");
            AppLoginUser appLoginUser = null;
            if (sessionUser != null) {
                appLoginUser = (AppLoginUser) sessionUser;
            }

            if (appLoginUser != null && !appLoginUser.getMobile().equalsIgnoreCase(mobile)) {
                result.setMessageCode(Result.FAIL);
                result.setMessage("不可替他人刷分！");
                logger.info("=====================签到失败，不可替他人刷分！");
                return JSONObject.fromObject(result).toString();
            }

            if (!canAddScore(mobile, AppConfig.ACTION_SIGN)) {
                result.setMessageCode(Result.FAIL);
                result.setMessage("你今天已经签过到了！");
                logger.info("=====================签到失败，你今天已经签过到了！！");
                return JSONObject.fromObject(result).toString();
            }

            final LjScoreHistoryDto dto = new LjScoreHistoryDto();
            dto.setUserId(mobile);
            final int days = this.ljScoreHistoryService.getScoreHistoryCount(dto);

            final int score = this.addScore(mobile, AppConfig.ACTION_SIGN, AppConfig.SCORE_SIGN);
            final String scoreStr =
                    "{\"total\":\"" + score + "\",\"add\":\"" + AppConfig.SCORE_SIGN + "\",\"days\":\"" + days + "\"}";
            result.setResultObject(scoreStr);

            logger.info("=====================签到成功！" + JSONObject.fromObject(result).toString());
            return JSONObject.fromObject(result).toString();

        } catch (final Exception e) {
            e.printStackTrace();
            result.setMessageCode(Result.FAIL);
            result.setMessage("签到失败！" + e.getMessage());
            return JSONObject.fromObject(result).toString();
        }
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#index()
     */

    @Override
    public String index() {

        final String params = "{\"pagesSize\":5,\"gotoPage\":1,\"type\":2}";
        final String noticeList = this.getNoticeOrActiveList(params);

        final Result result = new Result();
        // 登录成功
        result.setMessageCode(Result.SUCCESS);
        result.setMessage("获取首页信息成功");
        result.setResultObject(noticeList);
        // logger.info("=====================>获取首页信息成功" + JSONObject.fromObject(result).toString());
        return JSONObject.fromObject(result).toString();
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#upload(java.lang.String)
     */

    @Override
    public String upload(final String aParams, final MultiPartRequestWrapper multipartRequest) {
        logger.info("=====================>开始上传  params=" + aParams);
        final Map paramsMap = JsonUtils.stringToObject(aParams);
        final Result result = new Result();
        final File[] files = multipartRequest.getFiles("images");
        final String[] fileNames = multipartRequest.getFileNames("images");

        try {
            final List<String> imageNameList = UploadUtils.uploadImages(files, fileNames, "repairOrCpln");
            result.setMessageCode(Result.SUCCESS);
            result.setMessage("上传成功成功！");

            if (imageNameList != null && imageNameList.size() > 0 && imageNameList.size() == 1) {
                result.setResultObject(imageNameList.get(0));
            } else {
                result.setResultObject(imageNameList);
            }
            logger.info("=====================上传成功！" + JSONObject.fromObject(result).toString());
        } catch (final FileNotFoundException e) {
            e.printStackTrace();
            result.setMessageCode(Result.FAIL);
            result.setMessage("上传失败！" + e.getMessage());
        } catch (final IOException e) {
            e.printStackTrace();
            result.setMessageCode(Result.FAIL);
            result.setMessage("上传失败！" + e.getMessage());
        }

        return JSONObject.fromObject(result).toString();
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#addComment(java.lang.String)
     */

    @Override
    @Transactional
    public String addComment(final String aParams) {

        logger.info("=====================>开始评论  params=" + aParams);
        final Map paramsMap = JsonUtils.stringToObject(aParams);
        Result result = new Result();

        final String id = paramsMap.get("id") == null ? "" : (String) paramsMap.get("id");
        final String type = paramsMap.get("type") == null ? "" : (String) paramsMap.get("type");
        final String mobile = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");
        final String content = paramsMap.get("content") == null ? "" : (String) paramsMap.get("content");

        result = checkMobile(mobile);
        if (result.getMessageCode() == Result.FAIL) {
            return JSONObject.fromObject(result).toString();
        }

        final LjActionCommentDto dto = new LjActionCommentDto();
        if (StringUtils.isNotEmpty(id)) {
            dto.setActionId(Long.valueOf(id));
        }

        if (StringUtils.isNotEmpty(content)) {
            dto.setTheContent(content);
        }

        if (StringUtils.isNotEmpty(mobile)) {
            dto.setCreateId(mobile);
        }

        if (StringUtils.isNotEmpty(type)) {
            dto.setType(Integer.valueOf(type));
        }

        dto.setLevelId(1L);
        dto.setStatus(1);
        try {
            final Long commentId = this.ljActionCommentService.insert(dto);

            result.setMessageCode(Result.SUCCESS);
            result.setMessage("评论成功！");
            result.setResultObject(commentId);
            if (StringUtils.isNotEmpty(type)) {
                if ("4".equals(type) || "5".equals(type) || "6".equals(type)) {
                    final LjMsgTipDto msgDto = new LjMsgTipDto();
                    if ("4".equals(type) || "5".equals(type)) {
                        final LjNoticeDto noticeDto = this.ljNoticeService.getById(Long.valueOf(id));
                        msgDto.setUserId(noticeDto.getCreaterId());
                        msgDto.setTitle(noticeDto.getName());
                        msgDto.setTypeName(type);
                        msgDto.setCreateDate(new Date().toString());
                        msgDto.setParamId(id);
                        msgDto.setOpt("getNoticeOrActiveDetail");
                    } else {
                    }
                    this.ljMsgTipService.insert(msgDto);
                }
            }

        } catch (final Exception e) {
            e.printStackTrace();
            result.setMessageCode(Result.FAIL);
            result.setMessage("评论失败！" + e.getMessage());
        }
        logger.info("=====================>评论结束  params=" + JSONObject.fromObject(result).toString());
        return JSONObject.fromObject(result).toString();
    }

    /**
     * 清除客户端的cookie。
     * 
     * @param req
     * @param response
     */
    private void removeCookie(final HttpServletRequest req, final HttpServletResponse response) {

        logger.info("====>removeCookie!!!!====>removeCookie!!!!====>removeCookie!!!!====>removeCookie!!!!");

        final Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                final String cookieName = cookie.getName();
                if (AppConfig.MANAGER_MEMORY.equals(cookieName)) {
                    cookie = new Cookie(cookieName, null);
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
    }

    /**
     * 获取accessToken
     * 
     * @see com.youthen.lj.app.service.LjAppService#getAccessToken(java.lang.String)
     */

    @Override
    public Result getAccessToken(final String code, String from) {
        from = "webchat";
        String url = "";
        if ("webchat".equalsIgnoreCase(from)) {
            url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + AppConfig.ID_WEBCHAT + "&secret="
                    + AppConfig.SECRET_WEBCHAT + "&code="
                    + code + "&grant_type=authorization_code";
        } else {
            url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + AppConfig.ID_APP + "&secret="
                    + AppConfig.SECRET_APP + "&code="
                    + code + "&grant_type=authorization_code";
        }
        logger.info("====>URL==>" + url);

        final Result rs = new Result();
        try {
            final URL getUrl = new URL(url);
            final HttpURLConnection http = (HttpURLConnection) getUrl.openConnection();
            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);

            http.connect();
            final InputStream is = http.getInputStream();
            final int size = is.available();
            final byte[] b = new byte[size];
            is.read(b);

            final String message = new String(b, "UTF-8");

            final JSONObject json = JSONObject.fromObject(message);
            System.out.println("================message  " + message);
            final AccessToken at = new AccessToken();
            at.setOpenid(json.getString("openid"));
            at.setAccessToken(json.getString("access_token"));
            at.setRefreshToken(json.getString("refresh_token"));
            rs.setMessageCode(Result.SUCCESS);
            rs.setResultObject(at);
        } catch (final MalformedURLException e) {
            e.printStackTrace();
            rs.setMessageCode(Result.FAIL);
            rs.setResultObject(e.getMessage());
        } catch (final IOException e) {
            e.printStackTrace();
            rs.setMessageCode(Result.SUCCESS);
            rs.setResultObject(e.getMessage());
        }

        logger.info("====>Rs==>" + JsonUtils.objectToString(rs));
        return rs;
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#logout(java.lang.String, javax.servlet.http.HttpSession)
     */
    @Override
    public String logout(final String aParams, final HttpServletRequest req, final HttpServletResponse response,
            final HttpSession session) {
        logger.info("=====================>退出开始  params=" + aParams);
        Result result = new Result();
        final Map<String, String> paramsMap = JsonUtils.stringToObject(aParams);
        final String mobile = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");

        result = checkMobile(mobile);
        if (result.getMessageCode() == Result.FAIL) {
            return JSONObject.fromObject(result).toString();
        }

        removeCookie(req, response);

        result.setMessageCode(Result.SUCCESS);
        result.setMessage("退出成功!");
        result.setResultObject(null);
        session.removeAttribute("LOGIN_USER");
        session.removeAttribute("SUBMIT_VERIFY_CODE");
        session.removeAttribute("CREATE_VERIFY_CODE_TIME");
        session.invalidate();
        logger.info("=====================>退出成功!!");
        return JSONObject.fromObject(result).toString();

    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#myNewMessages(java.lang.String)
     */

    @Override
    @Transactional
    public String myNewMessages(final String aParams) {

        logger.info("=====================>查看我的新消息开始 params=" + aParams);
        Result result = new Result();
        final Map<String, String> paramsMap = JsonUtils.stringToObject(aParams);
        final String mobile = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");

        result = checkMobile(mobile);
        if (result.getMessageCode() == Result.FAIL) {
            return JSONObject.fromObject(result).toString();
        }

        final LjMsgTipDto condition = new LjMsgTipDto();
        condition.setUserId(mobile);
        final List<LjMsgTipDto> list = this.ljMsgTipService.getMsgTipList(condition);
        final List<NewMessage> msgList = new ArrayList<NewMessage>();
        for (final LjMsgTipDto dto : list) {
            final NewMessage msg = new NewMessage();
            msg.setTitle(dto.getTitle());
            msg.setOpt(dto.getOpt());
            msg.setType(dto.getTypeName());
            msg.setPostId(dto.getParamId());
            msgList.add(msg);
        }

        this.ljMsgTipService.delByUserId(mobile);

        result.setMessageCode(Result.SUCCESS);
        result.setMessage("查看我的新消息开始 !");
        result.setResultObject(msgList);
        logger.info("=====================>查看我的新消息开始 结束!!");
        return JSONObject.fromObject(result).toString();
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#noticeAndactive()
     */

    @Override
    public String noticeAndactive(final String aParams) {
        final Map<String, String> paramsMap = JsonUtils.stringToObject(aParams);
        final String pageSize = paramsMap.get("pageSize") == null ? "15" : paramsMap.get("pageSize").toString();
        final String gotoPage = paramsMap.get("gotoPage") == null ? "1" : paramsMap.get("gotoPage").toString();

        final LjNoticeDto condition = new LjNoticeDto();
        condition.setPageSize(Integer.parseInt(pageSize));
        condition.setGotoPage(Integer.parseInt(gotoPage));
        condition.setStatus(1);
        condition.setType(0);
        final List<LjNoticeDto> noticeList = this.ljNoticeService.getLjNoticeList(condition);

        condition.setType(1);
        final List<LjNoticeDto> activeList = this.ljNoticeService.getLjNoticeList(condition);

        final List noticeList2 = new ArrayList();
        for (final LjNoticeDto dto : noticeList) {
            final AppActive active = new AppActive();
            active.setName(dto.getName());
            active.setId(dto.getId());
            if (dto.getCreater() != null) {
                active.setCreaterNickName(dto.getCreater().getNickName());
            }
            if (dto.getCreateTime() != null) {
                active.setCreateTime(DateFormatUtils.format(dto.getCreateTime(), "yyyy-MM-dd"));
            }

            noticeList2.add(active);
        }

        final List activeList2 = new ArrayList();
        for (final LjNoticeDto dto : activeList) {
            final AppActive active = new AppActive();
            active.setName(dto.getName());
            active.setId(dto.getId());
            if (dto.getCreater() != null) {
                active.setCreaterNickName(dto.getCreater().getNickName());
            }

            if (dto.getCreateTime() != null) {
                active.setCreateTime(DateFormatUtils.format(dto.getCreateTime(), "yyyy-MM-dd"));
            }

            activeList2.add(active);
        }

        final List resultList = new ArrayList();
        resultList.add(noticeList2);
        resultList.add(activeList2);

        final Result result = new Result();
        result.setMessageCode(Result.SUCCESS);
        result.setMessage("获取公告活动成功 !");
        result.setResultObject(resultList);
        logger.info("=====================>获取公告活动成功结束!!" + JSONObject.fromObject(result).toString());
        return JSONObject.fromObject(result).toString();
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#createVerifyCode(java.lang.String)
     */

    @Override
    public String createVerifyCode(final String aParams, final HttpSession session) {

        final Map<String, String> paramsMap = JsonUtils.stringToObject(aParams);
        final String mobile = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");

        String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("");
        path = path + "/verifies/";

        final String imgName = mobile + Calendar.getInstance().getTimeInMillis();

        final String filePath = path + imgName + ".jpg";

        final File dir = new File(path);
        final int w = 80, h = 40;
        final String verifyCode = VertifyCodeUtils.generateVerifyCode(AppConfig.VERIFY_CODE_LENGTH);
        final File file = new File(filePath);
        session.setAttribute("SUBMIT_VERIFY_CODE", verifyCode);
        session.setAttribute("CREATE_VERIFY_CODE_TIME", new Date());
        try {
            VertifyCodeUtils.outputImage(w, h, file, verifyCode);
        } catch (final IOException e) {
            e.printStackTrace();
        }

        final String imgPath = "/verifies/" + imgName + ".jpg";

        final Result result = new Result();
        result.setMessageCode(Result.SUCCESS);
        result.setMessage("创建验证码成功 !");
        result.setResultObject(imgPath);
        logger.info("=====================>创建验证码成功结束!!" + JSONObject.fromObject(result).toString());
        return JSONObject.fromObject(result).toString();
    }

    /**
     * 提交前判断验证码是否正确和过期。
     * 
     * @param verifyCode
     * @param session
     * @return boolean
     */
    private Result canSubmit(final String verifyCode, final HttpSession session) {

        final String codeInsession =
                session.getAttribute("SUBMIT_VERIFY_CODE") == null ? "" : (String) session
                        .getAttribute("SUBMIT_VERIFY_CODE");
        final Date gotVertifyCodeTime = (Date) session.getAttribute("CREATE_VERIFY_CODE_TIME");
        final Result result = new Result();

        final Date now = new Date();

        if (StringUtils.isEmpty(verifyCode)) {
            result.setMessageCode(Result.FAIL);
            result.setMessage("请输入验证码！");
            return result;
        }

        final long time = now.getTime() - gotVertifyCodeTime.getTime();// 时间差
        if (time >= 30 * 60 * 1000) {
            result.setMessageCode(Result.FAIL);
            result.setMessage("验证码超时，请重新获取验证码！");
            return result;
        }

        if (!codeInsession.equalsIgnoreCase(verifyCode)) {
            result.setMessageCode(Result.FAIL);
            result.setMessage("验证码错误，请重新输入验证码！");
            return result;
        }

        result.setMessageCode(Result.SUCCESS);
        return result;
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#cancelRepairOrCpl(java.lang.String, javax.servlet.http.HttpSession)
     */

    @Override
    @Transactional
    public String cancelRepairOrCpl(final String aParams) {
        logger.info("=====================>取消报修或投诉开始  params=" + aParams);

        Result result = new Result();

        final Map<String, String> paramsMap = JsonUtils.stringToObject(aParams);
        final String mobile = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");
        final String id = paramsMap.get("id") == null ? "" : (String) paramsMap.get("id");

        result = checkMobile(mobile);
        if (result.getMessageCode() == Result.FAIL) {
            return JSONObject.fromObject(result).toString();
        }

        final LjRepairCplnDto dto = this.ljRepairCplnService.getById(Long.valueOf(id));

        if (dto.getReporterId().equalsIgnoreCase(mobile)) {
            dto.setStatus(3);
            dto.setUpdTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
            dto.setUpdId(mobile);
            this.ljRepairCplnService.update(dto);
            result.setMessageCode(Result.SUCCESS);
            result.setMessage("取消报修或投成功！");
        } else {
            result.setMessageCode(Result.FAIL);
            result.setMessage("取消报修或投诉失败！你怎么可以取消别人的报修或投诉？！");
        }

        logger.info("=====================>取消报修或投诉结束  !!" + JSONObject.fromObject(result).toString());
        return JSONObject.fromObject(result).toString();
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#isValidToken(java.lang.String)
     */

    @Override
    public Result isValidToken(final HttpServletRequest request, final HttpServletResponse response) {

        final Result result = new Result();
        String token = "";
        final Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            logger.info("=====================cookies != null!!!!!");
            for (final Cookie cookie : cookies) {
                final String cookieName = cookie.getName();
                if (!StringUtils.isEmpty(cookieName) && cookieName.equalsIgnoreCase(AppConfig.MANAGER_MEMORY)) {
                    final String cookieValue = cookie.getValue();
                    logger.info("=====================cookieValue=" + cookieValue);
                    final AppLoginUser loginUser =
                            (AppLoginUser) JsonUtils.stringToObject(cookieValue, AppLoginUser.class);
                    token = loginUser.getAccessToken();

                }
            }
        }
        if (StringUtils.isNotEmpty(token)) {
            final LoginUserDto dto = this.userService.getUserByToken(token);

            if (dto != null) {
                final Date expireDate = dto.getExpireTime();
                final Date now = new Date();
                if (now.before(expireDate)) {
                    result.setMessage("Token 有效!!");
                    result.setMessageCode(Result.SUCCESS);
                    result.setResultObject(dto);
                    logger.info("=====================Token ok!!");
                    return result;
                } else {
                    result.setMessage("Token过期，无效Token !!");
                    result.setMessageCode(Result.FAIL);
                    result.setResultObject(null);
                    logger.info("=====================Token过期，无效的Token !!");
                }
            } else {
                result.setMessage("您的账户在别处登录过了，若非本人操作，为了安全请重新登录并修改密码  !!");
                result.setMessageCode(Result.MULT_LOGIN);
                result.setResultObject(null);
                this.removeCookie(request, response);
                logger.info("=====================您的账户在别处登录过了，为了安全请重新登录并修改密码  !!");
            }

        } else {
            result.setMessage("您还未登录，请重新登录 !!");
            result.setMessageCode(Result.NO_LOGIN);
            result.setResultObject(null);
            logger.info("=====================您还未登录，请重新登录 !!");
        }
        return result;
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#getRoomInfo(java.lang.String)
     */

    @Override
    public String getRoomInfo(final String aParams) {
        final Result result = new Result();
        final Map<String, String> paramsMap = JsonUtils.stringToObject(aParams);
        final String roomCode = paramsMap.get("roomCode") == null ? "" : (String) paramsMap.get("roomCode");
        final LjRoomInfoDto dto = this.ljRoomInfoService.getRoomInfo(roomCode);

        if (dto != null) {
            final RoomInfo room = new RoomInfo();
            room.setLastPeriod(dto.getLastPeriod());
            final double dPrice = dto.getTotalPrice();

            final DecimalFormat df = new DecimalFormat("#.00");
            room.setPrice(df.format(dPrice));
            room.setRoomCode(dto.getCode());
            room.setId(dto.getId().toString());
            room.setArea(String.valueOf(dto.getMeasureOfArea()));
            result.setMessageCode(Result.SUCCESS);
            result.setMessage("取得房屋信息成功！");
            result.setResultObject(room);

        }

        result.setMessageCode(Result.FAIL);
        result.setMessage("取得房屋信息失败！单元代码不存在！");
        logger.info("getRoomInfo=====>" + JSONObject.fromObject(result).toString());
        return JSONObject.fromObject(result).toString();
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#addRoom(java.lang.String)
     */

    @Override
    @Transactional
    public String addRoom(final String aParams) {
        logger.info("=====================>新增房屋  params=" + aParams);
        final Map paramsMap = JsonUtils.stringToObject(aParams);
        final Result result = new Result();

        final String mobile = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");
        final String building = paramsMap.get("building") == null ? "" : (String) paramsMap.get("building");
        final String room = paramsMap.get("room") == null ? "" : (String) paramsMap.get("room");

        final String roomCode = "MLY-" + building + "-" + room;
        final LjUserBuilding userRoom = new LjUserBuilding();
        userRoom.setUserId(mobile);

        final LjRoomInfoDto condition = new LjRoomInfoDto();
        condition.setCode(roomCode);
        final List<LjRoomInfoDto> list = this.ljRoomInfoService.getRoomInfoList(condition);
        if (CollectionUtils.isEmpty(list)) {
            result.setMessageCode(Result.FAIL);
            result.setMessage("新增房屋失败，单元编码" + roomCode + "不存在！");
            logger.info("====》新增房屋失败，单元编码" + roomCode + "不存在！");
            return JSONObject.fromObject(result).toString();
        } else {

            final List<RoomInfo> list2 = this.ljUserBuildingService.getUserRoom(mobile);
            if (CollectionUtils.isNotEmpty(list2)) {
                for (final RoomInfo roomInfo : list2) {
                    if (roomInfo.getRoomCode().equalsIgnoreCase(roomCode)) {
                        result.setMessageCode(Result.FAIL);
                        result.setMessage("单元编码" + roomCode + "重复绑定！");
                        logger.info("====》新增房屋失败，单元编码" + roomCode + "重复绑定！");
                        return JSONObject.fromObject(result).toString();
                    }
                }
            }

            userRoom.setRoomInfoId(list.get(0).getId().toString());
            this.ljUserBuildingService.insertUserRoom(userRoom);
            result.setMessageCode(Result.SUCCESS);
            result.setMessage("新增房屋成功!");
            logger.info("新增房屋成功!");
            return JSONObject.fromObject(result).toString();
        }

    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#delRoom(java.lang.String)
     */
    @Override
    @Transactional
    public String delRoom(final String aParams) {
        final Result result = new Result();
        final Map paramsMap = JsonUtils.stringToObject(aParams);
        final String mobile = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");
        final String roomCode = paramsMap.get("roomCode") == null ? "" : (String) paramsMap.get("roomCode");
        this.ljUserBuildingService.delUserRoom(mobile, roomCode);
        result.setMessageCode(Result.SUCCESS);
        result.setMessage("删除房间成功!");
        logger.info("删除房间成功!");
        return JSONObject.fromObject(result).toString();
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#getKbn(java.lang.String)
     */

    @Override
    public String getKbn(final String aParams) {
        final Result result = new Result();
        final Map<String, String> paramsMap = JsonUtils.stringToObject(aParams);
        final String id = paramsMap.get("id") == null ? "" : (String) paramsMap.get("id");
        if (StringUtils.isEmpty(id)) {
            result.setMessageCode(Result.FAIL);
            result.setMessage("获取黄页或家政服务的小类型失败！");
            logger.info("getKbn=====>获取黄页或家政服务的小类型失败,id为空       param:" + JSONObject.fromObject(result).toString());
            return JSONObject.fromObject(result).toString();
        }
        final AppKbn appKbn = this.ljAppLogic.getKbn(id);
        result.setMessageCode(Result.SUCCESS);
        result.setMessage("获取黄页或家政服务小类型成功");
        result.setResultObject(appKbn);
        return JSONObject.fromObject(result).toString();

    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#getKbnList(java.lang.String)
     */

    @Override
    public String getKbnList(final String aParams) {
        final Result result = new Result();
        final Map<String, String> paramsMap = JsonUtils.stringToObject(aParams);
        final String code = paramsMap.get("code") == null ? "" : (String) paramsMap.get("code");
        if (StringUtils.isEmpty(code)) {
            result.setMessageCode(Result.FAIL);
            result.setMessage("获取黄页或家政服务信息失败！");
            logger.info("getKbnList=====>获取黄页或家政服务信息失败,code为空       param:" + JSONObject.fromObject(result).toString());
            return JSONObject.fromObject(result).toString();
        }
        final List<AppKbn> appKbnList = this.ljAppLogic.getKbnList(code);
        result.setMessageCode(Result.SUCCESS);
        result.setMessage("获取黄页或家政服务列表成功");
        result.setResultObject(appKbnList);
        return JSONObject.fromObject(result).toString();
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#getMerchantList(java.lang.String)
     */

    @Override
    public String getMerchantList(final String aParams) {
        final Result result = new Result();
        final Map paramsMap = JsonUtils.stringToObject(aParams);
        final String smallTypeId = paramsMap.get("smallTypeId") == null ? "" : paramsMap.get("smallTypeId").toString();
        final String gotoPage = paramsMap.get("gotoPage") == null ? "" : paramsMap.get("gotoPage").toString();
        final String pageSize = paramsMap.get("pageSize") == null ? "" : paramsMap.get("pageSize").toString();
        if (StringUtils.isEmpty(smallTypeId)) {
            result.setMessageCode(Result.FAIL);
            result.setMessage("获取黄页或家政服务分类信息失败！小类型为空");
            logger.info("getMerchantList=====>获取黄页或家政服务分类信息失败,smallTypeId为空       param:"
                    + JSONObject.fromObject(result).toString());
            return JSONObject.fromObject(result).toString();
        }
        final List<AppMerchant> appMerchantList = this.ljAppLogic.getMerchantList(smallTypeId, gotoPage, pageSize);
        result.setMessageCode(Result.SUCCESS);
        result.setMessage("获取黄页或家政服务分类信息成功！");
        result.setResultObject(appMerchantList);
        return JSONObject.fromObject(result).toString();
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#getMerchantDetail(java.lang.String)
     */

    @Override
    public String getMerchantDetail(final String aParams) {
        final Result result = new Result();
        final Map<String, String> paramsMap = JsonUtils.stringToObject(aParams);
        final String id = paramsMap.get("id") == null ? "" : (String) paramsMap.get("id");
        if (StringUtils.isEmpty(id)) {
            result.setMessageCode(Result.FAIL);
            result.setMessage("获取商家、家政服务详细失败！商家、家政服务id为空");
            logger.info("getMerchantDetail=====>获取商品详细失败,id为空       param:" + JSONObject.fromObject(result).toString());
            return JSONObject.fromObject(result).toString();
        }
        final AppMerchant appMerchant = this.ljAppLogic.getMerchantDetail(id);

        if (appMerchant == null) {
            result.setMessageCode(Result.FAIL);
            result.setMessage("id为" + id + "的商家不存在！");
            logger.info("getMerchantDetail=====>id为" + id + "的商家不存在！ param:" + JSONObject.fromObject(result).toString());
            return JSONObject.fromObject(result).toString();
        }
        result.setMessageCode(Result.SUCCESS);
        result.setMessage("获取商家、家政服务详细成功");
        result.setResultObject(appMerchant);
        return JSONObject.fromObject(result).toString();
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#kuaidiQuery(java.lang.String)
     */

    @Override
    public String kuaidiQuery(final String aParams) {

        final Result result = new Result();
        final Map<String, String> paramsMap = JsonUtils.stringToObject(aParams);
        final String companyCode = paramsMap.get("companyCode") == null ? "" : (String) paramsMap.get("companyCode");
        final String orderNo = paramsMap.get("orderNo") == null ? "" : (String) paramsMap.get("orderNo");

        if (StringUtils.isEmpty(companyCode)) {
            result.setMessageCode(Result.FAIL);
            result.setMessage("快递查询失败，请选择快递公司！");
            logger.info("kuaidiQuery=====>查询失败，请选择快递公司！       param:" + aParams);
            return JSONObject.fromObject(result).toString();
        }

        if (StringUtils.isEmpty(orderNo)) {
            result.setMessageCode(Result.FAIL);
            result.setMessage("快递查询失败，请输入快递单号！");
            logger.info("getStoreDetail=====>快递查询失败，请输入快递单号！       param:" + aParams);
            return JSONObject.fromObject(result).toString();
        }
        final String msg = this.ljAppLogic.kuaidiQuery(companyCode, orderNo);

        if (StringUtils.isEmpty(msg)) {
            result.setMessageCode(Result.FAIL);
            result.setMessage("快递查询失败，快递接口无返回数据！");
            logger.info("kuaidiQuery=====>快递查询失败，快递接口无返回数据！       param:" + aParams);
            return JSONObject.fromObject(result).toString();
        }

        result.setMessageCode(Result.SUCCESS);
        result.setMessage("快递查询成功！");
        result.setResultObject(msg);
        logger.info("kuaidiQuery=====>快递查询成功！       param:" + JSONObject.fromObject(result).toString());
        return JSONObject.fromObject(result).toString();

    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#getUserNoticeOrActiveList(java.lang.String)
     */

    @Override
    public String getUserNoticeOrActiveList(final String params) {
        final Map paramsMap = JsonUtils.stringToObject(params);
        final String mobile = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");
        final String pageSize = paramsMap.get("pageSize") == null ? "15" : paramsMap.get("pageSize").toString();
        final String gotoPage = paramsMap.get("gotoPage") == null ? "1" : paramsMap.get("gotoPage").toString();
        final String type = paramsMap.get("type") == null ? "" : paramsMap.get("type").toString();
        Result result = new Result();
        result = checkMobile(mobile);
        if (result.getMessageCode() == Result.FAIL) {
            return JSONObject.fromObject(result).toString();
        }
        final LjNoticeDto condition = new LjNoticeDto();
        if (StringUtils.isNotEmpty(type)) {
            condition.setType(Integer.valueOf(type));
        }
        condition.setPageSize(Integer.valueOf(pageSize));
        condition.setGotoPage(Integer.valueOf(gotoPage));
        condition.setStatus(1);
        condition.setCreaterId(mobile);
        final List<LjNoticeDto> resultList = this.ljNoticeService.getLjNoticeList(condition);
        final List<AppActive> list = new ArrayList<AppActive>();
        if (resultList != null) {
            for (final LjNoticeDto dto : resultList) {
                final AppActive active = this.noticeToActive(dto);
                list.add(active);
            }
        }
        result.setMessageCode(Result.SUCCESS);
        result.setMessage("获取我的活动或我的话题列表成功！");
        result.setResultObject(list);
        logger.info("=====================>获取我的活动或我的话题列表成功！！" + JSONObject.fromObject(result).toString());
        return JSONObject.fromObject(result).toString();

    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#addTopicOrActive(java.lang.String)
     */

    @Override
    @Transactional
    public String addTopicOrActive(final String aParams) {
        Result result = new Result();
        try {
            final Map paramsMap = JsonUtils.stringToObject(aParams);
            final String mobile = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");
            final String title = paramsMap.get("title") == null ? "" : (String) paramsMap.get("title");
            final String content = paramsMap.get("content") == null ? "" : (String) paramsMap.get("content");
            final String startDate = paramsMap.get("startDate") == null ? "" : (String) paramsMap.get("startDate");
            final String endDate = paramsMap.get("endDate") == null ? "" : (String) paramsMap.get("endDate");
            final String planNum = paramsMap.get("planNum") == null ? "" : (String) paramsMap.get("planNum");
            final String type = paramsMap.get("type") == null ? "" : (String) paramsMap.get("type");
            final String place = paramsMap.get("place") == null ? "" : (String) paramsMap.get("place");
            final JSONArray images = (JSONArray) paramsMap.get("images");

            result = checkMobile(mobile);
            if (result.getMessageCode() == Result.FAIL) {
                return JSONObject.fromObject(result).toString();
            }
            final LjNoticeDto noticeDto = new LjNoticeDto();
            noticeDto.setCreaterId(mobile);
            noticeDto.setName(title);
            noticeDto.setTheContent(content);
            if (StringUtils.isNotEmpty(startDate)) {
                noticeDto.setStartDate(CommonUtils.stringToDate(startDate, "yyyy-MM-dd"));
            }
            if (StringUtils.isNotEmpty(endDate)) {
                noticeDto.setEndDate(CommonUtils.stringToDate(endDate, "yyyy-MM-dd"));
            }
            if (StringUtils.isNotEmpty(planNum)) {
                noticeDto.setPlanNum(Integer.parseInt(planNum));
            }
            if (StringUtils.isNotEmpty(type)) {
                noticeDto.setType(Integer.parseInt(type));
            }
            if (StringUtils.isNotEmpty(place)) {
                noticeDto.setPlace(place);;
            }
            noticeDto.setInfactNum(1);
            if (images != null) {
                for (int i = 0; i < images.size(); i++) {
                    final JSONObject names = images.getJSONObject(i);
                    if (names != null) {
                        final String name = names.get("name").toString();
                        if (i == 0) {
                            noticeDto.setImage1(name);
                        }
                        if (i == 1) {
                            noticeDto.setImage2(name);
                        }
                        if (i == 2) {
                            noticeDto.setImage3(name);
                        }
                        if (i == 3) {
                            noticeDto.setImage4(name);
                        }
                        if (i == 4) {
                            noticeDto.setImage5(name);
                        }
                    }
                }
            }

            if (noticeDto.getType() == 3) {
                if (CommonUtil.after(startDate, CommonUtil.strToDate(endDate))) {
                    result.setMessage("开始时间不得大于结束时间");
                    result.setMessageCode(Result.FAIL);
                    return JSONObject.fromObject(result).toString();
                }
                final Date nowDate = new Date();
                final String strNowDate = CommonUtil.dateToStr(nowDate);
                if (CommonUtil.after(strNowDate, CommonUtil.strToDate(startDate))) {
                    result.setMessage("开始时间不得小于当前时间");
                    result.setMessageCode(Result.FAIL);
                    return JSONObject.fromObject(result).toString();
                }
            }
            final Long id = this.ljNoticeService.insert(noticeDto);
            if (noticeDto.getType() == 3) {

                final LjActionUserDto actionUserDto = new LjActionUserDto();
                actionUserDto.setActionId(id);
                actionUserDto.setUserId(mobile);
                actionUserDto.setType(1);
                try {
                    this.ljActionUserService.insert(actionUserDto);
                } catch (final DuplicateKeyException e) {
                    e.printStackTrace();
                }
                result.setMessage("活动发布成功！");
            } else {
                result.setMessage("话题发布成功！");
            }

            result.setMessageCode(Result.SUCCESS);
            result.setResultObject(id);
            logger.info("话题或活动发布成功！" + JSONObject.fromObject(result).toString());
            return JSONObject.fromObject(result).toString();
        } catch (final DuplicateKeyException e) {
            e.printStackTrace();
            result.setMessage("话题或活动发布失败！");
            result.setMessageCode(Result.FAIL);
            logger.info("话题或活动发布失败！");
            return JSONObject.fromObject(result).toString();
        }

    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#participateInActivities(java.lang.String)
     */

    @Override
    public String participateInActivities(final String aParams) {
        final Result result = new Result();
        final Map<String, String> paramsMap = JsonUtils.stringToObject(aParams);
        final String id = paramsMap.get("id") == null ? "" : (String) paramsMap.get("id");
        final String userId = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");
        if (StringUtils.isEmpty(id)) {
            result.setMessageCode(Result.FAIL);
            result.setMessage("参与活动失败！活动id为空");
            logger.info("participateInActivities=====>参与活动失败,id为空       param:"
                    + JSONObject.fromObject(result).toString());
            return JSONObject.fromObject(result).toString();
        }
        final LjActionUserDto actionUserdto = new LjActionUserDto();
        actionUserdto.setActionId(Long.valueOf(id));
        actionUserdto.setUserId(userId);
        actionUserdto.setType(1);
        if (this.ljActionUserService.getActionUserList(actionUserdto).size() > 0) {
            try {
                this.ljActionUserService.delete(this.ljActionUserService.getActionUserList(actionUserdto).get(0));
                final LjNoticeDto noticeDto = this.ljNoticeService.getById(Long.valueOf(id));
                final int peopleNum = noticeDto.getInfactNum() - 1;
                noticeDto.setInfactNum(peopleNum);
                this.ljNoticeService.update(noticeDto);
            } catch (final ObjectNotFoundException e) {
                e.printStackTrace();
            } catch (final OptimisticLockStolenException e) {
                e.printStackTrace();
            }
            result.setMessageCode(Result.SUCCESS);
            result.setMessage("退出活动成功！");
            return JSONObject.fromObject(result).toString();
        }
        this.ljAppLogic.participateInActivities(id, userId);
        result.setMessageCode(Result.SUCCESS);
        result.setMessage("参与活动成功");

        return JSONObject.fromObject(result).toString();
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#getPayStatus(java.lang.String)
     */

    @Override
    public String getPayStatus(final String aParams) {
        System.out.println("根据订单号，查询支付状态:" + aParams);
        logger.info("=====================>根据订单号，查询支付状态=" + aParams);
        final Result result = new Result();
        final Map<String, String> paramsMap = JsonUtils.stringToObject(aParams);
        final String orderNo = paramsMap.get("orderNo") == null ? "" : (String) paramsMap.get("orderNo");

        final String[] status = this.ljAppLogic.getPayStatus(orderNo);
        System.out.println("支付状态:" + status[0] + "///////////////////////////////");

        if ("-1".equals(status[0])) {
            result.setMessageCode(Result.FAIL);
            result.setMessage("查询失败！");
            result.setResultObject(status);
        } else {
            result.setMessageCode(Result.SUCCESS);
            result.setMessage("根据订单号，查询支付状态成功！");
            result.setResultObject(status);
        }
        return JSONObject.fromObject(result).toString();
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#kuaidiList(java.lang.String)
     */

    @Override
    public JSON kuaidiList(final String aParams) {
        final Result result = new Result();
        final List list = this.ljAppLogic.kuaidiList();
        final JSONObject json = new JSONObject();
        json.put("li", list);
        System.out.println(json);
        return json;
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#thumbUp(java.lang.String)
     */

    @Override
    @Transactional
    public String thumbUp(final String aParams) {
        final Result result = new Result();
        final Map<String, String> paramsMap = JsonUtils.stringToObject(aParams);
        final String id = paramsMap.get("id") == null ? "" : (String) paramsMap.get("id");
        final String userId = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");
        if (StringUtils.isEmpty(id)) {
            result.setMessageCode(Result.FAIL);
            result.setMessage("点赞失败！活动id为空");
            logger.info("participateInActivities=====>参与活动失败,id为空       param:"
                    + JSONObject.fromObject(result).toString());
            return JSONObject.fromObject(result).toString();
        }
        final LjActionUserDto actionUserdto = new LjActionUserDto();
        actionUserdto.setActionId(Long.valueOf(id));
        actionUserdto.setUserId(userId);
        actionUserdto.setType(0);
        if (this.ljActionUserService.getActionUserList(actionUserdto).size() > 0) {
            try {
                this.ljActionUserService.delete(this.ljActionUserService.getActionUserList(actionUserdto).get(0));
            } catch (final ObjectNotFoundException e) {
                e.printStackTrace();
            } catch (final OptimisticLockStolenException e) {
                e.printStackTrace();
            }
            result.setMessage("取消点赞成功");
        } else {
            this.ljAppLogic.thumbUp(id, userId);
            result.setMessage("点赞成功");
        }
        result.setMessageCode(Result.SUCCESS);

        return JSONObject.fromObject(result).toString();
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#getVerifyCode()
     */

    @Override
    public String getVerifyCode2(final HttpSession session) {

        final Result result = new Result();

        final String sRand = CommonUtils.getVertifyCode(AppConfig.VERIFY_CODE_LENGTH);

        session.setAttribute("VERTIFY_CODE", sRand);
        session.setAttribute("VERTIFY_CODE_TIME", new Date());

        result.setMessageCode(Result.SUCCESS);
        result.setMessage("获取验证码成功！");
        result.setResultObject(sRand);
        logger.info("=====================>获取验证码成功！ " + JSONObject.fromObject(result).toString());
        return JSONObject.fromObject(result).toString();
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#updateHeadPic(java.lang.String,
     *      org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper)
     */

    @Override
    public String updateHeadPic(final String aParams, final MultiPartRequestWrapper aMultipartRequest) {
        final Map<String, String> paramsMap = JsonUtils.stringToObject(aParams);
        final String mobile = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");

        final Result rst = checkMobile(mobile);
        if (rst.getMessageCode() == Result.FAIL) {
            return JSONObject.fromObject(rst).toString();
        };

        final String str = this.upload(aParams, aMultipartRequest);
        final Result result = JsonUtils.stringToResult(str);

        if (result.getMessageCode() == 1) {

            try {
                final LoginUserDto dto = new LoginUserDto();
                dto.setUserId(mobile);
                dto.setImageName(result.getResultObject().toString());
                this.userService.updateUser(dto, null);
            } catch (final ObjectNotFoundException e) {
                e.printStackTrace();
                result.setMessageCode(Result.FAIL);
                result.setMessage("更新头像失败！");
                logger.info("=====================>更新头像失败！Params=" + aParams + " " + e);
                return JSONObject.fromObject(result).toString();
            } catch (final OptimisticLockStolenException e) {
                e.printStackTrace();
                result.setMessageCode(Result.FAIL);
                result.setMessage("更新头像失败！！");
                logger.info("=====================>更新头像失败！！！Params=" + aParams + " " + e);
                return JSONObject.fromObject(result).toString();
            } catch (final DuplicateKeyException e) {
                e.printStackTrace();
                result.setMessageCode(Result.FAIL);
                result.setMessage("更新头像失败！！！");
                logger.info("=====================>更新头像失败！Params=" + aParams + " " + e);
                return JSONObject.fromObject(result).toString();
            } catch (final BusinessCheckException e) {
                e.printStackTrace();
                result.setMessageCode(Result.FAIL);
                result.setMessage("更新头像失败！！！");
                logger.info("=====================>更新头像失败！Params=" + aParams + " " + e);
            }
        }

        return str;
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#getAppVersion()
     */

    @Override
    public double getAppVersion() {
        return 0;
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#getAndriodDownloadUrl()
     */

    @Override
    public String getAndriodDownloadUrl() {
        return null;
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#getIosDownloadUrl()
     */

    @Override
    public String getIosDownloadUrl() {
        return null;
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#delRepariOrCpl(java.lang.String)
     */

    @Override
    @Transactional
    public String delRepariOrCpl(final String aParams) {
        Result result = new Result();
        final Map paramsMap = JsonUtils.stringToObject(aParams);
        final String id = paramsMap.get("id") == null ? "" : paramsMap.get("id").toString();

        final String mobile = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");

        result = checkMobile(mobile);
        if (result.getMessageCode() == Result.FAIL) {
            return JSONObject.fromObject(result).toString();
        }

        if (StringUtils.isEmpty(id)) {
            result.setMessageCode(Result.FAIL);
            result.setMessage("删除已撤销的报修或投诉失败！id为空");
            return JSONObject.fromObject(result).toString();
        }

        this.ljAppLogic.delRepariOrCpl(id);
        result.setMessageCode(Result.SUCCESS);
        result.setMessage("删除成功！");

        return JSONObject.fromObject(result).toString();
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#deleteTopicOrActive(java.lang.String)
     */

    @Override
    public String deleteTopicOrActive(final String aParams) {
        Result result = new Result();
        final Map paramsMap = JsonUtils.stringToObject(aParams);
        final String id = paramsMap.get("id") == null ? "" : paramsMap.get("id").toString();

        final String mobile = paramsMap.get("mobile") == null ? "" : (String) paramsMap.get("mobile");

        result = checkMobile(mobile);
        if (result.getMessageCode() == Result.FAIL) {
            return JSONObject.fromObject(result).toString();
        }

        if (StringUtils.isEmpty(id)) {
            result.setMessageCode(Result.FAIL);
            result.setMessage("删除失败！id为空");
            return JSONObject.fromObject(result).toString();
        }
        this.ljAppLogic.deleteTopicOrActive(id);
        result.setMessageCode(Result.SUCCESS);
        result.setMessage("删除成功！");

        return JSONObject.fromObject(result).toString();
    }

    /**
     * @see com.youthen.lj.app.service.LjAppService#getActionUserList(java.lang.String)
     */

    @Override
    public String getActionUserList(final String aParams) {
        final Result result = new Result();
        final Map<String, String> paramsMap = JsonUtils.stringToObject(aParams);
        final String actionId = paramsMap.get("actionId") == null ? "" : (String) paramsMap.get("actionId");
        final String type = paramsMap.get("type") == null ? "" : (String) paramsMap.get("type");
        if (StringUtils.isEmpty(actionId)) {
            result.setMessageCode(Result.FAIL);
            result.setMessage("获取点赞或参与活动人员失败！活动id为空");
            logger.info("getActionUserList=====>获取点赞或参与活动人员失败,活动id为空       param:"
                    + JSONObject.fromObject(result).toString());
            return JSONObject.fromObject(result).toString();
        }
        final List<AppActionUser> list = this.ljAppLogic.getActionUserList(actionId, type);
        result.setMessageCode(Result.SUCCESS);
        result.setMessage("获取点赞或参与活动人员成功！");
        result.setResultObject(list);
        return JSONObject.fromObject(result).toString();
    }

}
