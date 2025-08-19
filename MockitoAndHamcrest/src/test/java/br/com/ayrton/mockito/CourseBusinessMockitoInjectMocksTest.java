package br.com.ayrton.mockito;

import br.com.ayrton.CourseBusiness;
import br.com.ayrton.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class CourseBusinessMockitoInjectMocksTest {

    @Mock
    CourseService mockService;

    @InjectMocks
    CourseBusiness business;

    @Captor
    ArgumentCaptor<String> argumentCaptor;

    List<String> courses;

    @BeforeEach
    void setup(){
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
        given(mockService.retrieveCourses("Ayrton")).willReturn(courses);

        List<String> filteredCourses = business.retrieveCourseSpring("Ayrton");

        assertThat(filteredCourses.size(), is(4));
    }

    @Test
    void testDeleteCourseNotSpring_UsingMockitoVerify(){
        given(mockService.retrieveCourses("Ayrton")).willReturn(courses);

        business.deleteCourseNotSpring("Ayrton");

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

        business.deleteCourseNotSpring("Ayrton");

        then(mockService)
                .should(times(7))
                .deleteCourse(argumentCaptor.capture());
        assertThat(argumentCaptor.getAllValues().size(), is(7));
    }

}