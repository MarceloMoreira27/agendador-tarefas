package com.javanata.agendador_tarefas.business;


import com.javanata.agendador_tarefas.business.dtos.TarefasDto;
import com.javanata.agendador_tarefas.business.dtos.UsuarioDTO;
import com.javanata.agendador_tarefas.business.mapper.TarefasMapper;
import com.javanata.agendador_tarefas.infrastructure.entity.TarefasEntity;
import com.javanata.agendador_tarefas.infrastructure.enuns.StatusNotificacaoEnum;
import com.javanata.agendador_tarefas.infrastructure.repository.TarefasRepository;
import com.javanata.agendador_tarefas.infrastructure.security.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final JwtUtil jwtUtil;
    private final TarefasMapper mapper;
    private final TarefasRepository tarefasRepository;

    public TarefasDto gravarTarefas(TarefasDto dto, String token) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));

        dto.setEmailUsuario(email);
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatus(StatusNotificacaoEnum.PENDENTE);

        TarefasEntity tarefasEntity = mapper.paraTarefasEntity(dto);
        return mapper.paraTarefasDto(tarefasRepository.save(tarefasEntity));

    }



}
