package com.duoc.ratetheanime.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.duoc.ratetheanime.dto.UsuarioDTO;
import com.duoc.ratetheanime.model.Usuario_Pagina;
import com.duoc.ratetheanime.repository.UsuarioRepository;

@Slf4j
@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    // 1. LISTAR (Ya lo tenías bien)
    public List<UsuarioDTO> getUsuarios() {
        log.info("Consultando la lista completa de usuarios desde la base de datos.");
        List<Usuario_Pagina> listaUsuarios = usuarioRepository.findAll();
        return listaUsuarios.stream().map(u -> {
            UsuarioDTO dto = new UsuarioDTO();
            dto.setUsername(u.getUsername());
            return dto;
        }).toList();
    }

    // 2. GUARDAR (Cambiado a UsuarioDTO)
    public UsuarioDTO saveUsuario(Usuario_Pagina usuario) {
        log.info("Guardando un nuevo usuario en la base de datos: {}", usuario.getUsername());
        Usuario_Pagina entidadGuardada = usuarioRepository.save(usuario);
        
        UsuarioDTO dto = new UsuarioDTO();
        dto.setUsername(entidadGuardada.getUsername());
        return dto;
    }

    // 3. BUSCAR POR ID (Cambiado a UsuarioDTO)
    public UsuarioDTO getUsuarioById(Integer id) {
        log.info("Buscando usuario con ID: {}", id);
        Usuario_Pagina u = usuarioRepository.findById(id).orElse(null);
        
        if (u == null) return null;

        UsuarioDTO dto = new UsuarioDTO();
        dto.setUsername(u.getUsername());
        return dto;
    }

    // 4. ACTUALIZAR (Cambiado a UsuarioDTO)
    public UsuarioDTO updateUsuario(Usuario_Pagina usuario){
        log.info("Actualizando usuario con ID: {}", usuario.getId());
        if (!usuarioRepository.existsById(usuario.getId())) {
            log.warn("El usuario con ID: {} no existe.", usuario.getId());
            return null;
        }
        Usuario_Pagina entidadActualizada = usuarioRepository.save(usuario);
        
        UsuarioDTO dto = new UsuarioDTO();
        dto.setUsername(entidadActualizada.getUsername());
        return dto;
    }

    // 5. ELIMINAR (Se mantiene void, está perfecto)
    public void deleteUsuario(Integer id) {
        log.info("Eliminando usuario con ID: {}", id);
        usuarioRepository.deleteById(id);
    }
}
