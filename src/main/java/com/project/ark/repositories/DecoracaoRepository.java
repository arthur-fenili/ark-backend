package com.project.ark.repositories;

import com.project.ark.models.Decoracao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DecoracaoRepository extends JpaRepository<Decoracao, Long> {
}
