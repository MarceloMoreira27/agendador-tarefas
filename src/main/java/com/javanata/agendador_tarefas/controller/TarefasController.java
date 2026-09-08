package com.javanata.agendador_tarefas.controller;

import com.javanata.agendador_tarefas.business.TarefasService;
import com.javanata.agendador_tarefas.business.dto.TarefasDTO;
import com.javanata.agendador_tarefas.infraestructure.enums.StatusNotificacaoEnum;
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


    @DeleteMapping
    public ResponseEntity<Void> deletaTarefasPorId(@RequestParam("id")String id){
        tarefasService.deletaTarefaPorId(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefasDTO> alteraStausNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                             @RequestParam("id")String id){

        return ResponseEntity.ok(tarefasService.alteraStatus(status, id));
    }

    @PutMapping
    public ResponseEntity<TarefasDTO> updateTarefas(@RequestBody TarefasDTO dto,@RequestParam("id")String id){
        return ResponseEntity.ok(tarefasService.updateTarefas(dto,id));
    }




}
