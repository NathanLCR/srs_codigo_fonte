package com.basis.srs.web.rest;

import com.basis.srs.builder.ClienteBuilder;
import com.basis.srs.builder.ReservaBuilder;
import com.basis.srs.dominio.Cliente;
import com.basis.srs.dominio.Reserva;
import com.basis.srs.repositorio.ClienteRepositorio;
import com.basis.srs.repositorio.ReservaRepositorio;
import com.basis.srs.servico.dto.ClienteDTO;
import com.basis.srs.servico.dto.ReservaDTO;
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
public class ReservaRecursoIT extends IntTestComum {

    @Autowired
    private ReservaBuilder reservaBuilder;

    @Autowired
    private ReservaRepositorio reservaRepositorio;

    @BeforeEach
    public void limparBanco() {
        reservaRepositorio.deleteAll();
    }

    @Test
    public void listar() throws Exception {
        reservaBuilder.construir();
        getMockMvc().perform(MockMvcRequestBuilders.get("/api/reservas"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id", Matchers.hasSize(1)));

    }

    @Test
    public void listarPorId() throws Exception {
        Reserva reserva = reservaBuilder.construir();
        getMockMvc().perform(MockMvcRequestBuilders.get("/api/reservas/" + reserva.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(reserva.getId()));
    }

    @Test
    public void salvar() throws Exception {
        Reserva reserva = reservaBuilder.construirEntidade();
        getMockMvc().perform(post("/api/reservas/")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(reservaBuilder.converterParaDto(reserva)))
        )
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void salvarEmDataOcupada() throws Exception {
        Reserva reserva = reservaBuilder.construir();
        getMockMvc().perform(post("/api/reservas/")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(reservaBuilder.converterParaDto(reserva)))
        )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void atualizar() throws Exception {
        Reserva reserva = reservaBuilder.construir();
        ReservaDTO dto = reservaBuilder.converterParaDto(reserva);
        getMockMvc().perform(put("/api/reservas/")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(reservaBuilder.converterParaDto(reserva)))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(reserva.getId()));
    }

    @Test
    public void deletar() throws Exception {
        Reserva reserva = reservaBuilder.construir();
        getMockMvc().perform(delete("/api/reservas/" + reserva.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
