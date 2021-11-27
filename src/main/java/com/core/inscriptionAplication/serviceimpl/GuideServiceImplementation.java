package com.core.inscriptionAplication.serviceimpl;

import com.core.inscriptionAplication.entity.Guide;
import com.core.inscriptionAplication.repository.GuideRepository;
import com.core.inscriptionAplication.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuideServiceImplementation implements GuideService {

    @Autowired
    GuideRepository guideRepository;
    @Override
    public Iterable<Guide> getAll() {
        return guideRepository.findAll();

    }
}
