package org.example.msstudents.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCoursesRequest {
    private String name;
    private String description;
    private Double price;
}