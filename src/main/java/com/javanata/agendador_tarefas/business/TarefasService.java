package com.javanata.agendador_tarefas.business;

import com.javanata.agendador_tarefas.business.dto.TarefasDTO;
import com.javanata.agendador_tarefas.business.mapper.TarefasMapper;
import com.javanata.agendador_tarefas.infrastructure.entity.TarefasEntity;
import com.javanata.agendador_tarefas.infrastructure.entity.enuns.StatusNotificacaoEnum;
import com.javanata.agendador_tarefas.infrastructure.repository.TarefasRepository;
import com.javanata.agendador_tarefas.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefasService {

   private final TarefasRepository tarefasRepository;
   private final TarefasMapper mapper;
   private final JwtUtil jwtUtil;

   public TarefasDTO gravarTarefa(TarefasDTO dto,String token){

       String email = jwtUtil.extrairEmailToken(token.substring(7));

       dto.setEmailUsuario(email);
       dto.setDataCriacao(LocalDateTime.now());
       dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);

       TarefasEntity  tarefasEntity = mapper.paraTarefasEntity(dto);

       return mapper.paraTarefasDTO(tarefasRepository.save(tarefasEntity));
   }

}
