package com.javanata.agendador_tarefas.controller;

import com.javanata.agendador_tarefas.business.TarefasService;
import com.javanata.agendador_tarefas.business.dtos.TarefasDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    public TarefasDto gravaTarefas(@RequestBody TarefasDto tarefasDto,
                                   @RequestHeader("Authorization")String token){
        return tarefasService.gravarTarefas(tarefasDto,token);

    }
}
