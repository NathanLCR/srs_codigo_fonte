package com.basis.srs.web;

import com.basis.srs.dominio.Reserva;
import com.basis.srs.repositorio.ReservaRepositorio;
import com.basis.srs.servico.ReservaServico;
import com.basis.srs.servico.dto.ReservaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservas")
public class ReservaRecurso {

    private ReservaServico reservaServico;


    @GetMapping("/{id}")
    public ResponseEntity<ReservaDto> procuraPorId(@PathVariable Integer id){
        ReservaDto reserva = reservaServico.listarPorId(id);
        return ResponseEntity.ok(reserva);
    }


    @GetMapping
    public ResponseEntity<List<ReservaDto>> listarReservas (){
        List<ReservaDto> listaDto = reservaServico.listar();
        return ResponseEntity.ok(listaDto);
    }



    @PostMapping
    public ResponseEntity<ReservaDto> cadastrarReserva(@RequestBody ReservaDto reservaDTO)throws URISyntaxException{
        reservaServico.salvar(reservaDTO);
        return ResponseEntity.created(new URI("/api/reservas")).body(reservaDTO);
    }

    @PutMapping
    public ResponseEntity<ReservaDto> alterarReserva(@RequestBody ReservaDto reserva ){
        reservaServico.salvar(reserva);
        return ResponseEntity.ok(reserva);

    }

    @DeleteMapping
    public ResponseEntity<ReservaDto> deletarReserva(@PathVariable Integer id){
        ReservaDto reservaDto = reservaServico.listarPorId(id);
        reservaServico.deletar(id);
        return ResponseEntity.ok(reservaDto);
        }






}
