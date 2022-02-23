package pers.justin.preselectioncourses;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.justin.preselectioncourses.entity.CsCourse;
import pers.justin.preselectioncourses.mapper.CsCourseMapper;
import pers.justin.preselectioncourses.service.CsCourseService;
import pers.justin.preselectioncourses.utils.Md5Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = PreSelectionCoursesApplication.class)
class PreSelectionCoursesApplicationTests {

    @Autowired
    CsCourseMapper csCourseMapper;

    @Autowired
    CsCourseService csCourseService;

    @Test
    void contextLoads() {
        String s = Md5Util.EncoderByMd5("123");
        System.out.println(s);
        //ICy5YqxZB1uWSwcVLSNLcA==
        System.out.println(s.length());
    }

    @Test
    void testOptional() throws ParseException, JsonProcessingException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        CsCourse csCourse = new CsCourse();
        csCourse.setClassAddress("A栋");
        csCourse.setClassStartTime(sdf.parse("2022-02-19 23:50:00"));
        csCourse.setClassEndTime(sdf.parse("2022-02-20 23:50:00"));
        csCourseMapper.checkConflictsByClassTimeAndAddress(csCourse);
    }

    @Test
    void testDemo() throws ParseException, JsonProcessingException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CsCourse csCourse = new CsCourse();
        csCourse.setClassAddress("A栋");
        csCourse.setClassStartTime(sdf.parse("2022-02-19 23:50:00"));
        csCourse.setClassEndTime(sdf.parse("2022-02-20 23:50:00"));
        csCourse.setUserId(2);
        Map<String, Object> map = csCourseService.addCourse(csCourse);
        System.out.println(map);
    }

    @Test
    void testDemo2() throws JsonProcessingException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", 1);
        map.put("teacher", "justin");
        System.out.println(map.remove("teacher"));
        CsCourse csCourse = new ObjectMapper().readValue(new ObjectMapper().writeValueAsString(map), CsCourse.class);
        System.out.println(csCourse);
        System.out.println(new ObjectMapper().writeValueAsString(map));

    }

}
