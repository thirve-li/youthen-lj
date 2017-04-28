// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.app.logic;

import java.io.File;
import java.util.List;
import javax.servlet.ServletInputStream;
import net.sf.json.JSONArray;
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
import com.youthen.lj.service.dto.LjRepairCplnDto;

/**
 * 理家物业APP调用接口服务类。
 * 
 * @author Administrator
 * @author Modifier By LiXin
 * @version $Revision: $<br>
 *          $Date: $
 */
public interface LjAppLogic {

    /**
     * 社区黄页或家政服务列表
     * 
     * @param code 类型code
     * @return List<AppKbn>
     */
    public List<AppKbn> getKbnList(String code);

    /**
     * 根据ID去找小类型的黄页或家政服务
     * 。
     * 
     * @param id
     * @return AppKbn
     */
    public AppKbn getKbn(String id);

    /**
     * 社区黄页或家政服务子类型列表
     * 
     * @param smallTypeId 小类型Id
     * @param gotoPage 去第几页
     * @param pageSize 每页多少条数据
     * @return List<AppMerchant>
     */
    public List<AppMerchant> getMerchantList(String smallTypeId, String gotoPage, String pageSize);

    /**
     * 社区黄页、家政服务和商家详情
     * 
     * @param id 社区黄页、家政服务和商家id
     * @return AppMerchant
     */
    public AppMerchant getMerchantDetail(String id);

    /**
     * 根据商店code获得便利店信息
     * 
     * @param code 商店code
     * @return AppMerchant
     */
    public AppMerchant getStoreDetail(String code);

    /**
     * 首页。
     * 
     * @return List<AppActive>
     */
    public List<AppActive> index();

    /**
     * 登录。
     * 
     * @param mobile 手机号
     * @param pwd 密码
     * @return AppLoginUser
     */
    public AppLoginUser login(String mobile, String pwd);

    /**
     * 退出。
     * 
     * @param mobile
     */
    public void logout(String mobile);

    /**
     * APP手机用户注册。
     * 
     * @param mobile 手机号
     * @param pwd1 密码
     * @param pwd2 重复密码
     * @return AppLoginUser
     */
    public AppLoginUser reg(String mobile, String pwd1, String pwd2);

    /**
     * 忘记密码。
     * 。
     * 
     * @param mobile 手机号
     * @param verifyCode 验证码
     * @param pwd1 密码
     * @param pwd2 重复密码
     */
    public void forgotPwd(String mobile, String verifyCode, String pwd1, String pwd2);

    /**
     * 修改密码。
     * 
     * @param mobile 手机号
     * @param oldPwd 老密码
     * @param pwd1 密码
     * @param pwd2 重复密码
     */
    public void changePwd(String mobile, String oldPwd, String pwd1, String pwd2);

    /**
     * 修改用户信息。
     * 
     * @param mobile 手机号
     * @param name 姓名
     * @param nickName 昵称
     * @param image 头像路径
     * @param building 房屋信息
     * @param park 车位信息
     */
    public void editUserInfo(String mobile, String name, String nickName, String image, AppBuilding[] building);

    /**
     * 取得用户信息。
     * 
     * @param mobile 手机号
     * @return Long
     */
    public AppLoginUser getUserInfo(String mobile);

    /**
     * 获取验证码。
     * 
     * @param mobile 手机号
     * @return String
     */
    public String getVerifyCode(String mobile);

    /**
     * 第三方登录前获取验证码的接口。
     * 
     * @return String 验证码
     */
    public String getVerifyCode2();

    /**
     * 社区通知、社区活动、推荐活动、个人话题和个人活动。
     * 
     * @param pageSize 每页数量
     * @param gotoPage 第几页
     * @param type 类型
     * @return List<AppActive>
     */
    public List<AppActive> getNoticeOrActiveList(String pageSize, String gotoPage, String type);

    /**
     * 社区通知、社区活动、推荐活动、个人话题和个人活动的详情。
     * 
     * @param id
     * @return AppActive
     */
    public AppActive getNoticeOrActiveDetail(String id);

    /**
     * 我的活动和我的话题
     * 
     * @param mobile 手机号
     * @param pageSize 每页数量
     * @param gotoPage 第几页
     * @param type 类型
     * @return List<AppActive>
     */
    public List<AppActive> getUserNoticeOrActiveList(String mobile, String type, String pageSize, String gotoPage);

