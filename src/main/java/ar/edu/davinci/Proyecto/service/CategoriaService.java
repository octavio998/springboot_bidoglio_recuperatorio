package ar.edu.davinci.Proyecto.service;

import ar.edu.davinci.Proyecto.model.dto.CategoriaDTO;
import ar.edu.davinci.Proyecto.model.Categoria;
import ar.edu.davinci.Proyecto.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    @Autowired
    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public CategoriaDTO agregarCategoria(CategoriaDTO categoriaDTO) {
        Categoria nuevaCategoria = new Categoria(categoriaDTO.getNombre());
        Categoria categoriaGuardada = categoriaRepository.save(nuevaCategoria);
        return new CategoriaDTO(categoriaGuardada.getId(), categoriaGuardada.getNombre());
    }

    public CategoriaDTO obtenerCategoriaPorId(Long id) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if (categoriaOptional.isPresent()) {
            Categoria categoria = categoriaOptional.get();
            return new CategoriaDTO(categoria.getId(), categoria.getNombre());
        } else {
            return null;
        }
    }

    public List<CategoriaDTO> listarCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(categoria -> new CategoriaDTO(categoria.getId(), categoria.getNombre()))
                .collect(Collectors.toList());
    }
}
