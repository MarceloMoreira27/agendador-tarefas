package com.javanata.agendador_tarefas.business.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class UsuarioDTO {

    private String email;
    private String senha;


}
