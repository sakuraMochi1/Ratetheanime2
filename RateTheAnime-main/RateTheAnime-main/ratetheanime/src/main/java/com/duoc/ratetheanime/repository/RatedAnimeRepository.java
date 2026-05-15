package com.duoc.ratetheanime.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duoc.ratetheanime.model.RatedAnime;

@Repository

public interface RatedAnimeRepository extends JpaRepository<RatedAnime, Integer> {
    
}
