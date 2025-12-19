package com.javanata.agendador_tarefas.business;


import com.javanata.agendador_tarefas.business.dtos.TarefasDto;
import com.javanata.agendador_tarefas.business.dtos.UsuarioDTO;
import com.javanata.agendador_tarefas.business.mapper.TarefasMapper;
import com.javanata.agendador_tarefas.business.mapper.TarefasUpdateMapper;
import com.javanata.agendador_tarefas.infrastructure.entity.TarefasEntity;
import com.javanata.agendador_tarefas.infrastructure.enuns.StatusNotificacaoEnum;
import com.javanata.agendador_tarefas.infrastructure.exceptions.ResourceNotFoundException;
import com.javanata.agendador_tarefas.infrastructure.repository.TarefasRepository;
import com.javanata.agendador_tarefas.infrastructure.security.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final JwtUtil jwtUtil;
    private final TarefasMapper mapper;
    private final TarefasRepository tarefasRepository;
    private final TarefasUpdateMapper updateMapper;

    public TarefasDto gravarTarefas(TarefasDto dto, String token) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));

        dto.setEmailUsuario(email);
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatus(StatusNotificacaoEnum.PENDENTE);

        TarefasEntity tarefasEntity = mapper.paraTarefasEntity(dto);
        return mapper.paraTarefasDto(tarefasRepository.save(tarefasEntity));

    }


    public List<TarefasDto> buscaTarefasPorPeriodo(LocalDateTime dataInicial
            , LocalDateTime dataFinal) {
        return mapper.paraListaTaredasDTO(
                tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }


    public List<TarefasDto> buscarTarefasPorEmail(String token) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));

        return mapper.paraListaTaredasDTO(tarefasRepository.findByEmailUsuario(email));

    }

    public void deletaTarefasPorId(String id) {
        try {

            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao deletar por id,id inexistente ",
                    e.getCause());
        }
    }


    public TarefasDto alteraStatus(StatusNotificacaoEnum status, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Tarefa não encontrada " + id));

            entity.setStatus(status);
            return mapper.paraTarefasDto(tarefasRepository.save(entity));

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa",
                    e.getCause());
        }

    }


    public TarefasDto upadateTarefas(TarefasDto dto,String id){
        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Tarefa não encontrada " + id));

           updateMapper.update(dto,entity);

            return mapper.paraTarefasDto(
                    tarefasRepository.save(entity));

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Erro ao alterar status da tarefa",
                    e.getCause());
        }

    }


}
