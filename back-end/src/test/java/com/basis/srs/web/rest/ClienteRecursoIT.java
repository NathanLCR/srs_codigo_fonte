package com.basis.srs.web.rest;

import com.basis.srs.builder.ClienteBuilder;
import com.basis.srs.dominio.Cliente;
<<<<<<< HEAD
import com.basis.srs.util.IntTestComum;
import com.basis.srs.util.TestUtil;
=======
import com.basis.srs.repositorio.ClienteRepositorio;
import com.basis.srs.servico.dto.ClienteDTO;
import com.basis.srs.util.IntTestComum;
import com.basis.srs.util.TestUtil;
import org.hamcrest.Matchers;
>>>>>>> fe8655038ed0757a48b2a5cecc43e6a54668beb1
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
<<<<<<< HEAD
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
=======
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
>>>>>>> fe8655038ed0757a48b2a5cecc43e6a54668beb1

@RunWith(SpringRunner.class)
@Transactional
public class ClienteRecursoIT extends IntTestComum {

    @Autowired
    private ClienteBuilder clienteBuilder;

<<<<<<< HEAD
    @BeforeEach
    public void limparBanco(){
        clienteBuilder.limparDados();
=======
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @BeforeEach
    public void limparBanco() {
        clienteRepositorio.deleteAll();
>>>>>>> fe8655038ed0757a48b2a5cecc43e6a54668beb1
    }

    @Test
    public void listar() throws Exception {
        clienteBuilder.construir();
<<<<<<< HEAD
        getMockMvc().perform(get("/api/clientes"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[*].id", hasSize(1)));
    }

//    @Test
//    public void obterPorId() throws Exception{
//        clienteBuilder.construir();
//        getMockMvc().perform(get("api/clientes"
//                + clienteBuilder.construir().getId()))
//                .andExpect(status().isOk());
//    }
=======
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
>>>>>>> fe8655038ed0757a48b2a5cecc43e6a54668beb1

    @Test
    public void salvar() throws Exception {
        Cliente cliente = clienteBuilder.construirEntidade();
<<<<<<< HEAD
        getMockMvc().perform(post("api/clientes")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(
                        clienteBuilder.converterToDto(cliente))
                ))
                .andExpect(status().isCreated());
    }

//    @Test
//    public void editar() throws Exception {
//        Cliente cliente = clienteBuilder.construir();
//        getMockMvc().perform(put("api/clientes")
//                .contentType(TestUtil.APPLICATION_JSON_UTF8)
//                .content(TestUtil.convertObjectToJsonBytes(clienteBuilder.converterToDto(cliente)))
//        ).andExpect(status().isOk());
//    }

    @Test
    public void delete() throws Exception {
        Cliente cliente = clienteBuilder.construir();
        getMockMvc().perform(get("/api/clientes/" + cliente.getId()))
                .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id").value(cliente.getId()));
    }

=======
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
>>>>>>> fe8655038ed0757a48b2a5cecc43e6a54668beb1
}
