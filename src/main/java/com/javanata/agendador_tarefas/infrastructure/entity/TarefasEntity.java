package com.javanata.agendador_tarefas.infrastructure.entity;


import com.javanata.agendador_tarefas.infrastructure.enuns.StatusNotificacaoEnum;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Document("tarefas")
public class TarefasEntity {

    private String id;
    private String nomeTarefa;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEvento;
    private String emailUsuario;
    private LocalDateTime dataAlteracao;
    private StatusNotificacaoEnum status;





}
