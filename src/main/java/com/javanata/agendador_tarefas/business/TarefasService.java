package com.javanata.agendador_tarefas.business;

import com.javanata.agendador_tarefas.business.dto.TarefasDTO;
import com.javanata.agendador_tarefas.business.mapper.TarefasMapper;
import com.javanata.agendador_tarefas.infraestructure.entity.TarefasEntity;
import com.javanata.agendador_tarefas.infraestructure.enums.StatusNotificacaoEnum;
import com.javanata.agendador_tarefas.infraestructure.repository.TarefasRepository;
import com.javanata.agendador_tarefas.infraestructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final JwtUtil jwtUtil;
    private final TarefasMapper mapper;

    public TarefasDTO gravarTarefa(TarefasDTO dto,String token){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        dto.setEmailUsuario(email);
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatus(StatusNotificacaoEnum.PENDENTE);
        TarefasEntity entity = mapper.paraTarefasEntity(dto);

        return mapper.paraTarefasDTO(tarefasRepository.save(entity));
    }


    public List<TarefasDTO> buscarTarefasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal){
        return mapper.paraListaTarefasDTO(tarefasRepository.findByDataEventoBetween(dataInicial,dataFinal));
    }


    public List<TarefasDTO> buscarTarefasPorEmail(String token){
        String email = jwtUtil.extrairEmailToken(token.substring(7));
        return mapper.paraListaTarefasDTO(tarefasRepository.findByEmailUsuario(email));
    }

}
