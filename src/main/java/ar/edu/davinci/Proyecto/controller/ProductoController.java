package ar.edu.davinci.Proyecto.controller;

import ar.edu.davinci.Proyecto.model.dto.ProductoDTO;
import ar.edu.davinci.Proyecto.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> agregarProducto(@RequestBody ProductoDTO productoDTO) {
        ProductoDTO nuevoProducto = productoService.agregarProducto(productoDTO);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> obtenerProductoPorId(@PathVariable Long id) {
        ProductoDTO productoDTO = productoService.obtenerProductoPorId(id);
        if (productoDTO != null) {
            return ResponseEntity.ok().body(productoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> listarProductos() {
        List<ProductoDTO> productosDTO = productoService.listarProductos();
        return ResponseEntity.ok().body(productosDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> editarProducto(@PathVariable Long id, @RequestBody ProductoDTO productoDTO) {
        ProductoDTO productoActualizado = productoService.editarProducto(id, productoDTO);
        if (productoActualizado != null) {
            return ResponseEntity.ok().body(productoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }
}
