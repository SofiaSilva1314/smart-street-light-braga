package com.smartlight.streetlight.p9_dados_administracao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {
    Optional<Utilizador> findByUsername(String username);
}
