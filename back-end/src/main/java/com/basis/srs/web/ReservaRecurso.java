package com.basis.srs.web;

import com.basis.srs.servico.ReservaServico;
import com.basis.srs.servico.dto.ReservaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

//autor = "lucas.costa"
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reservas")
public class ReservaRecurso {

    private final ReservaServico reservaServico;


    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> procurarPorId(@PathVariable Integer id){
        ReservaDTO reserva = reservaServico.procurarPorId(id);
        return ResponseEntity.ok(reserva);
    }


    @GetMapping
    public ResponseEntity<List<ReservaDTO>> listarReservas (){
        List<ReservaDTO> listaDto = reservaServico.listar();
        return ResponseEntity.ok(listaDto);
    }



    @PostMapping
<<<<<<< HEAD
    public ResponseEntity<ReservaDTO> cadastrarReserva(@Valid @RequestBody ReservaDTO reservaDTO)throws URISyntaxException{
        reservaServico.salvar(reservaDTO);
        return ResponseEntity.created(new URI("/api/reservas")).body(reservaDTO);
    }

    @PutMapping
    public ResponseEntity<ReservaDTO> alterarReserva(@Valid @RequestBody ReservaDTO reserva ){
        reservaServico.salvar(reserva);
=======
    public ResponseEntity<ReservaDTO> cadastrarReserva(@RequestBody ReservaDTO reservaDTO)throws URISyntaxException{
        ReservaDTO reservaSalva = reservaServico.salvar(reservaDTO);
        return ResponseEntity.created(new URI("/api/reservas")).body(reservaSalva);
    }

    @PutMapping
    public ResponseEntity<ReservaDTO> alterarReserva(@RequestBody ReservaDTO reservaDTO ){
        ReservaDTO reserva = reservaServico.salvar(reservaDTO);
>>>>>>> 4ed5152ba517f2452b8dacf0649f0fcf308031c8
        return ResponseEntity.ok(reserva);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReservaDTO> deletarReserva(@PathVariable Integer id){
        reservaServico.deletar(id);
        return ResponseEntity.ok().build();
        }


}
