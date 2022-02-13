package pers.justin.preselectioncourses;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pers.justin.preselectioncourses.utils.Md5Util;

@SpringBootTest
class PreSelectionCoursesApplicationTests {

    @Test
    void contextLoads() {
        String s = Md5Util.EncoderByMd5("123");
        System.out.println(s);
        //ICy5YqxZB1uWSwcVLSNLcA==
        System.out.println(s.length());
    }

}
