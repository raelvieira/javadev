package br.com.mobicare.collaborator.modules.user.repository;

import br.com.mobicare.collaborator.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    @Query("FROM Profile p WHERE p.name IN(:names)")
    List<Profile> findByName(@Param("names") List<String> name);
}