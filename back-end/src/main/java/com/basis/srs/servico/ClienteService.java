package com.basis.srs.servico;

import com.basis.srs.dominio.Cliente;
import com.basis.srs.repositorio.ClienteRepositorio;
import com.basis.srs.rest.ClienteRecurso;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public class ClienteService {

    ClienteRepositorio clienteRepositorio = new ClienteRepositorio() {


    };
}
