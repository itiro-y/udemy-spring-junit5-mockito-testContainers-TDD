package br.com.ayrton;

import br.com.ayrton.service.CourseService;

import java.util.ArrayList;
import java.util.List;

// SUT - System Under Test
public class CourseBusiness {

    // CourseService is a dependency
    private CourseService courseService;

    public CourseBusiness(CourseService courseService) {
        this.courseService = courseService;
    }

    public List<String> retrieveCourseSpring(String student){
        List<String> filteredCourses = new ArrayList<>();
        if("Foo Bar".equals(student)){
            return filteredCourses;
        }
        List<String> allCourses = courseService.retrieveCourses(student);

        for(String course: allCourses){
            if(course.contains("Spring")){
                filteredCourses.add(course);
            }
        }
        return filteredCourses;
    }

    public void deleteCourseNotSpring(String student) {
        List<String> allCourses = courseService.retrieveCourses(student);

        for(String course: allCourses){
            if(!course.contains("Spring")){
                courseService.deleteCourse(course);
            }
        }
    }
}
