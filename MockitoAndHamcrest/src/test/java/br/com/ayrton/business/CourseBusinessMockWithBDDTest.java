package br.com.ayrton.business;

import br.com.ayrton.CourseBusiness;
import br.com.ayrton.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;

class CourseBusinessMockWithBDDTest {
    CourseService mockService;
    CourseBusiness business;
    List<String> courses;

    @BeforeEach
    void setup(){
        mockService = mock(CourseService.class);
        business = new CourseBusiness(mockService);
        courses = Arrays.asList(
                "REST API's RESTFul do 0 à Azure com ASP.NET Core 5 e Docker",
                "Agile Desmistificado com Scrum, XP, Kanban e Trello",
                "Spotify Engineering Culture Desmistificado",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker",
                "Docker do Zero à Maestria - Contêinerização Desmistificada",
                "Docker para Amazon AWS Implante Apps Java e .NET com Travis CI",
                "Microsserviços do 0 com Spring Cloud, Spring Boot e Docker",
                "Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Kotlin e Docker",
                "Kotlin para DEV's Java: Aprenda a Linguagem Padrão do Android",
                "Microsserviços do 0 com Spring Cloud, Kotlin e Docker"
        );
    }

    @Test
    void testRetrieveCourseSpring_WhenUsingAMock(){
        // Using BDD style to define the behavior of the mock
        given(mockService.retrieveCourses("Ayrton")).willReturn(courses);

        List<String> filteredCourses = business.retrieveCourseSpring("Ayrton");

        // Using Hamcrest to assert the size of the filtered courses
        // More user-friendly assertion, but not necessarily better than JUnit's assertEquals
        assertThat(filteredCourses.size(), is(4));
    }

    @Test
    void testDeleteCourseNotSpring_UsingMockitoVerify(){
        given(mockService.retrieveCourses("Ayrton")).willReturn(courses);

        business.deleteCourseNotSpring("Ayrton");

        //verify(mockService).deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
        verify(mockService, times(1))
                .deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
        verify(mockService, atLeast(1))
                .deleteCourse("Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#");
        verify(mockService, atLeastOnce())
                .deleteCourse("Kotlin para DEV's Java: Aprenda a Linguagem Padrão do Android");
        verify(mockService, never())
                .deleteCourse("REST API's RESTFul do 0 à AWS com Spring Boot 3 Kotlin e Docker");
    }

    @Test
    void testDeleteCourseNotSpring_UsingMockitoVerifyV2(){
        given(mockService.retrieveCourses("Ayrton")).willReturn(courses);
        String agileCourse = "Agile Desmistificado com Scrum, XP, Kanban e Trello";
        String restKotlinCourse = "REST API's RESTFul do 0 à AWS com Spring Boot 3 Kotlin e Docker";

        business.deleteCourseNotSpring("Ayrton");


        then(mockService)
                .should()
                    .deleteCourse(agileCourse);
        then(mockService)
                .should(never())
                    .deleteCourse(restKotlinCourse);
    }

    @Test
    void testDeleteCourseNotSpring_CapturingArguments(){
        courses = Arrays.asList(
                "Agile Desmistificado com Scrum, XP, Kanban e Trello",
                "Microsserviços do 0 com Spring Cloud, Kotlin e Docker"
        );

        given(mockService.retrieveCourses("Ayrton")).willReturn(courses);

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        String agileCourse = "Agile Desmistificado com Scrum, XP, Kanban e Trello";

        business.deleteCourseNotSpring("Ayrton");


        then(mockService)
                .should()
                .deleteCourse(argumentCaptor.capture());

        assertThat(argumentCaptor.getValue(), is(agileCourse));
    }

    @Test
    void testDeleteCourseNotSpring_CapturingArgumentsV2(){

        given(mockService.retrieveCourses("Ayrton")).willReturn(courses);

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);

        business.deleteCourseNotSpring("Ayrton");


        then(mockService)
                .should(times(7))
                .deleteCourse(argumentCaptor.capture());

        assertThat(argumentCaptor.getAllValues().size(), is(7));
    }

}