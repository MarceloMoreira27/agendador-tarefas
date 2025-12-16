package com.javanata.agendador_tarefas.business.mapper;


import com.javanata.agendador_tarefas.business.dtos.TarefasDto;
import com.javanata.agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasMapper {

    @Mapping(source = "id",target = "id")

    TarefasDto paraTarefasDto(TarefasEntity tarefasEntity);

    TarefasEntity paraTarefasEntity(TarefasDto tarefasDto);

    List<TarefasDto>paraListaTaredasDTO(List<TarefasEntity>tarefasLista);

    List<TarefasEntity>paraListaTaredasEntity(List<TarefasDto>tarefasDtoLista);

}
