package com.caideli.springBootEs.service.elasticsearch;


import org.springframework.data.domain.Pageable;
import com.caideli.springBootEs.model.elasticsearch.RiskAddressBook;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 通讯录
 * @author walle
 */
public interface RiskAddressBookRepository extends ElasticsearchRepository<RiskAddressBook, Long> {

    List<RiskAddressBook> findAllByOrderNo(Long orderNo, Pageable pageable);

    List<RiskAddressBook> findAllByPhone(String phone, Pageable pageable);

}
