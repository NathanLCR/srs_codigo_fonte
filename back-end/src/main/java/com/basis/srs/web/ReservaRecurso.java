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

    @GetMapping("/total")
    public ResponseEntity<Double> retornarTotalCusto(@RequestBody ReservaDTO reservaDTO){
        Double total = reservaServico.calculaTotalReserva(reservaDTO);
        return ResponseEntity.ok(total);
    }

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> listarReservas (){
        List<ReservaDTO> listaDto = reservaServico.listar();
        return ResponseEntity.ok(listaDto);
    }



    @PostMapping
    public ResponseEntity<ReservaDTO> cadastrarReserva(@Valid @RequestBody ReservaDTO reservaDTO)throws URISyntaxException{
        ReservaDTO reserva = reservaServico.salvar(reservaDTO);
        return ResponseEntity.created(new URI("/api/reservas")).body(reserva);
    }


    @PutMapping
    public ResponseEntity<ReservaDTO> alterarReserva(@Valid @RequestBody ReservaDTO reservaDTO ) {
        ReservaDTO reserva = reservaServico.salvar(reservaDTO);
        return ResponseEntity.ok(reserva);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ReservaDTO> deletarReserva(@PathVariable Integer id){
        reservaServico.deletar(id);
        return ResponseEntity.ok().build();
    }


}
