package com.youthen.lj.constant;

import java.util.HashMap;
import java.util.Map;

public class Command {

    /**
     * 登录
     */
    public static final int login = 1;

    /**
     * 注册
     */
    public static final int reg = 2;

    /**
     * 取得验证码
     */
    public static final int getVerifyCode = 3;

    /**
     * 忘记密码
     */
    public static final int forgotPwd = 4;

    /**
     * 小区活动或公告的列表
     */
    public static final int getNoticeOrActiveList = 5;

    /**
     * 小区活动或公告详细
     */
    public static final int getNoticeOrActiveDetail = 6;

    /**
     * 报修或投诉列表
     */
    public static final int myRepairOrComplainList = 7;

    /**
     * 报修或投诉详细
     */
    public static final int myRepairOrComplainDetail = 8;

    /**
     * 闭我的报修或者投诉
     */
    public static final int closeMyRepairOrComplain = 9;

    /**
     * 我的缴费历史记录
     */
    public static final int myPayHistoryList = 10;

    /**
     * 欠费缴费
     */
    public static final int myPostPay = 11;

    /**
     * 修改密码
     */
    public static final int changePwd = 12;

    /**
     * 意见反馈
     */
    public static final int feedBack = 13;

    /**
     * 关于我们
     */
    public static final int aboutUs = 14;

    /**
     * 报修或投诉
     */
    public static final int repairOrCpln = 15;

    /**
     * 新增或修改用户资料
     */
    public static final int editUserInfo = 16;

    /**
     * 获取用户信息
     */
    public static final int getUserInfo = 17;

    /**
     * 获取积分历史
     */
    public static final int myScoreHistory = 19;

    /**
     * 首页
     */
    public static final int index = 20;

    /**
     * 上传
     */
    public static final int upload = 21;

    /**
     * 评论
     */
    public static final int addComment = 22;

    /**
     * 签到
     */
    public static final int sign = 23;

    /**
     * 签到
     */
    public static final int logout = 24;

    /**
     * 我的消息
     */
    public static final int myNewMessages = 25;

    /**
     * 公告活动菜单
     */
    public static final int noticeAndActive = 26;

    /**
     * 创建提交时的验证码
     */
    public static final int createVerifyCode = 27;

    /**
     * 撤销投诉或报修
     */
    public static final int cancelRepairOrCpl = 28;

    /**
     * 取得房屋信息
     */
    public static final int getRoomInfo = 30;

    /**
     * 微信支付回调接口
     */
    public static final int payNotify = 31;

    /**
     * 判断支付状态
     */
    public static final int getPayStatus = 32;

    /**
     * 获取商品详细信息
     */
    public static final int addRoom = 34;

    /**
     * 获取商品详细信息
     */
    public static final int delRoom = 36;

    /**
     * 获取黄页信息
     */
    public static final int getKbnList = 38;
    /**
     * 获取黄页分类信息
     */
    public static final int getMerchantList = 39;
    /**
     * 获取商家详细信息
     */
    public static final int getMerchantDetail = 40;

    /**
     * 结算
     */
    public static final int compute = 41;

    /**
     * 查询快递
     */
    public static final int kuaidiQuery = 42;

    /**
     * 个人话题或个人活动列表
     */
    public static final int getUserNoticeOrActiveList = 43;

    /**
     * 新增个人活动或者个人话题
     */
    public static final int addTopicOrActive = 48;

    /**
     * 获取黄页或家政服务的小类型
     */
    public static final int getKbn = 51;

    /**
     * 参与个人活动
     */
    public static final int participateInActivities = 55;

    /**
     * 快递列表
     */
    public static final int kuaidiList = 56;

    /**
     * 点赞
     */
    public static final int thumbUp = 57;

    public static final int closeSecondGoods = 58;

    /**
     * 我的送货地址
     */
    public static final int getMyAddress = 59;

    /**
     * 新增或修改我的送货地址
     */
    public static final int saveAddress = 60;

    /**
     * 删除我的送货地址
     */
    public static final int delAddress = 61;

    /**
     * 根据ID获取我的地址
     */
    public static final int getAddressById = 62;

    /**
     * 订单确认
     */
    public static final int confirmBuy = 63;

    /**
     * 立即购买
     */
    public static final int buyNow = 64;

    public static final int updateVersion = 65;

    /**
     * 获得参与或者点赞的用户昵称、id、头像
     */
    public static final int getActionUserList = 66;

    /**
     * 第三方登录
     */
    public static final int thirdLogin = 67;

