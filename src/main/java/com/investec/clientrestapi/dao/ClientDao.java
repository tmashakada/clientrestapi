package com.investec.clientrestapi.dao;

import com.investec.clientrestapi.dto.ClientDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClientDao {
    static  int id=100;

  private final List<ClientDto> clientsList=new ArrayList<>();
    public ClientDao() {
        clientsList.add(ClientDto.builder().id(createAndGetId()).firstName("Tafadzwa").lastName("Mash").idNumber("8502254397083").mobileNumber("0833217605").physicalAddress("201 Grant Road Norwood").build());
        clientsList.add(ClientDto.builder().id(createAndGetId()).firstName("Emmanuel").lastName("Mashakada").idNumber("8003145497083").mobileNumber("0833217605").physicalAddress("201 Grant Road Norwood").build());
        clientsList.add(ClientDto.builder().id(createAndGetId()).firstName("Lesley").lastName("Madiba").idNumber("2001014800086").mobileNumber("0833217605").physicalAddress("201 Grant Road Norwood").build())  ;
    }

    private int createAndGetId() {
        return id++;
    }

    public List<ClientDto> getClients() {

        return clientsList;

    }

    public ClientDto addClient(ClientDto clientDto){
        clientDto.setId(createAndGetId());
        clientsList.add(clientDto);
        return clientDto;
    }
}
