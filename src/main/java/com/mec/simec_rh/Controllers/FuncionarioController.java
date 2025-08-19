package com.mec.simec_rh.Controllers;

import com.mec.simec_rh.DTOs.Funcionario.FuncionarioDTO;
import com.mec.simec_rh.Entities.Funcionario;
import com.mec.simec_rh.Mappers.FuncionarioMapper;
import com.mec.simec_rh.Services.FuncionarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/funcionarios")
public class FuncionarioController {

    private final  FuncionarioService funcionarioService;


    public FuncionarioController(FuncionarioService funcionarioService, FuncionarioMapper funcionarioMapper) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping("full")
    public ResponseEntity<List<FuncionarioDTO>> obterTodosFuncionariosCompleto(){
        return ResponseEntity.status(HttpStatus.FOUND).body(funcionarioService.getAllFullFuncionario());
    }

    @GetMapping("{id}")
    public Funcionario obterTodosFuncionario(){
        return funcionarioService.getFuncionario();
    }

    @PostMapping
    public Funcionario adicionarFuncionario(@RequestBody Funcionario funcionario){
        return funcionarioService.createFuncionario(funcionario);
    }

    @PostMapping("/full")
    public Funcionario registrarFuncionario(@RequestBody Funcionario funcionario){
        return funcionarioService.registrarFuncionario(funcionario);
    }

    @PutMapping("{id}")
    public Funcionario atualizarFuncionario(@RequestBody Funcionario funcionario, @PathVariable Long id){
        return  funcionarioService.atualizarFuncionario(funcionario, id);
    }

}
