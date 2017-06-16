package com.youthen.lj.app.web;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.youthen.lj.app.bean.Result;
import com.youthen.lj.app.service.LjAppService;
import com.youthen.lj.constant.AppConfig;
import com.youthen.lj.constant.Command;
import com.youthen.lj.utils.JsonUtils;

@Controller
public class MobileController {

    static Logger logger = Logger.getLogger(MobileController.class.getName());

    @Autowired
    LjAppService ljAppService;
    HttpServletResponse response;

    @RequestMapping(value = "/phoneInterface")
    public @ResponseBody
    String mobileService(final HttpServletRequest request, final HttpServletResponse response) {
        this.response = response;
        final String secretKey = request.getParameter("secretKey");
        final String from = request.getParameter("from");// 判断是来自iPhone还是android
        final StringBuffer result = new StringBuffer();

        try {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html;charset=UTF-8");

            final String operationString = request.getParameter("opt");
            final String params = request.getParameter("params");
            logger.info(operationString + " params=" + params);
            final Integer operation = Command.commandMap.get(operationString.trim());

            if (secretKey.equals(AppConfig.SECRET_KEY)) {

                final HttpSession session = request.getSession();
                final Result rs = this.isLogin(request, response);
                if (operation != null) {

                    switch (operation.intValue()) {
                        case Command.login:
                            result.append(this.ljAppService.login(params, request, session, response));

                            break;
                        case Command.logout:
                            result.append(this.ljAppService.logout(params, request, response, session));
                            break;
                        case Command.reg:
                            result.append(this.ljAppService.reg(params, session, request, response));
                            break;
                        case Command.getAccessToken:
                            final Map paramsMap2 = JsonUtils.stringToObject(params);
                            final String code = paramsMap2.get("code") == null ? "" : paramsMap2.get("code").toString();
                            final Result accessTokenResult = this.ljAppService.getAccessToken(code, from);
                            result.append(JsonUtils.objectToString(accessTokenResult));

                            break;
                        case Command.isUserBinded:
                            Map paramsMap = JsonUtils.stringToObject(params);
                            String openId = paramsMap.get("openId") == null ? "" : paramsMap.get("openId").toString();
                            result.append(this.ljAppService.isUserBinded(request, response, openId));
                            break;
                        case Command.getWxUserInfo:

                            paramsMap = JsonUtils.stringToObject(params);
                            openId = paramsMap.get("openId") == null ? "" : paramsMap.get("openId").toString();
                            final String accessToken =
                                    paramsMap.get("accessToken") == null ? "" : paramsMap.get("accessToken").toString();

                            final Result wxUserResult = this.ljAppService.getWxUserInfo(accessToken, openId);
                            result.append(JsonUtils.objectToString(wxUserResult));

                            break;
                        case Command.getVerifyCode:
                            result.append(this.ljAppService.getVerifyCode(params, session));
                            break;

                        case Command.forgotPwd:
                            result.append(this.ljAppService.forgotPwd(params, session));
                            break;

                        case Command.changePwd:

                            if (rs.messageCode == Result.SUCCESS) {
                                result.append(this.ljAppService.changePwd(params, session));
                            } else {
                                return JsonUtils.objectToString(rs);
                            }

                            break;

                        case Command.feedBack:

                            if (rs.messageCode == Result.SUCCESS) {
                                result.append(this.ljAppService.feedBack(params, session));
                            } else {
                                return JsonUtils.objectToString(rs);
                            }

                            break;

                        case Command.aboutUs:
                            result.append(this.ljAppService.aboutUs(params));
                            break;

                        case Command.getNoticeOrActiveList:
                            result.append(this.ljAppService.getNoticeOrActiveList(params));
                            break;

                        case Command.getNoticeOrActiveDetail:
                            result.append(this.ljAppService.getNoticeOrActiveDetail(params));
                            break;

                        case Command.myRepairOrComplainList:
                            if (rs.messageCode == Result.SUCCESS) {
                                result.append(this.ljAppService.myRepairOrComplainList(params));
                            } else {
                                return JsonUtils.objectToString(rs);
                            }
                            break;

                        case Command.myRepairOrComplainDetail:

                            if (rs.messageCode == Result.SUCCESS) {
                                result.append(this.ljAppService.myRepairOrComplainDetail(params));
                            } else {
                                return JsonUtils.objectToString(rs);
                            }

                            break;

                        case Command.closeMyRepairOrComplain:

                            if (rs.messageCode == Result.SUCCESS) {
                                result.append(this.ljAppService.closeMyRepairOrComplain(params, session));
                            } else {
                                return JsonUtils.objectToString(rs);
                            }

                            break;
                        case Command.myPayHistoryList:

                            if (rs.messageCode == Result.SUCCESS) {
                                result.append(this.ljAppService.myPayHistoryList(params));
                            } else {
                                return JsonUtils.objectToString(rs);
                            }

                            break;
                        case Command.pay:
                            if (rs.messageCode == Result.SUCCESS) {
                                result.append(this.ljAppService.pay(params, request, response));
                            } else {
                                return JsonUtils.objectToString(rs);
                            }

                            break;

                        case Command.repairOrCpln:
                            if (rs.messageCode == Result.SUCCESS) {
                                result.append(this.ljAppService.repairOrCpln(params, session));
                            } else {
                                return JsonUtils.objectToString(rs);
                            }
                            break;

                        case Command.editUserInfo:
                            if (rs.messageCode == Result.SUCCESS) {
                                result.append(this.ljAppService.editUserInfo(params));
                            } else {
                                return JsonUtils.objectToString(rs);
                            }
                            break;

                        case Command.getUserInfo:
                            if (rs.messageCode == Result.SUCCESS) {
                                result.append(this.ljAppService.getUserInfo(params));
                            } else {
                                return JsonUtils.objectToString(rs);
                            }
                            break;

                        case Command.myScoreHistory:
                            if (rs.messageCode == Result.SUCCESS) {
                                result.append(this.ljAppService.myScoreHistory(params));
                            } else {
                                return JsonUtils.objectToString(rs);
                            }
                            break;

                        case Command.index:
                            result.append(this.ljAppService.index());
                            break;

                        case Command.sign:
                            if (rs.messageCode == Result.SUCCESS) {
                                result.append(this.ljAppService.sign(params, session));
                            } else {
                                return JsonUtils.objectToString(rs);
                            }
                            break;

                        case Command.upload:
                            result.append(this.ljAppService.upload(params, (MultiPartRequestWrapper) request));
                            break;

                        case Command.addComment:

                            if (rs.messageCode == Result.SUCCESS) {
                                result.append(this.ljAppService.addComment(params));
                            } else {
                                return JsonUtils.objectToString(rs);
                            }

                            break;

                        case Command.myNewMessages:

                            if (rs.messageCode == Result.SUCCESS) {
                                result.append(this.ljAppService.myNewMessages(params));
                            } else {
                                return JsonUtils.objectToString(rs);
                            }

                            break;
                        case Command.noticeAndActive:
                            result.append(this.ljAppService.noticeAndactive(params));
                            break;

                        case Command.createVerifyCode:
                            result.append(this.ljAppService.createVerifyCode(params, session));
                            break;

                        case Command.cancelRepairOrCpl:

                            if (rs.messageCode == Result.SUCCESS) {
                                result.append(this.ljAppService.cancelRepairOrCpl(params));
                            } else {
                                return JsonUtils.objectToString(rs);
                            }

                            break;

                        case Command.getRoomInfo:
                            result.append(this.ljAppService.getRoomInfo(params));
                            break;

                        case Command.getKbn:
                            result.append(this.ljAppService.getKbn(params));
                            break;
                        case Command.getKbnList:
                            result.append(this.ljAppService.getKbnList(params));
                            break;
                        case Command.getMerchantList:
                            result.append(this.ljAppService.getMerchantList(params));
                            break;
                        case Command.getMerchantDetail:
                            result.append(this.ljAppService.getMerchantDetail(params));
                            break;

                        case Command.addRoom:
                            if (rs.messageCode == Result.SUCCESS) {
                                result.append(this.ljAppService.addRoom(params));
                            } else {
                                return JsonUtils.objectToString(rs);
                            }

                            break;

                        case Command.delRoom:

                            if (rs.messageCode == Result.SUCCESS) {
                                result.append(this.ljAppService.delRoom(params));
                            } else {
                                return JsonUtils.objectToString(rs);
                            }

                            break;

                        case Command.kuaidiQuery:
                            result.append(this.ljAppService.kuaidiQuery(params));
                            break;
                        case Command.kuaidiList:
                            result.append(this.ljAppService.kuaidiList(params));
                            break;
                        case Command.getUserNoticeOrActiveList:
                            if (rs.messageCode == Result.SUCCESS) {
                                result.append(this.ljAppService.getUserNoticeOrActiveList(params));
                            } else {
                                return JsonUtils.objectToString(rs);
                            }
                            break;

                        case Command.addTopicOrActive:

                            if (rs.messageCode == Result.SUCCESS) {
                                result.append(this.ljAppService.addTopicOrActive(params));
                            } else {
                                return JsonUtils.objectToString(rs);
                            }

                            break;

                        case Command.getPayStatus:
                            System.out.println("getPayStatus方法:" + params);
                            result.append(this.ljAppService.getPayStatus(params));
                            break;

                        case Command.participateInActivities:
                            if (rs.messageCode == Result.SUCCESS) {
                                result.append(this.ljAppService.participateInActivities(params));
                            } else {
                                return JsonUtils.objectToString(rs);
                            }
                            break;

                        case Command.thumbUp:
                            if (rs.messageCode == Result.SUCCESS) {
                                result.append(this.ljAppService.thumbUp(params));
                            } else {
                                return JsonUtils.objectToString(rs);
                            }
                            break;

                        case Command.getActionUserList:
                            result.append(this.ljAppService.getActionUserList(params));
                            break;
                        case Command.getVerifyCode2:
                            result.append(this.ljAppService.getVerifyCode2(session));
                            break;

                        case Command.updateHeadPic:
                            // if (rs.messageCode == Result.SUCCESS) {
                            result.append(this.ljAppService
                                    .updateHeadPic(params, (MultiPartRequestWrapper) request));
                            // } else {
                            // return JsonUtils.objectToString(rs);
                            // }
                            break;
                        case Command.delRepariOrCpl:
                            if (rs.messageCode == Result.SUCCESS) {
                                result.append(this.ljAppService.delRepariOrCpl(params));
                            } else {
                                return JsonUtils.objectToString(rs);
                            }
                            break;
                        case Command.deleteTopicOrActive:

                            if (rs.messageCode == Result.SUCCESS) {
                                result.append(this.ljAppService.deleteTopicOrActive(params));
                            } else {
                                return JsonUtils.objectToString(rs);
                            }

                            break;
                        default:
                            break;
                    }
                }
                logger.info(operationString + "执行完毕================>opt=" + operationString + " param=" + params);
                logger.info("执行==result==============>" + result.toString());
                return result.toString();
            }

            final Result resultError = new Result();
            resultError.setMessageCode(Result.FAIL);
            resultError.setMessage("秘钥不正确!");
            return JsonUtils.objectToString(resultError);

        } catch (final Exception exception) {

            logger.error("error==================>" + exception.getMessage() + " opt=" + request.getParameter("opt")
                    + " params="
                    + request.getParameter("params"));
            final Result resultError = new Result();
            resultError.setMessageCode(Result.FAIL);
            resultError.setMessage(exception.getMessage());
            return JsonUtils.objectToString(resultError);
        }
    }

    @RequestMapping(value = "/payNotify")
    public @ResponseBody
    String payNotify(final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {
        logger.info("==================>微信支付成功后开始调用回调函数！！！！！！！");
        return this.ljAppService.payNotify(request, response);
    }

    @RequestMapping(value = "/wxpayNotify")
    public @ResponseBody
    String wxpayNotify(final HttpServletRequest request, final HttpServletResponse response)
            throws Exception {
        logger.info("==================>微信公众号支付成功后开始调用回调函数！！！！！！！");

        return this.ljAppService.wxpayNotify(request, response);
    }

    private Result isLogin(final HttpServletRequest request, final HttpServletResponse response) {
        return this.ljAppService.isValidToken(request, response);
    }
}
