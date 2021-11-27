package com.core.inscriptionAplication.repository;

import com.core.inscriptionAplication.entity.Guide;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface GuideRepository extends CrudRepository<Guide, Long> {
   public List<Guide> findAllByTeacherId(Long id);
   public List<Guide> findAllBySubjectId(Long id);
   // public Optional<Guide> ListAll();
}
