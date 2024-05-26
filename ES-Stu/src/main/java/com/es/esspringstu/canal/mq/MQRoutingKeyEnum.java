package com.es.esspringstu.canal.mq;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MQRoutingKeyEnum {


    SEND_FENPEI_CANAL("canal.mysql_es", "数据同步通知");
    private String routingKey;
    private String routingName;
}