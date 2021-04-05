package br.com.mobicare.collaborator.exceptions.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Error {
    private String mensage;
    private String details;
}