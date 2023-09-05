package com.investec.clientrestapi.service;

import com.investec.clientrestapi.dto.ClientDto;

import java.util.List;

public interface ClientService {
    ClientDto addClient(ClientDto clientDto);
    List<ClientDto> getClients();
    ClientDto getClientByMobileNumber(String mobileNumber);
    ClientDto getClientByIdNumber(String idNumber);
    List<ClientDto> getClientByFirstName(String firstName);
    ClientDto updateClient(ClientDto client, String idNumber);
}
