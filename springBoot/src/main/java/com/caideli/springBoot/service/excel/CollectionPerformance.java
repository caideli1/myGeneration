package com.caideli.springBoot.service.excel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * @Author : caideli
 * @Email : 1595252552@qq.com
 * @Date : 2020/4/27 10:45
 * 描述：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollectionPerformance implements Serializable {
    /**
     * 主键id
     */
    private Integer id;

    /**
     * 日期
     */
    private LocalDate reportDate;

    /**
     * 催收人名称
     */
    private String collectionUserName;

    /**
     * 崔收员id
     */
    private Integer collectionUserId;

    /**
     * 日委案本金，每天主管分配给催员的所有案件中逾期订单的应还本金总和
     */
    private BigDecimal principalEntrustAmount;

    /**
     * 日回款本金，催员每天催回的所有案件中逾期订单的应还本金总和。展期或者部分还款，只要用户有还款行为的意向，都按照催回本金计入日回款本金的统计
     */
    private BigDecimal principalReturnAmount;

    /**
     * 日催回总额，催员每天催回的所有案件中逾期订单的回款总额，=日回款本金每天实还利息每天实还罚息
     */
    private BigDecimal totalReturnAmount;

    /**
     * 日回款罚息，催员每天催回的所有案件中逾期订单的实还罚息总和
     */
    private BigDecimal InterestReturnAmount;

    /**
     * 日跟进案件，主管每天分配给催员的所有案件数量
     */
    private Integer colletciontAssignCount;

    /**
     * 日还款案件，催员每天催回的案件数量，展期或者部分还款，只要用户有还款行为的意向，都按照催回案件计入日还款案件统计。
     */
    private Integer collectionReturnCount;

    /**
     * create_time
     */
    private Date createTime;

    /**
     * update_time
     */
    private Date updateTime;
}
