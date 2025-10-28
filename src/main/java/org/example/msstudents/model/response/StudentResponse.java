package org.example.msstudents.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.msstudents.dao.entity.AdressEntity;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private String phoneNumber;
    private AdressResponse address;
//    private Long addressId;
}
