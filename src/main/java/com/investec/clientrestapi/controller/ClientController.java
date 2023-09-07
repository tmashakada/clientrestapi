package com.investec.clientrestapi.controller;

import com.investec.clientrestapi.dto.ClientDto;
import com.investec.clientrestapi.exception.ClientNotFoundException;
import com.investec.clientrestapi.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ClientController {
   private  final ClientService clientService;
   @GetMapping(value = "/clients")
   public List<ClientDto> getClients(){
       return clientService.getClients();
   }
   @PostMapping(value = "/clients")
   public ResponseEntity<ClientDto>  addClient(@RequestBody ClientDto clientDto){

     return ResponseEntity.status(HttpStatus.CREATED).body(clientService.addClient(clientDto));
   }
    @PutMapping(value = "/clients/update/{idNumber}")
   public ResponseEntity<ClientDto> updateClient(@RequestBody ClientDto clientDto, @PathVariable String idNumber){
       return ResponseEntity.status(HttpStatus.OK).body( clientService.updateClient(clientDto,idNumber));
   }
    @GetMapping("/clients/idNumber/{idNumber}")
    public ResponseEntity<ClientDto>  getClientByIdNumber(@PathVariable String idNumber){
        ClientDto clientDto = clientService.getClientByIdNumber(idNumber);
        if(clientDto==null){
            throw  new ClientNotFoundException("Client Not Found");
        }
       return  ResponseEntity.status(HttpStatus.OK).body(clientDto);
    }
    @GetMapping("/clients/firstname/{firstName}")
    public ResponseEntity<List<ClientDto>> getClientByFirstName(@PathVariable String firstName){
        return  ResponseEntity.status(HttpStatus.OK).body(clientService.getClientByFirstName(firstName));
    }
    @GetMapping("/clients/mobilenumber/{mobileNumber}")
    public ResponseEntity<ClientDto>  getClientByMobileNumber(@PathVariable String mobileNumber){
       ClientDto clientDto= clientService.getClientByMobileNumber(mobileNumber);
       if(clientDto==null){
            throw  new ClientNotFoundException("Client Not Found");
       }

       return  ResponseEntity.status(HttpStatus.OK).body(clientService.getClientByMobileNumber(mobileNumber));
    }
}
