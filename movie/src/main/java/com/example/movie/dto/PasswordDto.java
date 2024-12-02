package com.example.movie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PasswordDto {
    private String eamil;
    private String currentPassword;
    private String newPassword;
}