    /**
     * 商品购买支付
     */

    public static final int getVerifyCode2 = 69;

    public static final int bindMobile = 73;
    public static final int updateHeadPic = 74;
    /**
     * 删除已撤销的报修或投诉记录
     */
    public static final int delRepariOrCpl = 75;

    /**
     * 删除话题或活动
     */
    public static final int deleteTopicOrActive = 78;

    /**
     * 获取access_Token
     */
    public static final int getAccessToken = 79;

    /**
     * 获取微信用户的基本信息
     */
    public static final int getWxUserInfo = 80;

    /**
     * 判断是否是本系统用户
     */
    public static final int isUserBinded = 81;

    /**
     * 缴费
     */
    public static final int pay = 82;

    public static Map<String, Integer> commandMap = new HashMap<String, Integer>();

    static
    {
        commandMap.put("login", login);
        commandMap.put("reg", reg);
        commandMap.put("getVerifyCode", getVerifyCode);
        commandMap.put("forgotPwd", forgotPwd);
        commandMap.put("changePwd", changePwd);
        commandMap.put("getNoticeOrActiveList", getNoticeOrActiveList);
        commandMap.put("getNoticeOrActiveDetail", getNoticeOrActiveDetail);
        commandMap.put("feedBack", feedBack);
        commandMap.put("aboutUs", aboutUs);

        commandMap.put("myRepairOrComplainList", myRepairOrComplainList);
        commandMap.put("myRepairOrComplainDetail", myRepairOrComplainDetail);
        commandMap.put("closeMyRepairOrComplain", closeMyRepairOrComplain);
        commandMap.put("myPayHistoryList", myPayHistoryList);
        commandMap.put("myPostPay", myPostPay);
        commandMap.put("pay", pay);
        commandMap.put("payNotify", payNotify);
        commandMap.put("getPayStatus", getPayStatus);

        commandMap.put("repairOrCpln", repairOrCpln);
        commandMap.put("editUserInfo", editUserInfo);

        commandMap.put("getUserInfo", getUserInfo);
        commandMap.put("myScoreHistory", myScoreHistory);
        commandMap.put("index", index);
        commandMap.put("upload", upload);
        commandMap.put("addComment", addComment);
        commandMap.put("sign", sign);
        commandMap.put("logout", logout);
        commandMap.put("myNewMessages", myNewMessages);

        commandMap.put("noticeAndActive", noticeAndActive);
        commandMap.put("createVerifyCode", createVerifyCode);
        commandMap.put("cancelRepairOrCpl", cancelRepairOrCpl);

        commandMap.put("getRoomInfo", getRoomInfo);

        commandMap.put("addRoom", addRoom);
        commandMap.put("delRoom", delRoom);

        commandMap.put("getKbn", getKbn);
        commandMap.put("getKbnList", getKbnList);
        commandMap.put("getMerchantList", getMerchantList);
        commandMap.put("getMerchantDetail", getMerchantDetail);

        commandMap.put("kuaidiQuery", kuaidiQuery);
        commandMap.put("kuaidiList", kuaidiList);
        commandMap.put("getUserNoticeOrActiveList", getUserNoticeOrActiveList);

        commandMap.put("addTopicOrActive", addTopicOrActive);

        commandMap.put("closeSecondGoods", closeSecondGoods);

        commandMap.put("participateInActivities", participateInActivities);
        commandMap.put("thumbUp", thumbUp);

        commandMap.put("saveAddress", saveAddress);
        commandMap.put("delAddress", delAddress);
        commandMap.put("getMyAddress", getMyAddress);
        commandMap.put("getAddressById", getAddressById);
        commandMap.put("confirmBuy", confirmBuy);
        commandMap.put("buyNow", buyNow);
        commandMap.put("updateVersion", updateVersion);
        commandMap.put("compute", compute);
        commandMap.put("getActionUserList", getActionUserList);
        commandMap.put("thirdLogin", thirdLogin);
        commandMap.put("getVerifyCode2", getVerifyCode2);
        commandMap.put("bindMobile", bindMobile);
        commandMap.put("updateHeadPic", updateHeadPic);
        commandMap.put("delRepariOrCpl", delRepariOrCpl);
        commandMap.put("deleteTopicOrActive", deleteTopicOrActive);
        commandMap.put("getAccessToken", getAccessToken);
        commandMap.put("getWxUserInfo", getWxUserInfo);
        commandMap.put("isUserBinded", isUserBinded);

    }

}
