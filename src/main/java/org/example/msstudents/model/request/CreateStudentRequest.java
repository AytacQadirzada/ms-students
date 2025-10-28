package org.example.msstudents.model.request;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.msstudents.annotation.Password;
import org.example.msstudents.dao.entity.AdressEntity;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentRequest {
    private String firstName;
    private String lastName;
    private Integer age;
    private String email;
    private String phoneNumber;
    @Password
//    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(.{8,})$", message = "Şifrənin uzunluğu minimum 8 olmalıdır. Daxilində bir kiçik hərf bir böyük hərf bir xüsusi simvol və bir rəqəm olmalıdır")
    private String password;
    private CreateAdressRequest address;
    private List<CreateCoursesRequest> courses;

}
