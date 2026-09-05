package com.javanata.agendador_tarefas.business.mapper;

import com.javanata.agendador_tarefas.business.dto.TarefasDTO;
import com.javanata.agendador_tarefas.infraestructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)

public interface TarefasUpdateMapper {

    void updateTarefas(TarefasDTO dto, @MappingTarget TarefasEntity entity);


}