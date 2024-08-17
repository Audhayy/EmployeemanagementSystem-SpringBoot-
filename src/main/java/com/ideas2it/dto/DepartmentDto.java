package com.ideas2it.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DepartmentDto {
    private int Id;
    @NotBlank(message ="Name is a mandatory field and must not be empty")
    @Pattern(regexp = "(/^[A-Za-z]+$/) ", message = "Name must only be alphabets")
    private String Name;

}
