package com.ideas2it.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProjectDto {
    private int Id;
    @NotBlank(message = "passport name is a mandatory field and cannot be empty")
    @Pattern(regexp = "(/^[A-Za-z]+$/) ",message = "department name must only contain alphabets")
    private String Name;

    public ProjectDto() {

    }
}
