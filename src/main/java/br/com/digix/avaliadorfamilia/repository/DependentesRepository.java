package br.com.digix.avaliadorfamilia.repository;

import br.com.digix.avaliadorfamilia.domain.Dependentes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DependentesRepository extends JpaRepository<Dependentes, Long> {

    @Modifying
    @Query(value = " DELETE FROM Dependentes d where d.familias.id =:idFamilia ")
    void excluirDependentesPeloIdFamilia(@Param("idFamilia") Long idFamilia);
}
