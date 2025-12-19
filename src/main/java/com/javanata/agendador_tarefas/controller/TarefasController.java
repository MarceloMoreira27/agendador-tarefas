package com.javanata.agendador_tarefas.controller;

import com.javanata.agendador_tarefas.business.TarefasService;
import com.javanata.agendador_tarefas.business.dtos.TarefasDto;
import com.javanata.agendador_tarefas.infrastructure.enuns.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    public TarefasDto gravaTarefas(@RequestBody TarefasDto tarefasDto,
                                   @RequestHeader("Authorization") String token) {
        return tarefasService.gravarTarefas(tarefasDto, token);

    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDto>> buscarTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDateTime dataFinal) {

        return ResponseEntity.ok(tarefasService.buscaTarefasPorPeriodo(dataInicial, dataFinal));

    }

    @GetMapping
    public ResponseEntity<List<TarefasDto>> buscaTarefasPorEmail(@RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(tarefasService.buscarTarefasPorEmail(token));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletaTarefasPorId(@RequestParam("id") String id) {
        tarefasService.deletaTarefasPorId(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public ResponseEntity<TarefasDto> alteraStatusNotificacao(@RequestParam("status") StatusNotificacaoEnum status,
                                                              @RequestParam("id") String id) {

        return ResponseEntity.ok(tarefasService.alteraStatus(status,id));

    }

    @PutMapping
    public ResponseEntity<TarefasDto>updateTarefas(@RequestBody TarefasDto dto,@RequestParam("id")String id){
        return ResponseEntity.ok(tarefasService.upadateTarefas(dto,id));
    }




}
