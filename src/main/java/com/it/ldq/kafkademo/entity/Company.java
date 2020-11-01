package com.it.ldq.kafkademo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @Auther: ldq
 * @Date: 2020/3/1
 * @Description:
 * @Version: 1.0
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Company {
    private int cid;
    private String cname;
    //消息
    private String msg;
    //时间戳
    private Date sendTime;
}
