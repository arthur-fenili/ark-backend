package com.project.ark.repositories;

import com.project.ark.models.Buffet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuffetRepository extends JpaRepository<Buffet, Long> {
}
