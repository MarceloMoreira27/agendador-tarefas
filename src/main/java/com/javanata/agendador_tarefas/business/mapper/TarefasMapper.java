package com.javanata.agendador_tarefas.business.mapper;


import com.javanata.agendador_tarefas.business.dtos.TarefasDto;
import com.javanata.agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasMapper {

    TarefasDto paraTarefasDto(TarefasEntity tarefasEntity);

    TarefasEntity paraTarefasEntity(TarefasDto tarefasDto);
}
