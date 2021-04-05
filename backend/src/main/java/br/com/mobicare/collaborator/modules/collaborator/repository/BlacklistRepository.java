package br.com.mobicare.collaborator.modules.collaborator.repository;

import br.com.mobicare.collaborator.models.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BlacklistRepository extends JpaRepository<Blacklist, Integer> {
    @Query("FROM Blacklist b WHERE b.cpf = :cpf")
    Optional<Blacklist> findByCpf(@Param("cpf") String cpf);
}
