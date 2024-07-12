package ar.edu.davinci.Proyecto.controller;

import ar.edu.davinci.Proyecto.model.dto.PedidoDTO;
import ar.edu.davinci.Proyecto.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> agregarPedido(@RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO nuevoPedido = pedidoService.agregarPedido(pedidoDTO);
        return new ResponseEntity<>(nuevoPedido, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> obtenerPedidoPorId(@PathVariable Long id) {
        PedidoDTO pedidoDTO = pedidoService.obtenerPedidoPorId(id);
        if (pedidoDTO != null) {
            return ResponseEntity.ok().body(pedidoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listarPedidos() {
        List<PedidoDTO> pedidosDTO = pedidoService.listarPedidos();
        return ResponseEntity.ok().body(pedidosDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> editarPedido(@PathVariable Long id, @RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO pedidoActualizado = pedidoService.editarPedido(id, pedidoDTO);
        if (pedidoActualizado != null) {
            return ResponseEntity.ok().body(pedidoActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
        return ResponseEntity.noContent().build();
    }
}
