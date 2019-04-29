package com.ruicheng.service.impl;

import com.ruicheng.config.WeChatUserConfig;
import com.ruicheng.dto.OrderDTO;
import com.ruicheng.service.interfaces.DeliveryMessage;
import com.ruicheng.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Ruicheng
 * on 2019/4/23.
 */
@Service
@Slf4j
public class DeliveryMessageImpl implements DeliveryMessage{

    @Autowired
    private WxMpService wxMpService;

    @Autowired
    private WeChatUserConfig userConfig;

    @Override
    public void onOrderStatus(OrderDTO orderDTO) {
        WxMpTemplateMessage templateMessage = new WxMpTemplateMessage();
        List<WxMpTemplateData> dataList = Arrays.asList(
                new WxMpTemplateData("first", "您所购买的货物已派单"),
                new WxMpTemplateData("keyword1", MessageUtil.desplayPurchaseDetails(orderDTO.getOrderDetailList())),
                new WxMpTemplateData("keyword2", orderDTO.getOrderId()),
                new WxMpTemplateData("keyword3", "食品"),
                new WxMpTemplateData("keyword4", "￥" + orderDTO.getOrderAmount()),
                new WxMpTemplateData("keyword5", orderDTO.getCreateTime().toString()),
                new WxMpTemplateData("remark", "感谢光临！欢迎下次惠顾")
        );
        templateMessage.setTemplateId(userConfig.getTemplateId().get("orderStatus"));  //模板ID
        templateMessage.setToUser(orderDTO.getBuyerOpenid());  // OPENID
        templateMessage.setData(dataList);
        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            log.error("[微信模板信息推送] 发送模板信息失败");
            e.printStackTrace();
        }
    }
}
