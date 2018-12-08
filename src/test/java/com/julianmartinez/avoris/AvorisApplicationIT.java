package com.julianmartinez.avoris;

import com.julianmartinez.avoris.model.messages.UserRQ;
import com.julianmartinez.avoris.model.messages.UserRS;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AvorisApplicationIT {

    @LocalServerPort
    private int port;

    private TestRestTemplate testRestTemplate = new TestRestTemplate();
    private HttpHeaders headers = new HttpHeaders();

    private final String HOST = "http://localhost:";

    @Test
    public void testPostUser(){
        UserRQ request = new UserRQ();
        request.setName("john doe");

        HttpEntity<UserRQ> entity = new HttpEntity<>(request, headers);
        ResponseEntity<UserRS> response = testRestTemplate.exchange(
                HOST + port + "/users", HttpMethod.POST, entity, UserRS.class);

        Assert.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testGetUser(){
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<UserRS> response = testRestTemplate.exchange(
                HOST + port + "/users/1", HttpMethod.GET, entity, UserRS.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }


    @Test
    public void testGetUserList(){
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<List<UserRS>> response = testRestTemplate.exchange(
                HOST + port + "/users", HttpMethod.GET, entity, (Class<List<UserRS>>) (Class) List.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testPutUser(){
        UserRQ request = new UserRQ(1L, "foo");

        HttpEntity<UserRQ> entity = new HttpEntity<>(request, headers);
        ResponseEntity response = testRestTemplate.exchange(
                HOST + port + "/users/1", HttpMethod.PUT, entity, ResponseEntity.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteUser(){
        HttpEntity<UserRQ> entity = new HttpEntity<>(null, headers);
        ResponseEntity response = testRestTemplate.exchange(
                HOST + port + "/users/1", HttpMethod.DELETE, entity, ResponseEntity.class);

        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
