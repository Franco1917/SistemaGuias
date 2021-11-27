package com.core.inscriptionAplication.repository;

import com.core.inscriptionAplication.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.SQLException;
import java.util.List;

public interface AuthRepository extends JpaRepository<Authority,Long> {

    @Query(
            value = "INSERT INTO `authorities_users` (`usuario_id`, `authority_id`) VALUES (?, 1) ",
            nativeQuery = true
    )
    public void insertAuthoryty (@Param("usuario_id")int userId) throws SQLException;

}
