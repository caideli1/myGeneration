package com.beyondsoft.studysharding;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.beyondsoft.studysharding.mapper")
public class StudyShardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyShardingApplication.class, args);
    }

}
