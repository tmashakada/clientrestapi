package com.investec.clientrestapi.service;

import com.investec.clientrestapi.dao.ClientDao;
import com.investec.clientrestapi.dto.ClientDto;
import com.investec.clientrestapi.exception.ClientNotFoundException;
import com.investec.clientrestapi.exception.DuplicateRecordException;
import com.investec.clientrestapi.exception.InvalidIdNumberException;
import com.investec.clientrestapi.exception.MandotoryFieldsException;
import com.investec.clientrestapi.util.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

@AllArgsConstructor
public class ClientServiceImpl implements ClientService{

    private  final ClientDao clientDao;
    @Override
    public ClientDto addClient(ClientDto clientDto) {
         checkMondatoryFields(clientDto);
         validateData(clientDto);
         return clientDao.addClient(clientDto);
    }


    @Override
    public ClientDto getClientByIdNumber(String idNumber) {

        return getClients().stream()
                .filter( clientDto -> clientDto.getIdNumber().equals(idNumber)).findFirst().orElse(null);

    }

    @Override
    public List<ClientDto> getClientByFirstName(String firstName) {
        return getClients().stream()
        .filter(clientDto -> clientDto.getFirstName().equalsIgnoreCase(firstName))
                .toList();
    }

    @Override
    public ClientDto updateClient(ClientDto client, String idNumber) {
        checkMondatoryFields(client);
        validateData(client);
        ClientDto clientDto = Optional.ofNullable(getClientByIdNumber(idNumber))
                .orElseThrow(() -> new ClientNotFoundException("Client Does not exist for the given ID Number"));
        if(client.getMobileNumber()!=null) {
            if (clientDto.getMobileNumber() == null) {
                if (isMobileNumberExist(client.getMobileNumber())){
                   throw new DuplicateRecordException("Duplicate Mobile Number")  ;
                }
                clientDto.setMobileNumber(client.getMobileNumber());
            }else{
                if (!client.getMobileNumber().equals(clientDto.getMobileNumber())) {
                    if (isMobileNumberExist(client.getMobileNumber())){
                        throw new DuplicateRecordException("Duplicate Mobile Number");
                    }
                    clientDto.setMobileNumber(client.getMobileNumber());
                }
            }
        }
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setPhysicalAddress(client.getPhysicalAddress());
        return clientDto;
    }

    @Override
    public List<ClientDto> getClients() {
        return clientDao.getClients();
    }

    @Override
    public ClientDto getClientByMobileNumber(String mobileNumber) {

      return   getClients().stream()
                .filter( clientDto -> clientDto.getMobileNumber().equals(mobileNumber)).findFirst().orElse(null);

    }

    private void validateData(ClientDto clientDto) {
        if(!Validator.isIdNumberValid(clientDto.getIdNumber())){
            throw  new InvalidIdNumberException("ID Number is NOT valid");
        }
        if(isIdNumberExist(clientDto.getIdNumber())){
            throw  new DuplicateRecordException("Duplicate ID Number");
        }

        if(isMobileNumberExist(clientDto.getMobileNumber())){
            throw  new DuplicateRecordException("Duplicate Mobile Number");
        }


    }
    private void checkMondatoryFields(ClientDto clientDto){
        if(clientDto.getFirstName()==null || clientDto.getFirstName().isEmpty()){
            throw  new MandotoryFieldsException("First Name can not be empty");
        }
        if(clientDto.getLastName()==null || clientDto.getLastName().isEmpty()){
            throw  new MandotoryFieldsException("First Name can not be empty");
        }
        if(clientDto.getIdNumber()==null || clientDto.getIdNumber().isEmpty()){
            throw  new MandotoryFieldsException("Id Number can not be empty");
        }
    }
    public Boolean isMobileNumberExist(String mobileNumber) {
        return Optional.ofNullable(getClientByMobileNumber(mobileNumber)).isPresent();

    }
    public Boolean isIdNumberExist(String idNumber) {
        return Optional.ofNullable(getClientByIdNumber(idNumber)).isPresent();

    }

}
