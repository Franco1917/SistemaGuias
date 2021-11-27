package com.core.inscriptionAplication.controller;

import com.core.inscriptionAplication.repository.GuideRepository;
import com.core.inscriptionAplication.serviceimpl.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class Upload {

    @Autowired
    private AmazonClient amazonClient;
    @Autowired
    GuideRepository guideRepository;


    @RequestMapping("/")
    public String up(){
        return "upload";
    }


    @RequestMapping("/file")
    public String upload(@RequestParam("file") MultipartFile file){
        return this.amazonClient.uploadFile(file);
    }
}
