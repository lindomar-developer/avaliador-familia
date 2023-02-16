package br.com.digix.avaliadorfamilia.repository;

import br.com.digix.avaliadorfamilia.domain.Familias;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamiliaRepository extends JpaRepository<Familias, Long> {
}
