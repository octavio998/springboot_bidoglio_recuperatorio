package ar.edu.davinci.Proyecto.service;

import ar.edu.davinci.Proyecto.model.dto.ProductoDTO;
import ar.edu.davinci.Proyecto.model.Producto;
import ar.edu.davinci.Proyecto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public ProductoDTO agregarProducto(ProductoDTO productoDTO) {
        Producto nuevoProducto = new Producto(productoDTO.getNombre(), productoDTO.getPrecio(), productoDTO.getDescripcion());
        Producto productoGuardado = productoRepository.save(nuevoProducto);
        return new ProductoDTO(productoGuardado.getId(), productoGuardado.getNombre(), productoGuardado.getPrecio(), productoGuardado.getDescripcion());
    }

    public ProductoDTO obtenerProductoPorId(Long id) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            return new ProductoDTO(producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getDescripcion());
        } else {
            return null;
        }
    }

    public List<ProductoDTO> listarProductos() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream()
                .map(producto -> new ProductoDTO(producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getDescripcion()))
                .collect(Collectors.toList());
    }

    public ProductoDTO editarProducto(Long id, ProductoDTO productoDTO) {
        Optional<Producto> productoOptional = productoRepository.findById(id);
        if (productoOptional.isPresent()) {
            Producto producto = productoOptional.get();
            producto.setNombre(productoDTO.getNombre());
            producto.setPrecio(productoDTO.getPrecio());
            producto.setDescripcion(productoDTO.getDescripcion());
            productoRepository.save(producto);
            return new ProductoDTO(producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getDescripcion());
        } else {
            return null;
        }
    }

    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }
}
