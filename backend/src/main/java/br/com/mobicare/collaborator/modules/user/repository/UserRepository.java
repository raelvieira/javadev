package br.com.mobicare.collaborator.modules.user.repository;

import br.com.mobicare.collaborator.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("FROM User u JOIN FETCH u.profiles WHERE u.email = :email")
    Optional<User> findByEmail(@Param("email") String email);

    @Query("FROM User u JOIN FETCH u.profiles WHERE u.id = :id")
    Optional<User> findByIdAndDeletedAtNull(@Param("id") Integer id);

    @Query("FROM User u JOIN FETCH u.profiles WHERE u.id IN(:idUser)")
    List<User> findByAllId(@Param("idUser") List<Integer> idUser);
}