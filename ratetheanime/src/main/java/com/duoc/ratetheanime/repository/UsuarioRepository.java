package com.duoc.ratetheanime.repository;

import com.duoc.ratetheanime.model.Usuario_Pagina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario_Pagina, Integer> {
    Optional<Usuario_Pagina> findByUsername(String username);
}