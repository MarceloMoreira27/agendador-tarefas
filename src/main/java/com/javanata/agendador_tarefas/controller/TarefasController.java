package com.javanata.agendador_tarefas.controller;

import com.javanata.agendador_tarefas.business.TarefasService;
import com.javanata.agendador_tarefas.business.dto.TarefasDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tarefas")
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO>gravarTarefa(@RequestBody TarefasDTO dto,
                                                   @RequestHeader("Authorization")String token){
        return ResponseEntity.ok(tarefasService.gravarTarefa(dto,token));

    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscarTarefasPorPeriodo(
            @RequestParam("dataIncial") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataIncial,
            @RequestParam("dataFinal") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal){
        return ResponseEntity.ok(tarefasService.buscarTarefasPorPeriodo(dataIncial, dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscarTarefasPorEmail(@RequestHeader("Authorization")String token){
        return ResponseEntity.ok(tarefasService.buscarTarefasPorEmail(token));
    }



}
