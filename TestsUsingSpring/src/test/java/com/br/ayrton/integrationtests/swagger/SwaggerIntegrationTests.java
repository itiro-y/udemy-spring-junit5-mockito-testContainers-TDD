package com.br.ayrton.integrationtests.swagger;

import com.br.ayrton.config.TestConfigs;
import com.br.ayrton.integrationtests.testcontainers.AbstractIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT) //8888 according to the yml
public class SwaggerIntegrationTests extends AbstractIntegrationTest {

    @DisplayName("JUnit test for Swagger UI page")
    @Test
    void testShouldDisplaySwaggerUiPage(){
        var content = given().basePath("/swagger-ui/index.html") // Given
                        .port(TestConfigs.SERVER_PORT)
                        .when().get()  // When
                        .then().statusCode(200)  // Then
                        .extract().body().asString();

        assertTrue(content.contains("Swagger UI"), () -> "Swagger UI should be present in the response body");
    }
}
