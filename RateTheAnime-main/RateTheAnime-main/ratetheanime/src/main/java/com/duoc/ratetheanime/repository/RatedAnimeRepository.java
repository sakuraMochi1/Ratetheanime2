package com.duoc.ratetheanime.repository;

import com.duoc.ratetheanime.model.RatedAnime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface RatedAnimeRepository extends JpaRepository<RatedAnime, Integer> {
    
}
