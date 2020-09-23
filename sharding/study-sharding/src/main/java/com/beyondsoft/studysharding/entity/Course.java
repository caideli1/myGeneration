package com.beyondsoft.studysharding.entity;

import lombok.Data;

/**
 * Copyright: Copyright (c) 2020 Asiainfo
 *
 * @ClassName: com.beyondsoft.studysharding.entity.Course
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: wusd
 * @date: 2020/8/6 19:08
 */
@Data
public class Course {

    private Long cid;
    private String cname;
    private Long userId;
    private String cstatus;
}
