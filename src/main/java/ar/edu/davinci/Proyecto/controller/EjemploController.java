package ar.edu.davinci.Proyecto.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EjemploController {
    @GetMapping("/ejemplo")
    public String index(){
        return "hola";
    }

}
