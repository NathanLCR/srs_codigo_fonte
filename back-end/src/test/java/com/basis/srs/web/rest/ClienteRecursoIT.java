package com.basis.srs.web.rest;

import com.basis.srs.builder.ClienteBuilder;
import com.basis.srs.dominio.Cliente;
import com.basis.srs.repositorio.ClienteRepositorio;
import com.basis.srs.servico.dto.ClienteDTO;
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
public class ClienteRecursoIT extends IntTestComum {

    @Autowired
    private ClienteBuilder clienteBuilder;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @BeforeEach
    public void limparBanco() {
        clienteRepositorio.deleteAll();
    }

    @Test
    public void listar() throws Exception {
        clienteBuilder.construir();
        getMockMvc().perform(MockMvcRequestBuilders.get("/api/clientes"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].id", Matchers.hasSize(1)));

    }

    @Test
    public void listarPorId() throws Exception {
        Cliente cliente = clienteBuilder.construir();
        getMockMvc().perform(MockMvcRequestBuilders.get("/api/clientes/" + cliente.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(cliente.getId()));
    }

    @Test
    public void salvar() throws Exception {
        Cliente cliente = clienteBuilder.construirEntidade();
        getMockMvc().perform(post("/api/clientes/")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(clienteBuilder.converterParaDto(cliente)))
        )
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void salvarComCpfJaExistente() throws Exception {
        Cliente cliente = clienteBuilder.construir();
        getMockMvc().perform(post("/api/clientes/")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(clienteBuilder.converterParaDto(cliente)))
        )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void atualizar() throws Exception {
        Cliente cliente = clienteBuilder.construir();
        ClienteDTO dto = clienteBuilder.converterParaDto(cliente);
        getMockMvc().perform(put("/api/clientes/")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(clienteBuilder.converterParaDto(cliente)))
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(cliente.getId()));
    }

    @Test
    public void deletar() throws Exception {
        Cliente cliente = clienteBuilder.construir();
        getMockMvc().perform(delete("/api/clientes/" + cliente.getId()))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}

