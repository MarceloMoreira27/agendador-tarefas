package com.javanata.agendador_tarefas.infraestructure.entity.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
