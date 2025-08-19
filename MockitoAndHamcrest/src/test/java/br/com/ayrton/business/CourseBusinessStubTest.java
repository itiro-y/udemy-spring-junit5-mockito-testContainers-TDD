package br.com.ayrton.business;

import br.com.ayrton.CourseBusiness;
import br.com.ayrton.service.CourseService;
import br.com.ayrton.service.stub.CourseServiceStub;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseBusinessStubTest {

    @Test
    void testRetrieveCourseSpring_WhenUsingAStub(){
        CourseService stubService = new CourseServiceStub();
        CourseBusiness business = new CourseBusiness(stubService);

        List<String> filteredCourses = business.retrieveCourseSpring("Ayrton");

        assertEquals(filteredCourses.size(), 4, () -> "The filtered Spring courses should have 4 elements!");
    }

    @Test
    void testRetrieveCourseSpring_WhenUsingAFooBarStudent(){
        CourseService stubService = new CourseServiceStub();
        CourseBusiness business = new CourseBusiness(stubService);

        List<String> filteredCourses = business.retrieveCourseSpring("Foo Bar");

        assertEquals(filteredCourses.size(), 0, () -> "The filtered Spring courses should have 0 elements!");
    }
}