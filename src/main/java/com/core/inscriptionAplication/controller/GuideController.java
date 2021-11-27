package com.core.inscriptionAplication.controller;


import com.core.inscriptionAplication.entity.Guide;
import com.core.inscriptionAplication.repository.GuideRepository;
import com.core.inscriptionAplication.serviceimpl.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/guide")
public class GuideController {

     @Autowired
    private AmazonClient amazonClient;
     @Autowired
    GuideRepository guideRepository;




    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file){
        return this.amazonClient.uploadFile(file);
    }

   // @PostMapping
           // public Guide createguide(@ModelAttribute("guide") Guide guide, BindingResult result, ModelMap model)
    //{ //Guide guide1= new Guide(guide.getSubject(),guide.getTeacher(),guide.getCreationDate(),guide.getId(),guide.getFile());
      //  return guideRepository.save(guide1);
   // }

}
