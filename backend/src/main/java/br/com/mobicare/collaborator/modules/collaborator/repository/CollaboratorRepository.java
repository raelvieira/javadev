package br.com.mobicare.collaborator.modules.collaborator.repository;

import br.com.mobicare.collaborator.models.Collaborator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CollaboratorRepository extends JpaRepository<Collaborator, Integer> {
    @Query("SELECT COUNT(*) " +
            "FROM Collaborator c " +
            "WHERE c.sector.id = :idSector " +
            "AND c.deletedAt is null")
    Long findCountCollaboratorsBySector(@Param("idSector") Integer idSector);

    @Query("SELECT COUNT(*) " +
            "FROM Collaborator c " +
            "WHERE c.sector.id = :idSector " +
            "AND c.birthday > :date " +
            "AND c.deletedAt is null")
    Long findCountAllByBirthdayAfterAndSector(@Param("idSector") Integer idSector, @Param("date") LocalDate date);

    @Query("SELECT COUNT(*) " +
            "FROM Collaborator c " +
            "WHERE c.birthday < :date " +
            "AND c.deletedAt is null")
    Long findCountAllByBirthdayBefore(@Param("date") LocalDate date);

    @Query("SELECT COUNT(*) " +
            "FROM Collaborator c " +
            "WHERE c.deletedAt is null")
    Long findCountAll();

    @Query("FROM Collaborator c " +
            "LEFT JOIN FETCH c.sector s " +
            "WHERE c.deletedAt is null " +
            "GROUP BY s.id, c.id")
    List<Collaborator> findAllGroupBySector();

    @Query("FROM Collaborator c " +
            "JOIN FETCH c.sector " +
            "WHERE c.id = :id " +
            "AND c.deletedAt is null")
    Optional<Collaborator> findById(@Param("id") Integer id);

    @Query("FROM Collaborator c " +
            "JOIN FETCH c.sector " +
            "WHERE c.cpf = :cpf " +
            "AND c.deletedAt is null")
    Optional<Collaborator> findByCpf(@Param("cpf") String cpf);
}
