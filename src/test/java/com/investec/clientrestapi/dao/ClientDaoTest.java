package com.investec.clientrestapi.dao;


import com.investec.clientrestapi.dto.ClientDto;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class ClientDaoTest {
    @Test
    void givenPrePopulatedData_getClients_shouldReturnClientList() {
        ClientDao clientDao = new ClientDao();
        assertThat(clientDao.getClients().size()).isEqualTo(3);
    }

    @Test
    void givenANewClientDto_addClient_shouldAddAndReturnDtoWithClientId() {
        ClientDao clientDao = new ClientDao();
        assertThat(clientDao.getClients().size()).isEqualTo(3);
        ClientDto newClient = ClientDto.builder().firstName("Talent").lastName("Mazenge").idNumber("8605065397083").mobileNumber("0733217605").physicalAddress("207 Grant Road Norwood").build();
        ClientDto createdDto = clientDao.addClient(newClient);
        assertThat(clientDao.getClients().size()).isEqualTo(4);
        assertThat(createdDto.getId()).isNotNull();
    }

}