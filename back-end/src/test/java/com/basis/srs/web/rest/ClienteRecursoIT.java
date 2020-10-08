package com.basis.srs.web.rest;

import com.basis.srs.builder.ClienteBuilder;
import com.basis.srs.dominio.Cliente;
import com.basis.srs.util.IntTestComum;
import com.basis.srs.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@Transactional
public class ClienteRecursoIT extends IntTestComum {

    @Autowired
    private ClienteBuilder clienteBuilder;

    @BeforeEach
    public void limparBanco(){
        clienteBuilder.limparDados();
    }

    @Test
    public void listar() throws Exception {
        clienteBuilder.construir();
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

    @Test
    public void salvar() throws Exception {
        Cliente cliente = clienteBuilder.construirEntidade();
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

}
