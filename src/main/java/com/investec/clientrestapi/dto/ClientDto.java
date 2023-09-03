package com.investec.clientrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ClientDto {
    private String firstName;
    private String lastName;
    private  int  mobileNumber;
    private String idNumber;
    private String physicalAddress;


}
