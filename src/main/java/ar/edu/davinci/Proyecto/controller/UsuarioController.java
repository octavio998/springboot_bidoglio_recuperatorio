package ar.edu.davinci.Proyecto.controller;

import ar.edu.davinci.Proyecto.model.dto.UsuarioDTO;
import ar.edu.davinci.Proyecto.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> registrarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO nuevoUsuario = usuarioService.registrarUsuario(usuarioDTO);
        return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuarioPorId(@PathVariable Long id) {
        UsuarioDTO usuarioDTO = usuarioService.obtenerUsuarioPorId(id);
        if (usuarioDTO != null) {
            return ResponseEntity.ok().body(usuarioDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> editarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO usuarioActualizado = usuarioService.editarUsuario(id, usuarioDTO);
        if (usuarioActualizado != null) {
            return ResponseEntity.ok().body(usuarioActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
