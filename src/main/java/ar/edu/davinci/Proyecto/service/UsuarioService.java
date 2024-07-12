package ar.edu.davinci.Proyecto.service;

import ar.edu.davinci.Proyecto.model.dto.UsuarioDTO;
import ar.edu.davinci.Proyecto.model.Usuario;
import ar.edu.davinci.Proyecto.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO) {
        Usuario nuevoUsuario = new Usuario(usuarioDTO.getNombre(), usuarioDTO.getEmail(), usuarioDTO.getPassword(), usuarioDTO.getRol());
        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);
        return new UsuarioDTO(usuarioGuardado.getId(), usuarioGuardado.getNombre(), usuarioGuardado.getEmail(), usuarioGuardado.getPassword(), usuarioGuardado.getRol());
    }

    public UsuarioDTO obtenerUsuarioPorId(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            return new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getPassword(), usuario.getRol());
        } else {
            return null;
        }
    }

    public UsuarioDTO editarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setNombre(usuarioDTO.getNombre());
            usuario.setEmail(usuarioDTO.getEmail());
            usuario.setPassword(usuarioDTO.getPassword());
            usuario.setRol(usuarioDTO.getRol());
            usuarioRepository.save(usuario);
            return new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getEmail(), usuario.getPassword(), usuario.getRol());
        } else {
            return null;
        }
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