    /**
     * 我的报修或者投诉。
     * 
     * @param mobile 手机号
     * @param type 类型
     * @param status 状态
     * @param year 年
     * @param month 月
     * @return List<LjRepairCplnDto>
     */
    public List<LjRepairCplnDto> myRepairOrComplainList(String mobile, String type, String status, String year,
            String month);

    /**
     * 我的报修或者投诉详情。
     * 
     * @param mobile 手机号
     * @param id
     * @return LjRepairCplnDto
     */
    public LjRepairCplnDto myRepairOrComplainDetail(String mobile, String id);

    /**
     * 关闭报修或者投诉。
     * 
     * @param mobile 手机号
     * @param id
     * @param speed 处理速度
     * @param service 服务
     * @param commentContent 评论内容
     * @param verifyCode 验证码
     */
    public void closeMyRepairOrComplain(String mobile, String id, String speed, String service,
            String commentContent, String verifyCode);

    /**
     * 反馈
     * 
     * @param mobile 手机号
     * @param content 内容
     * @param verifyCode 验证码
     * @return Long
     */
    public Long feedBack(String mobile, String content, String verifyCode);

    /**
     * 关于我们
     * 
     * @return String
     */
    public String aboutUs();

    /**
     * 报修或投诉
     * 
     * @param mobile 手机号
     * @param tel
     * @param content 内容
     * @param repairObject 修理物品
     * @param roomCode 房屋编号
     * @param title 标题
     * @param contacterTel 联系人电话
     * @param contacter 联系人
     * @param type 类型
     * @param images 图片
     * @return Long
     */
    public Long repairOrCpln(String mobile, String tel, String content, String repairObject, String roomCode,
            String title, String contacterTel, String contacter, String type, JSONArray images);

    /**
     * 取得用户积分历史。
     * 
     * @param mobile 手机号
     * @param gotoPage 每页数量
     * @param pageSize 第几页
     * @return List<ScoreHistory>
     */
    public List<ScoreHistory> myScoreHistory(String mobile, String gotoPage, String pageSize);

    /**
     * 取得用户通知。
     * 
     * @param mobile 手机号
     * @return List<NewMessage>
     */
    public List<NewMessage> myNewMessages(String mobile);

    /**
     * 签到。
     * 
     * @param mobile 手机号
     * @return String
     */
    public String sign(String mobile);

    /**
     * 上传。
     * 
     * @param images 图片
     * @return List<String>
     */
    public List<String> upload(File[] images);

    /**
     * 新增评论。
     * 
     * @param id
     * @param type 类型
     * @param mobile 手机号
     * @param content 内容
     * @return Long
     */
    public Long addComment(String id, String type, String mobile, String content);

    /**
     * 公告活动菜单列表。
     * 
     * @return List
     */
    public List noticeAndactive(String gotoPage, String pageSize);

    /**
     * 生成验证码图片。
     * 
     * @param mobile 手机号
     * @return String
     */
    public String createVerifyCode(String mobile);

    /**
     * @param mobile 手机号
     * @param id
     */
    public void cancelRepairOrCpl(String mobile, String id);

    /**
     * 根据单元代码，取得房屋最后付费期数，每月物业费等信息
     */
    public RoomInfo getRoomInfo(String roomCode);

    /**
     * 增加房屋
     * 
     * @param params
     *            eg:params={"mobile":"手机号","building":"1","room":"101"}
     * @return String
     */

    /**
     * 增加房屋
     * 
     * @param mobile 手机号
     * @param building 楼号
     * @param room 房间号
     * @return String
     */
    public String addRoom(String mobile, String building, String room);

    /**
     * 删除房屋
     * 
     * @param mobile 手机号
     * @param roomCode 单元代码
     * @return String
     */
    public String delRoom(final String mobile, String roomCode);

    /**
     * 获取快递公司
     * 
     * @return String
     */
    public List<String> getExpressCompany();

