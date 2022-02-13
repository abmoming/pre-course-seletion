package pers.justin.preselectioncourses;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("pers.justin.preselectioncourses.mapper")
public class PreSelectionCoursesApplication {

    public static void main(String[] args) {
        SpringApplication.run(PreSelectionCoursesApplication.class, args);
    }

}
