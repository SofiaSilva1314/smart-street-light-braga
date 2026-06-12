package com.smartlight.streetlight.p6_dados_monitorizacao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/** Repositorio JPA para Leitura. Suporta O2.2 consultar historico. */
public interface LeituraRepository extends JpaRepository<Leitura, Long> {
    List<Leitura> findByPosteIdOrderByInstanteDesc(Long posteId);
}
