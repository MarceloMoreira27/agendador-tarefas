package com.javanata.agendador_tarefas.business.mapper;


import com.javanata.agendador_tarefas.business.dtos.TarefasDto;
import com.javanata.agendador_tarefas.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )

public interface TarefasUpdateMapper  {

    void update(TarefasDto dto,@MappingTarget TarefasEntity entity);


}
