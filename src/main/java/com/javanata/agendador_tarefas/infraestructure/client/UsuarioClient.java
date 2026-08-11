package com.javanata.agendador_tarefas.infraestructure.client;

import com.javanata.agendador_tarefas.infraestructure.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient{

    @GetMapping("/usuario")
    UsuarioDTO busscarUsuarioPorEmail(@RequestParam("email")String emaiç,
                                      @RequestHeader("Autorization")String token);
}
