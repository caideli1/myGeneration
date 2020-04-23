package com.caideli.monggodb.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * 通讯录
 *
 * @author caideli
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RiskAddressBook implements Serializable {

    @Id
    private Long id;

    /**
     * 订单编号
     */
    private Long orderNo;

    /**
     * 所属国家/地区
     */
    private String region;

    /**
     * 电话号
     */
    private String phone;

    /**
     * 联系人
     */
    private String name;

    /**
     * 亲属关系
     */
    private Long relation;

    /**
     * 同一号码重复次数
     */
    private Long repeatCount;

    /**
     * 联系次数
     */
    private Long contactCount;

    /**
     * 最后联系时间
     */
    private Date lastContactTime;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
