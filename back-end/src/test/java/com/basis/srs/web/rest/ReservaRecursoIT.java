package com.basis.srs.web.rest;

import com.basis.srs.Builder.ReservaBuilder;
import com.basis.srs.dominio.Reserva;
import com.basis.srs.repositorio.ReservaRepositorio;
import com.basis.srs.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@RunWith(SpringRunner.class)
public class ReservaRecursoIT extends com.basis.srs.util.IntTestComum {

    @Autowired
    private ReservaBuilder reservaBuilder;

    @Autowired
    private ReservaRepositorio reservaRepositorio;

    @BeforeEach
    public void limparBanco(){ reservaRepositorio.deleteAll();
    }

    @Test//Teste Get
    public void listar() throws Exception {
        reservaBuilder.construir();
        getMockMvc().perform(MockMvcRequestBuilders.get("api/reservas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("${*}.id", hasSize(1)));
    }

    @Test// Teste
    public void salvar() throws Exception {
        Reserva reserva = reservaBuilder.construirEntidade();
        getMockMvc().perform(MockMvcRequestBuilders.post("api/reservas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(reservaBuilder.converterToDto(reserva)))
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("${*}.id").value(reserva.getId()));
    }

    @Test
    public void editar()throws Exception{
        Reserva reserva = reservaBuilder.construir();

        getMockMvc().perform(MockMvcRequestBuilders.put("api/reservas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(reservaBuilder.converterToDto(reserva)))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(reserva.getId()));


    }
    @Test
    public void obtrtPorId()throws Exception{
        Reserva reserva = reservaBuilder.construir();
        getMockMvc().perform(MockMvcRequestBuilders.get("api/reservas" + reserva.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("${*}.id",hasSize(1)));
    }

    @Test
    public void deletar() throws Exception{
        Reserva reserva = reservaBuilder.construir();

        getMockMvc().perform(MockMvcRequestBuilders.delete("api/reservas" + reserva.getId()))
                .andExpect(status().isOk());





    }

}
