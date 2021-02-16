package cz.zpapez.lcs;


import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class})
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class IntegrationTest {

    @LocalServerPort private int port;

    @Test
    public void testPostDiffTwoFiles() throws IOException {

        given()
            .port(port)
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .multiPart("file1",new File("./src/test/resources/IntegrationTest/testPostDiffTwoFiles/file1.txt"))
            .multiPart("file2",new File("./src/test/resources/IntegrationTest/testPostDiffTwoFiles/file2.txt"))
            .post("/api/v1/upload")
            .then()
            .log()
            .all()
            .statusCode(HttpStatus.OK.value())
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body("size()", equalTo(11))
            .body("[0].type", equalTo(""))
            .body("[0].string", equalTo("Lo"))
            .body("[1].type", equalTo("-"))
            .body("[1].string", equalTo("AAA"))
            .body("[2].type", equalTo(""))
            .body("[2].string", equalTo("rem "))
            .body("[3].type", equalTo("+"))
            .body("[3].string", equalTo("ZZZ "))
            .body("[4].type", equalTo(""))
            .body("[4].string", equalTo("ip"))
            .body("[5].type", equalTo("-"))
            .body("[5].string", equalTo("BBB"))
            .body("[6].type", equalTo(""))
            .body("[6].string", equalTo("sum "))
            .body("[7].type", equalTo("-"))
            .body("[7].string", equalTo("dolor "))
            .body("[8].type", equalTo(""))
            .body("[8].string", equalTo("sit amet"))
            .body("[9].type", equalTo("+"))
            .body("[9].string", equalTo(" XXX"))
            .body("[10].type", equalTo(""))
            .body("[10].string", equalTo("\n"));
    }

}