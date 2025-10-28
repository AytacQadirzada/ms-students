package org.example.msstudents.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdressResponse {
    private Long id;
    private String city;
    private String state;
    private String street;
    private String zipCode;
}
