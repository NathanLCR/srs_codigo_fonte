package com.basis.srs.servico;

import com.basis.srs.dominio.Sala;
import com.basis.srs.dominio.SalaEquipamento;
import com.basis.srs.repositorio.EquipamentoRepositorio;
import com.basis.srs.repositorio.ReservaRepositorio;
import com.basis.srs.repositorio.SalaEquipamentoRepositorio;
import com.basis.srs.repositorio.SalaRepositorio;
import com.basis.srs.servico.dto.SalaDTO;
import com.basis.srs.servico.exception.RegraNegocioException;
import com.basis.srs.servico.mapper.SalaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class SalaServico {

    private final SalaMapper salaMapper;
    private final SalaRepositorio salaRepositorio;
    private final SalaEquipamentoRepositorio salaEquipamentoRepositorio;
    private final EquipamentoRepositorio equipamentoRepositorio;
    private final ReservaRepositorio reservaRepositorio;

    //GET
    public List<SalaDTO> listarTodas() {
        List<Sala> salas = salaRepositorio.findAll();
        List<SalaDTO> salasDto = salaMapper.toDto(salas);
        return salasDto;
    }

    //GET POR ID
    public SalaDTO pegarSalaPorId(Integer id) {
        Sala sala = salaRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Sala não encontrada!"));
        SalaDTO salaDto = salaMapper.toDto(sala);
        return salaDto;
    }

    //POST e put
    public SalaDTO salvar(SalaDTO salaDto) {
//         CHECAR SE É UMA ATUALIZAÇÃO
        if (salaDto.getId() != null) {
            Sala sala = salaRepositorio.findById(salaDto.getId()).orElseThrow(() -> new RegraNegocioException("Essa sala não existe"));
        }
        Sala sala2 = salaMapper.toEntity(salaDto);
        List<SalaEquipamento> novosEquipamentos = sala2.getEquipamentos();
        sala2.setEquipamentos(new ArrayList<>());
        salaRepositorio.save(sala2);
        novosEquipamentos.forEach(equipamento -> {
            equipamento.setSala(sala2);
            equipamento.getId().setIdSala(sala2.getId());
        });
        salaEquipamentoRepositorio.saveAll(novosEquipamentos);
        sala2.setEquipamentos(novosEquipamentos);
        return salaMapper.toDto(sala2);
    }

//    private void validaAtualizacao(SalaDTO salaDto) {
//    Essa função havia sido desenvolvida para validar a atualização de salas, verificando se haviam sido excluídos itens obrigatórios
//    porém, foi estabelecida uma mudança na regra de negócio, que inviabiliza o uso da mesma. Deixei comentada para que, se algum dia
//    for necessária sua reimplementação, já estará salva.
//
//
//        //PEGAR A ANTIGA SALA, QUE O USUÁRIO QUER ATUALIZAR
//        Sala sala = salaRepositorio.findById(salaMapper.toEntity(salaDto).getId()).orElse(null);
//        //PEGAR A LISTA DE EQUIPAMENTOS PASSADOS NA SALA NOVA
//        List<SalaEquipamentoDTO> salaEquipamentoAtualizados = salaDto.getEquipamentos();
//        //PEGAR A LISTA DE EQUIPAMENTOS EXISTENTES NA SALA JÁ CADASTRADA
//        List<SalaEquipamento> salaEquipamentoAntigos = sala.getEquipamentos();
//        for (int i = 0; i < salaEquipamentoAntigos.size(); i++) {
//            Equipamento equipamento = equipamentoRepositorio.findById(salaEquipamentoAntigos.get(i).getEquipamento().getId()).orElse(null);
//            if (equipamento.getObrigatorio() == 1) {
//                for (int j = 0; j < salaEquipamentoAtualizados.size(); j++) {
//                    if (salaEquipamentoAtualizados.get(j).getIdEquipamento() == equipamento.getId()) {
//                        if (salaEquipamentoAtualizados.get(j).getQuantidade() < 1) {
//                            throw new RegraNegocioException("A quantidade mínima para esse equipamento nesta sala é 1!");
//                        }
//                    }
//                    if (salaEquipamentoAtualizados.size() == j) {
//                        throw new RegraNegocioException("Você excluiu um equipamento obrigatório!");
//                    }
//                }
//            }
//        }
//    }

    //DELETE POR ID
    public void deletarSala (Integer id) {
        Sala sala = salaRepositorio.findById(id).orElseThrow(() -> new RegraNegocioException(id + ": Essa sala não existe."));

        if(reservaRepositorio.existsBySalaId(id)) {
            throw new RegraNegocioException("Ja existe reserva nessa sala.");
        }

        salaEquipamentoRepositorio.deleteAllBySalaId(id);
        salaRepositorio.deleteById(id);
    }

    //Criar um método que retorna uma lista de TODOS os equipamentos opcionais
}
