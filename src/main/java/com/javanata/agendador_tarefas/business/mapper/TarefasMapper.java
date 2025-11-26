package com.javanata.agendador_tarefas.business.mapper;


import com.javanata.agendador_tarefas.business.dto.TarefasDTO;
import com.javanata.agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasMapper {

    TarefasEntity paraTarefasEntity(TarefasDTO tarefasDTO);

    TarefasDTO paraTarefasDTO( TarefasEntity tarefasEntity);


    List<TarefasDTO>paraTarefasDTO(List<TarefasEntity>tarefasEntityList);

    List<TarefasEntity>paraTarefasEntity(List<TarefasDTO>tarefasDTOS);
}
