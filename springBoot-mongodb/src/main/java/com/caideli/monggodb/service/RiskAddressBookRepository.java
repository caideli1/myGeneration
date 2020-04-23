package com.caideli.monggodb.service;


import com.caideli.monggodb.model.RiskAddressBook;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * 通讯录
 * @author caideli
 */
public interface RiskAddressBookRepository extends MongoRepository<RiskAddressBook, Long> {

    List<RiskAddressBook> findAllByOrderNo(Long orderNo, Pageable pageable);

    List<RiskAddressBook> findAllByPhone(String phone, Pageable pageable);

}
