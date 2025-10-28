package org.example.msstudents.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAdressRequest {
    private String city;
    private String state;
    private String street;
    private String zipCode;

}
