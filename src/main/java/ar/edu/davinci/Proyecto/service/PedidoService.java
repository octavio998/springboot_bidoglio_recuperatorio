package ar.edu.davinci.Proyecto.service;

import ar.edu.davinci.Proyecto.model.dto.PedidoDTO;
import ar.edu.davinci.Proyecto.model.Pedido;
import ar.edu.davinci.Proyecto.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public PedidoDTO agregarPedido(PedidoDTO pedidoDTO) {
        Pedido nuevoPedido = new Pedido(pedidoDTO.getDireccionEnvio(), pedidoDTO.getTotal());
        Pedido pedidoGuardado = pedidoRepository.save(nuevoPedido);
        return new PedidoDTO(pedidoGuardado.getId(), pedidoGuardado.getFecha(), pedidoGuardado.getDireccionEnvio(), pedidoGuardado.getTotal());
    }

    public PedidoDTO obtenerPedidoPorId(Long id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();
            return new PedidoDTO(pedido.getId(), pedido.getFecha(), pedido.getDireccionEnvio(), pedido.getTotal());
        } else {
            return null;
        }
    }

    public List<PedidoDTO> listarPedidos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream()
                .map(pedido -> new PedidoDTO(pedido.getId(), pedido.getFecha(), pedido.getDireccionEnvio(), pedido.getTotal()))
                .collect(Collectors.toList());
    }

    public PedidoDTO editarPedido(Long id, PedidoDTO pedidoDTO) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();
            pedido.setDireccionEnvio(pedidoDTO.getDireccionEnvio());
            pedido.setTotal(pedidoDTO.getTotal());
            pedidoRepository.save(pedido);
            return new PedidoDTO(pedido.getId(), pedido.getFecha(), pedido.getDireccionEnvio(), pedido.getTotal());
        } else {
            return null;
        }
    }

    public void eliminarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}
