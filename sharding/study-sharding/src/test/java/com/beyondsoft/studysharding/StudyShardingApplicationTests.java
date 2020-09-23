package com.beyondsoft.studysharding;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.beyondsoft.studysharding.entity.Course;
import com.beyondsoft.studysharding.entity.Dict;
import com.beyondsoft.studysharding.entity.User;
import com.beyondsoft.studysharding.mapper.CourseMapper;
import com.beyondsoft.studysharding.mapper.DictMapper;
import com.beyondsoft.studysharding.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudyShardingApplicationTests {

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DictMapper dictMapper;

    //==============================测试公共表====================================
    @Test
    public void addDict(){
        Dict dict = new Dict();
        dict.setUstatus("a");
        dict.setUvalue("已启用");
        dictMapper.insert(dict);
    }

    @Test
    public void deleteDict(){
        QueryWrapper<Dict> wrapper = new QueryWrapper<>();
        wrapper.eq("dictid",498292244155990017L);
        dictMapper.delete(wrapper);
    }


    //===============================测试垂直分库==================================
    @Test
    public void addUserDb(){
        User user = new User();
        user.setUserName("zhangsan");
        user.setUstatus("Normal");
        userMapper.insert(user);
    }

    @Test
    public void findUserDb(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",498286821667504129L);
        User user = userMapper.selectOne(wrapper);
        System.out.println(user.toString());
    }

    //===============================测试水平分库===================================
    @Test
    public void addCourseDb() {
        Course course = new Course();
        course.setCname("Java");
        course.setUserId(100L);
        course.setCstatus("Normal");
        courseMapper.insert(course);
    }
    @Test
    public void addCourseDb1() {
        Course course = new Course();
        course.setCname("Java");
        course.setUserId(101L);
        course.setCstatus("Normal");
        courseMapper.insert(course);
    }

    //查询课程的方法
    @Test
    public void findCourseDb(){
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",100L);
        wrapper.eq("cid",498276104004435969L);
        Course course = courseMapper.selectOne(wrapper);
        System.out.println(course.toString());
    }


    //===============================测试水平分表===================================

    //添加课程的方法
    @Test
    public void addCourse() {
        Course course = new Course();
        course.setCname("Java");
        course.setUserId(100L);
        course.setCstatus("Normal");
        courseMapper.insert(course);
    }

    //批量添加课程
    @Test
    public void batchAddCourse() {
        for (int i = 0; i <10 ; i++) {
            Course course = new Course();
            course.setCname("Java"+i);
            course.setUserId(100L);
            course.setCstatus("Normal"+i);
            courseMapper.insert(course);
        }
    }

    //查询课程的方法
    @Test
    public void findCourse(){
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("cid",498260737236402177L);
        Course course = courseMapper.selectOne(wrapper);
        System.out.println(course.toString());
    }
}
