package com.javanata.agendador_tarefas.infraestructure.repository;

import com.javanata.agendador_tarefas.infraestructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TarefasRepository extends MongoRepository<TarefasEntity,String> {


}
