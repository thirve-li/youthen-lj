// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.app.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.json.JSON;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import com.youthen.lj.app.bean.Result;

/**
 * 理家物业APP调用接口服务类。
 * 
 * @author Administrator
 * @author Modifier By LiXin
 * @version $Revision: $<br>
 *          $Date: $
 */
public interface LjAppService {

    /**
     * 能否加积分
     * 
     * @param mobile
     * @param actionName
     * @return
     */
    public boolean canAddScore(final String mobile, final String actionName);

    /**
     * 加积分。
     * 
     * @param mobile
     * @param form
     * @param socre
     * @return
     */
    public int addScore(final String mobile, final String form, final int socre);

    /**
     * 判断token是否有效
     */
    public Result isValidToken(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取accessToken
     * 。
     * 
     * @param code
     * @return
     */
    public Result getAccessToken(String code);

    /**
     * 获取微信用户的基本信息
     * 。
     * 
     * @param accessToken
     * @param openId
     * @return
     */
    public Result getWxUserInfo(String accessToken, String openId);

    /**
     * 首页。
     * 
     * @return LjNotice的json字符串
     */
    public String index();

    /**
     * 登录。
     * 
     * @param params
     *            eg: params={"mobile":"13671587455","pwd":"password"}
     * @return AppLoginUser和LjNotice的json字符串
     */
    public String login(String params, HttpServletRequest req, HttpSession session, HttpServletResponse response);

    /**
     * 登出。
     * 
     * @param params
     *            eg: params={"mobile":"13671587455"}
     * @return String
     */
    public String logout(String params, HttpServletRequest req, HttpServletResponse response, HttpSession session);

    /**
     * 注册。
     * 
     * @param params
     *            eg: params={"mobile":"13671587455","verifyCode":"2346"}
     * @return String
     */
    public String reg(String params, final HttpSession session, HttpServletRequest request, HttpServletResponse response);

    /**
     * 看用户是否绑定
     * 。
     * 
     * @param aReq
     * @param aResponse
     * @param params
     * @return
     */
    public String isUserBinded(final HttpServletRequest aReq,
            final HttpServletResponse aResponse, String params);

    /**
     * 忘记密码。
     * 
     * @param params
     *            eg: params={"mobile":"13671587455","verifyCode":"2346","pwd":"abc123","pwd2":"abc123"}
     * @return String
     */
    public String forgotPwd(String params, final HttpSession session);

    /**
     * 修改密码。
     * 
     * @param params
     *            eg: params={"mobile":"13671587455","oldPwd":"2346","pwd":"abc123","pwd2":"abc123"}
     * @return String
     */
    public String changePwd(String params, final HttpSession session);

    /**
     * 修改用户信息。
     * 
     * @param params
     *            eg: params=
     *            {"mobile":"13918531443"
     *            ,"image":"/test.jpg"
     *            ,"name":"LiXin",
     *            "nickName":"小飞侠",
     *            "buildingNum":["1","2","3"],
     *            "roomCode":["101","1005","1505"],
     *            "parkNum":["1","2","3"],
     *            "carNum":["沪A101","沪B1005","沪C1505"]}
     * @return String
     */
    public String editUserInfo(String params);

    /**
     * 取得用户信息。
     * 
     * @param params
     *            eg: params= { "mobile":"13671587455"}
     * @return String
     *         eg: resultObject= {
     *         "hasAsigned":"1","mobile":"13671587455","name":"lixin","nickName":"xiaofeixia","score":"100","newMsgCnt":
     *         "10","building":
     *         [
     *         { "buildingNum": "1", "roomCode":"101" },
     *         { "buildingNum": "2", "roomCode":"201" },
     *         ]
     *         ,"parkInfo":
     *         [
     *         { "parkNum": "1", "carNum":"沪A BK101" },
     *         { "parkNum": "2", "carNum":"沪A BK101" },
     *         ]
     *         }
     *         score:积分
     *         newMsgCnt：新消息条数
     *         buildingNum：楼道号
     *         roomCode：房间编号
     *         parkNum：车位号
     *         carNum：车牌号
     */
    public String getUserInfo(String params);

    /**
     * 获取验证码。
     * 
     * @param params
     *            eg:{"mobile":"13671587455"}
     * @return String
     */
    public String getVerifyCode(String params, final HttpSession session);

    /**
     * 社区通知和活动。
     * 
     * @param params
     *            eg:{"pageSize":10,"gotoPage":1,"type":1}
     * @return String
     */
    public String getNoticeOrActiveList(String params);

    /**
     * 社区通知和活动活动详情。
     * 
     * @param params
     *            eg:{"mobile":"13671587455","id":1}
     * @return String
     */
    public String getNoticeOrActiveDetail(String params);

    /**
     * 我的报修或者投诉。
     * 
     * @param params
     *            eg:{"mobile":"13671587455","type":1,"status":1,"year":2016,"month":1}
     * @return String
     */
    public String myRepairOrComplainList(String params);

    /**
     * 我的报修或者投诉详情。
     * 
     * @param params
     *            eg:{"mobile":"13671587455","id":1}
     * @return
     */
    public String myRepairOrComplainDetail(String params);

    /**
     * 关闭报修或者投诉。
     * 
     * @param params
     *            eg:{"mobile":"13671587455","id":1,"speed":1,"service":0}
     * @return String
     */
    public String closeMyRepairOrComplain(String params, HttpSession session);

    /**
     * 我的付费历史。
     * 
     * @param params
     *            eg:{"mobile":"13671587455","type":1,"year":2016,"month":1,"status":1}
     *            type： 0：物业费 1：车位管理费 2：其它
     *            status： 0：欠费 1：已缴费
     * @return String
     */
    public String myPayHistoryList(String params);

    /**
     * 缴费。
     * 
     * @param params
     *            缴纳物业费 eg :{
     *            "mobile":"13671587455"
     *            ,"type":0
     *            ,"payType":0
     *            ,"isPrepay":1
     *            ,"months":6
     *            ,"cuponId":1
     *            ,"roomInfo":
     *            [
     *            {"roomCode":"MLY-1-101","yearMonth":"201501","money":"1200"},
     *            {"roomCode":"MLY-1-101","yearMonth":"201501","money":"1200"}
     *            ]
     *            }
     *            缴纳车位管理费 eg:{
     *            "mobile":"13671587455"
     *            ,"type":1
     *            ,"payType":0
     *            ,"isPrepay":1
     *            ,"months":6
     *            ,"cuponId":1
     *            ,"parkInfo":
     *            [{
     *            {"parkNo":"01","yearMonth":"201501","money":"1200"},
     *            {"parkNo":"02","yearMonth":"201501","money":"1200"}
     *            }]
     *            }
     *            type：缴费类型 0=物业费 1=车位管理费
     *            payType : 支付方式 0=微信
     *            isPrepay：是否是预付费 1=是 0=否
     *            cuponId：折扣计划ID
     *            months : 缴费时长
     * @return
     */
    public String pay(String params, HttpServletRequest request, HttpServletResponse response);

    /**
     * 支付返回接口
     * 。
     * 
     * @param params
     * @return
     */
    public String payNotify(HttpServletRequest request, HttpServletResponse response);

    /**
     * 发送提示短信
     * 
     * @param mobile
     * @param content
     * @param templateNo 模板号
     * @return Result
     */
    public Result sendSMS(String mobile, String content, String templateNo);

    /**
     * 反馈
     * 
     * @param params
     *            eg:{"mobile":"13671587455","content":"反馈内容"}
     * @return String
     */
    public String feedBack(String params, HttpSession session);

    /**
     * 关于我们
     * 
     * @param params
     * @return String
     */
    public String aboutUs(String params);

    /**
     * 报修或投诉
     * 
     * @param params
     *            eg:{
     *            "mobile":"13671587455","repairObject":"light"
     *            ,"tel":"13671578899" ,"contacter":"王先森"
     *            ,"roomCode": "MLY-1-101","content":"内容"
     *            ,"title":"投诉标题","type":"1"
     *            ,imagesPath:[{"images","/test/a.jpg"}
     *            ,{"images", "/test/b.jpg"}]
     *            }
     * @return String
     */
    public String repairOrCpln(String params, HttpSession session);

    /**
     * 取得用户积分历史。
     * 
     * @param params
     *            eg: params= { "mobile":"13671587455","year":"2016","pageSize":"10","gotoPage":"1"}
     * @return String
     *         eg: resultObject = {
     *         [
     *         { "gotFrom": "登录", "score":"10","time":"2016-01-08 10:10:20", },
     *         { "gotFrom": "登录", "score":"10","time":"2016-01-09 10:20:20", }
     *         ]
     *         }
     *         score:积分
     *         gotFrom：来源
     *         time：时间
     */
    public String myScoreHistory(String params);

    /**
     * 取得用户通知。
     * 
     * @param params
     *            eg: params= { "mobile":"13671587455"}
     * @return String
     */
    public String myNewMessages(String params);

    /**
     * 签到。
     * 
     * @param params
     *            eg: params= { "mobile":"13671587455"}
     * @return String
     *         eg: result= { "score":"3"}
     */
    public String sign(String params, final HttpSession session);

    /**
     * 上传。
     * 
     * @param params
     *            eg: params= { "mobile":"13671587455"}
     * @return String fileName上传后的文件名
     */
    public String upload(String params, final MultiPartRequestWrapper multipartRequest);

    /**
     * 新增评论。
     * 
     * @param params
     *            eg: params={"mobile":"13918531443","id":"活动的ID","content":"评论内容","type":"社区评论为0 商品评论为1"}
     * @return String
     */
    public String addComment(String params);

    /**
     * 公告活动菜单列表。
     * 
     * @return String
     */
    public String noticeAndactive(String params);

    /**
     * 生成验证码图片。
     * 
     * @param params
     *            eg: params={"mobile":"13918531443"}
     * @return
     */
    public String createVerifyCode(String params, final HttpSession session);

    /**
     * @param params
     *            eg:params={"mobile":"手机号","id":"投诉或报修的id"}
     * @return String
     */
    public String cancelRepairOrCpl(String params);

    /**
     * 根据单元代码，取得房屋最后付费期数，每月物业费等信息
     */
    String getRoomInfo(String params);

    /**
     * 增加房屋
     * 
     * @param params
     *            eg:params={"mobile":"手机号","building":"1","room":"101"}
     * @return String
     */
    public String addRoom(String params);

    /**
     * 删除房屋
     * 
     * @param params
     *            eg:params={"mobile":"手机号","roomCode":"MLY-1-101"}
     * @return
     */
    public String delRoom(final String params);

    /**
     * 黄页或家政服务的小栏位
     * 。
     * 
     * @param params
     *            eg:params={"id":"206"}
     * @return
     */
    public String getKbn(final String params);

    /**
     * 黄页大栏位
     * 
     * @param params
     *            eg:params={"code":"SQHY或JZFW"}
     * @return String
     */
    public String getKbnList(String params);

    /**
     * 黄页小栏位
     * 
     * @param params
     *            eg:params={"smallTypeId":"小类型id","gotoPage":"1","pageSize":"15"}
     * @return String
     */
    public String getMerchantList(String params);

    /**
     * 商家或家政服务详情
     * 
     * @param params
     *            eg:params={"id":"商家和家政的id"}
     * @return String
     */
    public String getMerchantDetail(String params);

    /**
     * 快递查询。
     * 
     * @param params
     * @return
     */
    public String kuaidiQuery(String params);

    /**
     * 快递列表。
     * 
     * @param params
     * @return
     */
    public JSON kuaidiList(String params);

    /**
     * 根据用户id获取发布的个人话题或个人活动
     * 
     * @param params
     *            eg:{"mobile":"手机号","pageSize":15,"gotoPage":1,"type":1}
     * @return String
     */
    public String getUserNoticeOrActiveList(String params);

    /**
     * 个人话题和个人活动的发布
     * 
     * @param params
     *            eg:{"mobile":"手机号","title":"标题","images":图片,"content":"内容","startDate":"个人活动开始时间","endDate":"个人活动结束时间"
     *            ,
     *            "planNum":"个人活动计划人数","type":"类型"}
     * @return String
     */
    public String addTopicOrActive(String params);

    /**
     * 参与个人活动
     * 
     * @param params eg:{"id":"活动id","mobile":"参与人id"}
     *            return String
     */
    public String participateInActivities(String params);

    /**
     * 根据订单号获取支付状态
     */

    public String getPayStatus(String params);

    /**
     * 点赞
     * 
     * @param params eg:{"id":"活动id","mobile":"参与人id"}
     *            return String
     */
    public String thumbUp(String params);

    /**
     * 获得点赞或者参与活动的用户
     * 
     * @param aParams
     *            eg:{"actionId":"活动或话题id","type":"区分点赞还是参与"}
     * @return String
     */
    public String getActionUserList(String aParams);

    /**
     * 获取验证码。
     * 
     * @return String
     */
    public String getVerifyCode2(final HttpSession session);

    /**
     * 更新头像
     * 
     * @param params
     * @param multipartRequest
     * @return String
     */
    public String updateHeadPic(String params, final MultiPartRequestWrapper multipartRequest);

    /**
     * 获取App的版本号
     * 。
     * 
     * @return
     */
    public double getAppVersion();

    /**
     * 获取安卓下载地址。
     * 
     * @return
     */
    public String getAndriodDownloadUrl();

    /**
     * 获取IOS的下载地址
     * 
     * @return
     */
    public String getIosDownloadUrl();

    /**
     * 删除已撤销的报修或投诉
     * 。
     * 
     * @param params
     *            eg:{"aId":"报修或投诉id"}
     * @return
     */
    public String delRepariOrCpl(String params);

    /**
     * 删除活动或话题
     * 
     * @param params
     *            eg:{"Id":"话题或活动的id"}
     * @return String
     */
    public String deleteTopicOrActive(String params);

}
