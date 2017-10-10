// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sf.json.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.baidu.yun.push.auth.PushKeyPair;
import com.baidu.yun.push.client.BaiduPushClient;
import com.baidu.yun.push.constants.BaiduPushConstants;
import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.baidu.yun.push.model.PushMsgToAllRequest;
import com.baidu.yun.push.model.PushMsgToAllResponse;
import com.youthen.framework.common.DateFormatUtils;
import com.youthen.framework.common.StringUtils;
import com.youthen.framework.common.context.SessionContext;
import com.youthen.framework.common.exception.DuplicateKeyException;
import com.youthen.framework.util.BeanUtils;
import com.youthen.lj.persistence.dao.LjNoticeDao;
import com.youthen.lj.persistence.entity.LjNotice;
import com.youthen.lj.service.LjNoticeService;
import com.youthen.lj.service.dto.LjNoticeDto;
import com.youthen.lj.utils.JsonUtils;
import com.youthen.master.util.CommonUtil;

/**
 * 。
 * 
 * @author gong
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
@Service(value = "ljNoticeService")
public class LjNoticeServiceImpl implements LjNoticeService {

    @Autowired
    LjNoticeDao noticedao;

    /**
     * 所查东西 活动名 活动内容 活动开始时间 活动结束时间 活动地点
     * list列表
     * 
     * @see com.youthen.lj.service.LjNoticeService#getLjNoticeList(com.youthen.lj.persistence.dao.LjNoticeDao)
     */

    @SuppressWarnings("javadoc")
    @Override
    public List<LjNoticeDto> getLjNoticeList(final LjNoticeDto noticeDto) {

        String hql = "from LjNotice where 1=1 ";

        final String orderBy = "order by status desc, isTop desc,createTime desc, startDate desc";

        if (StringUtils.isNotEmpty(noticeDto.getName())) {
            hql += " and name like '%" + noticeDto.getName() + "%'";
        }

        if (StringUtils.isNotEmpty(noticeDto.getTheContent())) {
            hql += " and theContent like '%" + noticeDto.getTheContent() + "%'";
        }
        if (StringUtils.isNotEmpty(noticeDto.getCreaterId())) {
            hql += " and createrId = '" + noticeDto.getCreaterId() + "'";
        }
        if (noticeDto.getStartDate() != null) {
            hql +=
                    " and startDate between '" + CommonUtil.dateToStrLong(noticeDto.getStartDate()) + "'"
                            + " and '" + CommonUtil.dateToStr(noticeDto.getStartDate()) + " 23:59:59'  ";
        }

        if (noticeDto.getStatus() != null) {
            hql += " and status ='" + noticeDto.getStatus() + "'";
        } else {
            hql += " and status ='1'";
        }
        if (noticeDto.getType() != null) {
            hql += " and type='" + noticeDto.getType() + "'";
        }
        hql += orderBy;
        final List<LjNotice> list =
                this.noticedao.getByPage(hql, noticeDto.getGotoPage(), noticeDto.getPageSize());
        final ArrayList<LjNoticeDto> result = new ArrayList<LjNoticeDto>();

        if (CollectionUtils.isNotEmpty(list)) {
            for (final LjNotice entity : list) {
                final LjNoticeDto dto = new LjNoticeDto();
                BeanUtils.copyAllNullableProperties(entity, dto);
                result.add(dto);

            }

        }
        return result;
    }

    /**
     * 一共多少条数据
     * 
     * @see com.youthen.lj.service.LjNoticeService#getNotice(com.youthen.lj.service.dto.LjNoticeDto)
     */

    @Override
    public int getNoticeCount(final LjNoticeDto noticeDto) {

        String hql = "from LjNotice where 1=1 ";

        final String orderBy = "order by status desc, isTop desc,createTime desc, startDate desc";
        if (StringUtils.isNotEmpty(noticeDto.getName())) {
            hql += " and name like '%" + noticeDto.getName() + "%'";
        }

        if (StringUtils.isNotEmpty(noticeDto.getTheContent())) {
            hql += " and theContent like '%" + noticeDto.getTheContent() + "%'";
        }

        if (noticeDto.getStartDate() != null) {
            hql +=
                    " and startDate between '" + CommonUtil.dateToStrLong(noticeDto.getStartDate()) + "'"
                            + " and '" + CommonUtil.dateToStr(noticeDto.getStartDate()) + " 23:59:59'  ";
        }

        if (noticeDto.getStatus() != null) {
            hql += " and status ='" + noticeDto.getStatus() + "'";
        }
        if (noticeDto.getType() != null) {
            hql += " and type='" + noticeDto.getType() + "'";
        }
        hql += orderBy;
        return this.noticedao.getCount(hql);
    }

    /**
     * 添加
     * 
     * @see com.youthen.lj.service.LjNoticeService#insert(com.youthen.lj.service.dto.LjNoticeDto)
     */
    @Override
    @Transactional
    public Long insert(final LjNoticeDto noticeDto) throws DuplicateKeyException {
        try {
            final LjNotice ljNotice = new LjNotice();
            BeanUtils.copyAllNullableProperties(noticeDto, ljNotice);
            ljNotice.setStatus(1);
            ljNotice.setIsTop(0);
            ljNotice.setCreateTime(new Date());
            if (SessionContext.getUser() != null) {
                ljNotice.setCreaterId(SessionContext.getUser().getUserId());
            }

            if (noticeDto.getType() == 9) {
                pushMessageToAndroid(noticeDto.getName(), noticeDto.getTheContent());
                pushMessageToIOS(noticeDto.getTheContent());
            }

            return (Long) this.noticedao.insert(ljNotice);
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Long push(final LjNoticeDto noticeDto) throws PushClientException, PushServerException {
        final LjNotice ljNotice = new LjNotice();
        BeanUtils.copyAllNullableProperties(noticeDto, ljNotice);
        if (noticeDto.getType() == 9) {
            pushMessageToAndroid(noticeDto.getName(), noticeDto.getTheContent());
            pushMessageToIOS(noticeDto.getTheContent());
        }
        return null;
    }

    /**
     * 向android端推送消息
     * 
     * @param title 推送的标题
     * @param message 推送的内容
     * @return
     * @throws PushClientException
     * @throws PushServerException
     */

    private boolean pushMessageToAndroid(final String title, final String message) throws PushClientException,
            PushServerException {
        final String apiKey = "Z2tqb9SAweLBAmYMa6Q7G3l58RLUt5jI";
        final String secretKey = "sdcssLtGyKw5IcVqW7kTRIbgP7GlkVHx";
        final PushKeyPair pair = new PushKeyPair(apiKey, secretKey);
        // 2. build a BaidupushClient object to access released interfaces
        final BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);
        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {

            @Override
            public void onHandle(final YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });
        try {
            final JSON json =
                    JsonUtils.stringToObject("{\"title\":\"" + title + "\",\"description\":\"" + message + "\"}");

            // 4. specify request arguments
            final PushMsgToAllRequest request = new PushMsgToAllRequest()
                    .addMsgExpires(new Integer(3600)).addMessageType(1)
                    .addMessage(json.toString())
                    // 设置定时推送时间，必需超过当前时间一分钟，单位秒.实例70秒后推送
                    .addSendTime(System.currentTimeMillis() / 1000 + 70).
                    addDeviceType(3);
            // 5. http request
            final PushMsgToAllResponse response = pushClient.pushMsgToAll(request);
            // Http请求返回值解析
            System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
                    + response.getSendTime() + ",timerId: "
                    + response.getTimerId());
        } catch (final PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (final PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMsg: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
        return false;
    }

    /**
     * 推送消息到IOS端
     * 。
     * 
     * @param message
     * @return
     * @throws PushClientException
     * @throws PushServerException
     */
    private boolean pushMessageToIOS(final String message) throws PushClientException, PushServerException {
        final String apiKey = "ZG58VkA2wnijNmquvXEKQNBg";
        final String secretKey = "wbGmtpH6IFMjMtGN1Wb0UpzWwFiK0UFr";
        final PushKeyPair pair = new PushKeyPair(apiKey, secretKey);
        // 2. build a BaidupushClient object to access released interfaces
        final BaiduPushClient pushClient = new BaiduPushClient(pair,
                BaiduPushConstants.CHANNEL_REST_URL);
        // 3. register a YunLogHandler to get detail interacting information
        // in this request.
        pushClient.setChannelLogHandler(new YunLogHandler() {

            @Override
            public void onHandle(final YunLogEvent event) {
                System.out.println(event.getMessage());
            }
        });
        try {
            final JSON json = JsonUtils.stringToObject("{\"description\":\"" + message + "\"}");

            // 4. specify request arguments
            final PushMsgToAllRequest request = new PushMsgToAllRequest()
                    .addMsgExpires(new Integer(3600)).addMessageType(1)
                    .addMessage(json.toString())
                    // 设置定时推送时间，必需超过当前时间一分钟，单位秒.实例70秒后推送
                    .addSendTime(System.currentTimeMillis() / 1000 + 70).
                    addDeviceType(4);
            // 5. http request
            final PushMsgToAllResponse response = pushClient.pushMsgToAll(request);
            // Http请求返回值解析
            System.out.println("msgId: " + response.getMsgId() + ",sendTime: "
                    + response.getSendTime() + ",timerId: "
                    + response.getTimerId());
        } catch (final PushClientException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                e.printStackTrace();
            }
        } catch (final PushServerException e) {
            if (BaiduPushConstants.ERROROPTTYPE) {
                throw e;
            } else {
                System.out.println(String.format(
                        "requestId: %d, errorCode: %d, errorMsg: %s",
                        e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
            }
        }
        return false;
    }

    /**
     * 根据ID
     * 
     * @see com.youthen.lj.service.LjNoticeService#getById(java.lang.Long)
     */
    @Override
    public LjNoticeDto getById(final Long Id) {
        final LjNoticeDto dto = new LjNoticeDto();
        BeanUtils.copyAllProperties(this.noticedao.getById(Id), dto);
        dto.setUpdateTime(DateFormatUtils.format("yyyy-MM-dd HH:mm:ss", new Date()));
        return dto;
    }

    /**
     * 修改
     * 
     * @see com.youthen.lj.service.LjNoticeService#update(com.youthen.lj.service.dto.LjNoticeDto)
     */
    @Override
    @Transactional
    public LjNoticeDto update(final LjNoticeDto noticeDto) {
        final LjNotice ljNotice = this.noticedao.getById(noticeDto.getId());
        if (noticeDto.getType() == 9) {
            try {
                pushMessageToAndroid(noticeDto.getName(), noticeDto.getTheContent());
                pushMessageToIOS(noticeDto.getTheContent());
            } catch (final PushClientException e) {
                e.printStackTrace();
            } catch (final PushServerException e) {
                e.printStackTrace();
            }
        }
        BeanUtils.copyNullableProperties(noticeDto, ljNotice);
        this.noticedao.update(ljNotice);
        BeanUtils.copyProperties(ljNotice, noticeDto);
        return noticeDto;
    }

    /**
     * @see com.youthen.lj.service.LjNoticeService#delete(java.lang.Long)
     */

    // // 删除
    // @Override
    // public void delete(final LjNoticeDto noticeDto) {
    // // 假删
    // final LjNotice ljNotice = this.ndao.getById(noticeDto.getId());
    // ljNotice.setStatus(0);
    // // ljNotice.setEndDate(noticeDto.getEndDate());
    // this.ndao.update(ljNotice);
    // BeanUtils.copyProperties(ljNotice, noticeDto);
    //
    // // 真删
    // // final LjNotice e = this.ndao.getById(noticeDto.getId());
    // // BeanUtils.copyNullableProperties(noticeDto, e);
    // // try {
    // // this.ndao.delete(e);
    // // } catch (final ObjectNotFoundException e1) {
    // // e1.printStackTrace();
    // // } catch (final OptimisticLockStolenException e1) {
    // // e1.printStackTrace();
    // // }
    // }

    // 模糊查询
    // @Override
    // public List<LjNoticeDto> getNoticeNames(final LjNoticeDto noticeDto) {
    // String hql = " from LjNotice where 1=1 ";
    // final String orderBy = " order by status asc,reportTime desc";
    // if (noticeDto.getType() != null) {
    // hql += " and type='" + noticeDto.getType() + "'";
    // }
    // if (noticeDto.getName() != null) {
    // hql += " and name like'%" + noticeDto.getName() + "%'";
    // }
    // hql += orderBy;
    // final List<LjNotice> list = this.ndao.getByHql(hql);
    // final ArrayList<LjNoticeDto> lists = new ArrayList<LjNoticeDto>();
    // if (CollectionUtils.isNotEmpty(list)) {
    // for (final LjNotice kbn : list) {
    // final LjNoticeDto kbnDto = new LjNoticeDto();
    // BeanUtils.copyAllNullableProperties(kbn, kbnDto);
    // lists.add(kbnDto);
    // }
    // }
    // return lists;
    // }
}
