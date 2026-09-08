package com.javanata.agendador_tarefas.business;

import com.javanata.agendador_tarefas.business.dto.TarefasDTO;
import com.javanata.agendador_tarefas.business.mapper.TarefasMapper;
import com.javanata.agendador_tarefas.business.mapper.TarefasUpdateMapper;
import com.javanata.agendador_tarefas.infraestructure.entity.TarefasEntity;
import com.javanata.agendador_tarefas.infraestructure.entity.exceptions.ResourceNotFoundException;
import com.javanata.agendador_tarefas.infraestructure.enums.StatusNotificacaoEnum;
import com.javanata.agendador_tarefas.infraestructure.repository.TarefasRepository;
import com.javanata.agendador_tarefas.infraestructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final JwtUtil jwtUtil;
    private final TarefasMapper mapper;
    private final TarefasUpdateMapper updateMapper;

    @Transactional
    public TarefasDTO gravarTarefa(TarefasDTO dto, String token) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        dto.setEmailUsuario(email);
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatus(StatusNotificacaoEnum.PENDENTE);
        TarefasEntity entity = mapper.paraTarefasEntity(dto);

        return mapper.paraTarefasDTO(tarefasRepository.save(entity));
    }


    public List<TarefasDTO> buscarTarefasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return mapper.paraListaTarefasDTO(tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal));
    }


    public List<TarefasDTO> buscarTarefasPorEmail(String token) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        return mapper.paraListaTarefasDTO(tarefasRepository.findByEmailUsuario(email));
    }


    @Transactional
    public void deletaTarefaPorId(String id) {
        if (!tarefasRepository.existsById(id)) {
            throw new ResourceNotFoundException("Erro ao deletar tarefa por id, id inexistente " + id);
        }
        tarefasRepository.deleteById(id);
    }

    @Transactional
    public TarefasDTO alteraStatus(StatusNotificacaoEnum status, String id) {
        try {
            TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(
                    () -> new ResourceNotFoundException("Tarefa não encontrada " + id));

            entity.setStatus(status);
            return mapper.paraTarefasDTO(tarefasRepository.save(entity));

        } catch (ResourceNotFoundException e) {
          throw new ResourceNotFoundException("Erro ao alterar status da tarefa " + e.getCause());
        }

    }

    @Transactional
    public  TarefasDTO updateTarefas(TarefasDTO dto, String id ) {
       try {
           TarefasEntity entity = tarefasRepository.findById(id).orElseThrow(
                   () -> new ResourceNotFoundException("Tarefa não encontrada " + id));

           updateMapper.updateTarefas(dto, entity);
           return mapper.paraTarefasDTO(tarefasRepository.save(entity));

       } catch (ResourceNotFoundException e) {
           throw new ResourceNotFoundException("Erro ao atualizar tarefa " + e.getCause());
       }
    }


}
