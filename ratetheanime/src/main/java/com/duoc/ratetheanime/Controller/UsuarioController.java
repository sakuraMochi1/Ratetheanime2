package com.duoc.ratetheanime.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.ratetheanime.dto.UsuarioDTO;
import com.duoc.ratetheanime.model.Usuario_Pagina;
import com.duoc.ratetheanime.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> obtenerUsuarios() {
        log.info("GET /api/v1/usuarios - Obteniendo la lista de usuarios.");
        return ResponseEntity.ok(usuarioService.getUsuarios());
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> agregarUsuario(@Valid @RequestBody Usuario_Pagina usuario) {
        log.info("POST /api/v1/usuarios - Creando un usuario: {}", usuario.getUsername()); 
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUsuario(usuario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuario(@PathVariable Integer id) {
        log.info("GET /api/v1/usuarios/{} - Buscando usuario por ID", id);
        UsuarioDTO dto = usuarioService.getUsuarioById(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizarUsuario(@PathVariable Integer id, @Valid @RequestBody Usuario_Pagina usuario){
        log.info("PUT /api/v1/usuarios/{} - Iniciando actualización", id);
        usuario.setId(id);
        
        // 1. Llamamos al service que ahora devuelve un UsuarioDTO
        UsuarioDTO actualizado = usuarioService.updateUsuario(usuario);
        
        if (actualizado == null) {
            log.warn("PUT /api/v1/usuarios/{} - Falló la actualización, usuario no encontrado", id);
            return ResponseEntity.notFound().build();
        }
        
        log.info("PUT /api/v1/usuarios/{} - Usuario actualizado exitosamente", id);
        
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Integer id) {
        log.info("DELETE /api/v1/usuarios/{} - Solicitud de eliminación recibida", id);

        if (usuarioService.getUsuarioById(id) == null) {
            log.warn("DELETE /api/v1/usuarios/{} - Intento de borrar un usuario que no existe", id);
            return ResponseEntity.notFound().build();
        }

        usuarioService.deleteUsuario(id);
        log.info("DELETE /api/v1/usuarios/{} - Usuario eliminado exitosamente", id);
        return ResponseEntity.noContent().build();
    }
}