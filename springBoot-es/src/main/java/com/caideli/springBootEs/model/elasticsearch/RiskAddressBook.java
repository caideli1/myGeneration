package com.caideli.springBootEs.model.elasticsearch;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * 通讯录
 *
 * @author walle
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "risk_address_book_v1", type = "riskAddressBook", replicas = 1, createIndex = false)
public class RiskAddressBook {

    @Id
    private Long id;

    /**
     * 订单编号
     */
    @Field(type = FieldType.Keyword)
    private Long orderNo;

    /**
     * 所属国家/地区
     */
    @Field
    private String region;

    /**
     * 电话号
     */
    @Field(type = FieldType.Keyword)
    private String phone;

    /**
     * 联系人
     */
    @Field(type = FieldType.Keyword)
    private String name;

    /**
     * 亲属关系
     */
    @Field(type = FieldType.Long)
    private Long relation;

    /**
     * 同一号码重复次数
     */
    @Field(type = FieldType.Long)
    private Long repeatCount;

    /**
     * 联系次数
     */
    @Field(type = FieldType.Long)
    private Long contactCount;

    /**
     * 最后联系时间
     */
    @Field(type = FieldType.Date)
    private Date lastContactTime;

    /**
     * 创建时间
     */
    @Field(type = FieldType.Date)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 更新时间
     */
    @Field(type = FieldType.Date)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
