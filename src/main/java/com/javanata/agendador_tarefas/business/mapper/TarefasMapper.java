package com.javanata.agendador_tarefas.business.mapper;


import com.javanata.agendador_tarefas.business.dto.TarefasDTO;
import com.javanata.agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TarefasMapper {

    TarefasEntity paraTarefasEntity(TarefasDTO tarefasDTO);

    TarefasDTO paraTarefasDTO( TarefasEntity tarefasEntity);


}
