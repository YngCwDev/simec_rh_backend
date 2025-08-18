package com.mec.simec_rh.Controllers;

import com.mec.simec_rh.Entities.Funcionario;
import com.mec.simec_rh.Services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/funcionarios")
public class FuncionarioController {

    private final  FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    @GetMapping
    public List<Funcionario> obterTodosFuncionarios(){
        return funcionarioService.getAllFuncionario();
    }

    @GetMapping("{id}")
    public List<Funcionario> obterFuncionario(){
        return funcionarioService.getAllFuncionario();
    }

    @PostMapping
    public Funcionario adicionarFuncionario(@RequestBody Funcionario funcionario){
        return funcionarioService.createFuncionario(funcionario);
    }

    @PostMapping("/full")
    public Funcionario registrarFuncionario(@RequestBody Funcionario funcionario){
        return funcionarioService.registrarFuncionario(funcionario);
    }

    @PutMapping
    public Funcionario atualizarFuncionario(@RequestBody Funcionario funcionario){
        return  funcionarioService.atualizarFuncionario(funcionario);
    }

}
