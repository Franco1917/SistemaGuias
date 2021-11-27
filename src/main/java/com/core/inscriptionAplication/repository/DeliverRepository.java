package com.core.inscriptionAplication.repository;

import com.core.inscriptionAplication.entity.Deliver;
import com.core.inscriptionAplication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DeliverRepository extends JpaRepository<Deliver, Long> {

    public List<Deliver> findAllByUser(User user);
    public List <Deliver> findAllByUserId(Long id);
    public Optional<Deliver> findById(Long id);
}
