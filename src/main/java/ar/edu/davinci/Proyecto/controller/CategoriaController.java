package ar.edu.davinci.Proyecto.controller;

import ar.edu.davinci.Proyecto.model.dto.CategoriaDTO;
import ar.edu.davinci.Proyecto.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @PostMapping
    public ResponseEntity<CategoriaDTO> agregarCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        CategoriaDTO nuevaCategoria = categoriaService.agregarCategoria(categoriaDTO);
        return new ResponseEntity<>(nuevaCategoria, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> obtenerCategoriaPorId(@PathVariable Long id) {
        CategoriaDTO categoriaDTO = categoriaService.obtenerCategoriaPorId(id);
        if (categoriaDTO != null) {
            return ResponseEntity.ok().body(categoriaDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> listarCategorias() {
        List<CategoriaDTO> categoriasDTO = categoriaService.listarCategorias();
        return ResponseEntity.ok().body(categoriasDTO);
    }
}
