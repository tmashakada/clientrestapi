package com.investec.clientrestapi.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private  int id;
  //  @NotEmpty(message="First Name can not be empty")
    private String firstName;
    private String lastName;
    private String  mobileNumber;
    private String idNumber;
    private String physicalAddress;



}
