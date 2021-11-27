package com.core.inscriptionAplication.service;

import com.core.inscriptionAplication.entity.Guide;
import org.springframework.stereotype.Service;

@Service
public interface GuideService {
    public Iterable<Guide> getAll();
}