    /**
     * 快递信息查询。
     * 
     * @param companyCode
     * @param orderNo
     * @return String
     *         字段名称 　　字段含义
     *         com 物流公司编号
     *         nu 物流单号
     *         time 每条跟踪信息的时间
     *         context 每条跟综信息的描述
     *         state 快递单当前的状态 ：　
     *         0：在途，即货物处于运输过程中；
     *         1：揽件，货物已由快递公司揽收并且产生了第一条跟踪信息；
     *         2：疑难，货物寄送过程出了问题；
     *         3：签收，收件人已签收；
     *         4：退签，即货物由于用户拒签、超区等原因退回，而且发件人已经签收；
     *         5：派件，即快递正在进行同城派件；
     *         6：退回，货物正处于退回发件人的途中；
     *         该状态还在不断完善中，若您有更多的参数需求，欢迎发邮件至 kuaidi@kingdee.com 提出。
     *         status 查询结果状态：
     *         0：物流单暂无结果，
     *         1：查询成功，
     *         2：接口出现异常，
     *         message 无意义，请忽略
     *         condition 无意义，请忽略
     *         ischeck 无意义，请忽略
     */
    public String kuaidiQuery(String companyCode, String orderNo);

    /**
     * 快递列表
     */
    public List kuaidiList();

    /**
     * 个人话题和个人活动的发布
     * 
     * @param mobile 手机号
     * @param title 标题
     * @param images 图片
     * @param content 内容
     * @param startDate 个人活动开始时间
     * @param endDate 个人活动结束时间
     * @param planNum 个人活动计划人数
     * @param type 类型。用来区分是个人活动和个人话题
     * @param place 活动地点
     * @return Long
     */
    public Long addTopicOrActive(String mobile, String title, JSONArray images, String content, String startDate,
            String endDate,
            String planNum, String type, String place);

    /**
     * 参与个人活动
     * 
     * @param activityId 活动id
     * @param userId 参与人id
     */
    public void participateInActivities(String activityId, String userId);

    /**
     * 根据订单号，查询支付状态
     * 
     * @param orderNo
     * @return String[]
     *         第一位是支付状态 0支付失败 1支付成功 -1查询失败
     *         第二位是类型 0物业缴费 1车位管理费 2购物支付
     */
    public String[] getPayStatus(String orderNo);

    /**
     * 下架二手。
     * 
     * @param mobile
     * @param id
     */
    public void closeSecondGoods(String mobile, String id);

    /**
     * 点赞
     * 
     * @param activityId 活动id
     * @param userId 参与人id
     */
    public void thumbUp(String activityId, String userId);

    /**
     * 获得点赞或者参与活动的用户
     * 
     * @param actionId 活动id
     * @param type 0:点赞、1:参与活动
     * @return List<AppActionUser>
     */
    public List<AppActionUser> getActionUserList(String actionId, String type);

    /**
     * 判断房屋是否存在
     * 。
     * 
     * @param roomCode
     * @return boolean
     */
    public boolean isHouseExisted(String roomCode);

    /**
     * 取得房屋月缴费用
     * 。
     * 
     * @param roomCode
     * @return double
     */
    public double getHousePrice(String roomCode);

    /**
     * 取得车位月缴费用
     * 。
     * 
     * @param roomCode
     * @return
     */
    public double getParkPrice(String parkNo);

    /**
     * 取得房屋最后付费期数。
     * 
     * @param roomCode
     * @return
     */
    public String getHouseLastPeriod(String roomCode);

    /**
     * 取得车位最后付费期数。
     * 
     * @param parkNo
     * @return
     */
    public String getParkLastPeriod(String parkNo);

    /**
     * 物业管理缴费
     * 
     * @param roomCode 房屋编号
     * @param lastPeriod 缴费到哪一期别
     * @return String
     */
    public String procWYGLJiaoFei(String roomCode, String lastPeriod);

    /**
     * 更新头像
     * 
     * @param mobile 手机号
     * @param images 头像文件流
     * @return String 头像路径
     */

    public String updateHeadPic(String mobile, ServletInputStream images);

    /**
     * 删除已撤销的报修或者投诉
     * 
     * @param id 报修或投诉的id
     * @return Result
     */
    public Result delRepariOrCpl(String id);

    /**
     * 删除活动或话题
     * 
     * @param id 订单id
     */
    public void deleteTopicOrActive(String id);

}
