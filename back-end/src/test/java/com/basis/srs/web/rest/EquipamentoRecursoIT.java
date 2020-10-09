package com.basis.srs.web.rest;

import com.basis.srs.builder.EquipamentoBuilder;
import com.basis.srs.builder.SalaBuilder;
import com.basis.srs.dominio.Equipamento;
import com.basis.srs.dominio.Sala;
import com.basis.srs.dominio.SalaEquipamento;
import com.basis.srs.repositorio.EquipamentoRepositorio;
import com.basis.srs.servico.dto.EquipamentoDTO;
import com.basis.srs.util.IntTestComum;
import com.basis.srs.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@Transactional
public class EquipamentoRecursoIT extends IntTestComum {

    @Autowired
    private EquipamentoRepositorio equipamentoRepositorio;

    @Autowired
    private SalaBuilder salaBuilder;

    @Autowired
    private EquipamentoBuilder equipamentoBuilder;

    @BeforeEach
    public void limparRegistro(){
        equipamentoRepositorio.deleteAll();
    }

    @Test
    public void listar() throws Exception {
        equipamentoBuilder.construir();

        getMockMvc().perform(MockMvcRequestBuilders.get("/api/equipamentos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id",hasSize(1)));
    }

    @Test
    public void obterPorId() throws Exception {
        Equipamento equipamento = equipamentoBuilder.construir();

        getMockMvc().perform(MockMvcRequestBuilders.get("/api/equipamentos/"+equipamento.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(equipamento.getId()));
    }

    @Test
    public void obterPorIdInexistente() throws Exception {
        getMockMvc().perform(MockMvcRequestBuilders.get("/api/equipamentos/250"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void salvar() throws Exception {
        Equipamento equipamento = equipamentoBuilder.construirEntidade();

        getMockMvc().perform(MockMvcRequestBuilders.post("/api/equipamentos")
                    .contentType(TestUtil.APPLICATION_JSON_UTF8)
                    .content(TestUtil.convertObjectToJsonBytes(equipamentoBuilder.converterToDto(equipamento)))
                )
                .andExpect(status().isCreated());
    }

    @Test
    public void atualizar() throws Exception {
        Equipamento equipamento = equipamentoBuilder.construir();
        EquipamentoDTO dto = equipamentoBuilder.converterToDto(equipamento);

        getMockMvc().perform(MockMvcRequestBuilders.put("/api/equipamentos")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(dto))
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(equipamento.getId()));
    }


    @Test
    public void deletar() throws Exception {
        Equipamento equipamento = equipamentoBuilder.construir();

        getMockMvc().perform(MockMvcRequestBuilders.delete("/api/equipamentos/"+equipamento.getId()))
                .andExpect(status().isOk());
    }


    @Test
    public void deletarComRelacionamentoSalaEquipamento() throws Exception {
        Sala sala = salaBuilder.construir();

        SalaEquipamento salaEquipamento = sala.getEquipamentos().get(0);

        getMockMvc().perform(MockMvcRequestBuilders.delete("/api/equipamentos/"+salaEquipamento.getEquipamento().getId()))
                .andExpect(status().isBadRequest());
    }

}
