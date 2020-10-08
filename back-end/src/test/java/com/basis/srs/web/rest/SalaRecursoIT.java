package com.basis.srs.web.rest;

import com.basis.srs.builder.SalaBuilder;
import com.basis.srs.dominio.Sala;
import com.basis.srs.repositorio.SalaRepositorio;
import com.basis.srs.servico.dto.SalaDTO;
import com.basis.srs.util.IntTestComum;
import com.basis.srs.util.TestUtil;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@RunWith(SpringRunner.class)
@Transactional
public class SalaRecursoIT extends IntTestComum {

    @Autowired
    private SalaBuilder salaBuilder;

    @Autowired
    private SalaRepositorio salaRepositorio;

    @BeforeEach
    public void limparBanco() {
        salaRepositorio.deleteAll();
    }

    @Test
    public void listar() throws Exception {
        salaBuilder.construir();
        getMockMvc().perform(MockMvcRequestBuilders.get("/api/salas"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id", Matchers.hasSize(1)));

    }

    @Test
    public void listarPorId() throws Exception {
        Sala sb = salaBuilder.construir();
        getMockMvc().perform(MockMvcRequestBuilders.get("/api/salas/" + sb.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(sb.getId()));
    }

    @Test
    public void salvar() throws Exception {
        Sala sala = salaBuilder.construirEntidade();
        getMockMvc().perform(post("/api/salas/")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(salaBuilder.converterParaDto(sala)))
        )
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void atualizar() throws Exception {
        Sala sala = salaBuilder.construir();
        SalaDTO salaDTO = salaBuilder.converterParaDto(sala);
        getMockMvc().perform(put("/api/salas/")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(salaBuilder.converterParaDto(sala)))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(sala.getId()));
    }

    @Test
    public void deletar() throws Exception {
        Sala sala = salaBuilder.construir();
        getMockMvc().perform(delete("/api/salas/" + sala.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
