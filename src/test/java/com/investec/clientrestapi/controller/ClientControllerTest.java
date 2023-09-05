package com.investec.clientrestapi.controller;

import com.investec.clientrestapi.dto.ClientDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class ClientControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Test
    void givenClients_GetClientsEndpoint_ShouldReturnClientList(){
        String baseUrl="http://localhost:"+port+"/clients";
        ResponseEntity<ClientDto[]> response=testRestTemplate.getForEntity(baseUrl, ClientDto[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Objects.requireNonNull(response.getBody()).length).isGreaterThanOrEqualTo(3);
    }
    @Test
    void givenNewClient_PostClientsEndpoint_ShouldAddNewClient(){
        String baseUrl="http://localhost:"+port+"/clients";
        ClientDto newClient=ClientDto.builder().firstName("Talent").lastName("Mazenge").idNumber("8605065397083").mobileNumber("0733217605").physicalAddress("207 Grant Road Norwood").build();
        ResponseEntity<ClientDto> response=testRestTemplate.postForEntity(baseUrl, newClient, ClientDto.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        ClientDto newDto=response.getBody();
        assertThat(newDto).isNotNull();
        assert newDto != null;
        assertEquals(newClient.getFirstName(),newDto.getFirstName(),"Client Firstname should be the same");
    }

    @Test
    void givenNewClientWithInvalidId_PostClientsEndpoint_ShouldThrowException(){
        String baseUrl="http://localhost:"+port+"/clients";
        ClientDto newClient=ClientDto.builder().firstName("Talent").lastName("Mazenge").idNumber("8605065397088").mobileNumber("0733217605").physicalAddress("207 Grant Road Norwood").build();
        ResponseEntity<?> response=testRestTemplate.postForEntity(baseUrl, newClient, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        String expectedMessage = "ID Number is NOT valid";
        String actualMessage = Objects.requireNonNull(response.getBody()).toString();
        assertThat(expectedMessage).isEqualTo(actualMessage);
    }

    @Test
    void givenNewClientWithDuplicateMobileNumber_PostClientsEndpoint_ShouldThrowException(){
        String baseUrl="http://localhost:"+port+"/clients";
        ClientDto newClient=ClientDto.builder().firstName("Talent").lastName("Mazenge").idNumber("8605065397083").mobileNumber("0833217605").physicalAddress("207 Grant Road Norwood").build();
        ResponseEntity<?> response=testRestTemplate.postForEntity(baseUrl, newClient, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        String expectedMessage = "Duplicate Mobile Number";
        String actualMessage = Objects.requireNonNull(response.getBody()).toString();
        assertThat(expectedMessage).isEqualTo(actualMessage);
    }

    @Test
    void givenNewClientWithoutFirstName_PostClientsEndpoint_ShouldReturnError(){
        String baseUrl="http://localhost:"+port+"/clients";
        ClientDto newClient=ClientDto.builder().lastName("Mazenge").idNumber("8605065397083").mobileNumber("0833217604").physicalAddress("207 Grant Road Norwood").build();
        ResponseEntity<?> response=testRestTemplate.postForEntity(baseUrl, newClient, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        String expectedMessage = "First Name can not be empty";
        String actualMessage = Objects.requireNonNull(response.getBody()).toString();
        assertThat(expectedMessage).isEqualTo(actualMessage);
    }
    @Test
    void givenClient_updateClientsEndpoint_ShouldReturnUpdatedClient(){
        String idNumber="8502254397083";
        String baseUrl="http://localhost:"+port+"/clients";
        ClientDto newClient=ClientDto.builder().firstName("Talent").lastName("Mashakada").idNumber("8605065397083").mobileNumber("0733217605").physicalAddress("20 Grant Road Norwood").build();
        testRestTemplate.put(baseUrl+"/update/{idNumber}", newClient, Map.of("idNumber", idNumber));
        ResponseEntity<ClientDto> response=testRestTemplate.getForEntity(baseUrl+"/idNumber/{idNumber}",ClientDto.class, Map.of("idNumber", idNumber));
        assertNotNull(response.getBody());
        assertThat(newClient.getLastName()).isEqualTo(response.getBody().getLastName());
    }

    @Test
    void givenClientIdNumber_GetClientEndpoint_ShouldReturnClient(){
        String idNumber="8502254397083";
        String baseUrl="http://localhost:"+port+"/clients/idNumber/{idNumber}";

        ResponseEntity<ClientDto> response=testRestTemplate.getForEntity(baseUrl,ClientDto.class, Map.of("idNumber", idNumber));
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        String expectedMessage = "8502254397083";
        String actualMessage = response.getBody().getIdNumber();
        assertThat(expectedMessage).isEqualTo(actualMessage);

    }

}