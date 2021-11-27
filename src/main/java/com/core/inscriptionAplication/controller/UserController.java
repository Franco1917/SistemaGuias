package com.core.inscriptionAplication.controller;

import javax.validation.Valid;

import com.core.inscriptionAplication.entity.*;
import com.core.inscriptionAplication.repository.DeliverRepository;
import com.core.inscriptionAplication.repository.GuideRepository;
import com.core.inscriptionAplication.repository.UserRepository;
import com.core.inscriptionAplication.service.GuideService;
import com.core.inscriptionAplication.service.TeacherService;
import com.core.inscriptionAplication.serviceimpl.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.core.inscriptionAplication.service.SubjectService;
import com.core.inscriptionAplication.service.UserService;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	GuideService guideService;

	@Autowired
	SubjectService subjectservice;

	@Autowired
	TeacherService teacherService;

	@Autowired
	GuideRepository guideRepository;

	@Autowired
	DeliverRepository deliverRepository;
	@Autowired
	UserRepository userRepository;

	//@Autowired
	//InscriptionService inscriptionservice;
	@Autowired
	private AmazonClient amazonClient;
	public String fileUrl;
	public boolean subido;

	public User usuario;

	@GetMapping("/userForm")
	public String getUserForm(Model model) {
		model.addAttribute("userForm", new User());

		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("listTab", "active");
		return "userlist";
	}

	@GetMapping("/teacherlist")
	public String getTeacherList(Model model) {
		model.addAttribute("teacherlist", new Teacher());

		model.addAttribute("teacherList", teacherService.getAllTeacher());
		//model.addAttribute("formTab","active");
		return "userteacherlist";
	}

	@GetMapping("/subjectlist")
	public String getSubjectList(Model model) {


		model.addAttribute("Subjectlist", new Subject());
		model.addAttribute("teacher", teacherService.getAllTeacher());
		model.addAttribute("subjectList", subjectservice.getAllSubject());
		model.addAttribute("formTab", "active");
		return "usersubject";
	}


	@GetMapping("/showinscriptions/{name}")
	public String getEditTeacherForm(Model model, @PathVariable(name = "name") String className) throws Exception {
		//Subject subjectedit= subjectservice.getSubjectById(id);
		//Teacher teacheredit= teacherservice.getTeacherById(id);

		//nmodel.addAttribute("inscription", inscriptionservice.getByname(className));


		model.addAttribute("formTab", "active");
		model.addAttribute("editMode", true);

		return "insc";
	}

	@GetMapping("/guidelist")
	public String guidelist(Model model) {


		model.addAttribute("Subjectlist", new Subject());
		model.addAttribute("teacher", teacherService.getAllTeacher());
		model.addAttribute("subject", subjectservice.getAllSubject());
		model.addAttribute("formTab", "active");

		model.addAttribute("guideForm", new Guide());

		model.addAttribute("guideList", guideRepository.findAll());
		model.addAttribute("listTab", "active");


		return "userguide";
	}

	@GetMapping("/fileurl/{id}")
	public String getEditUserForm(Model model, @PathVariable(name = "id") Long id) throws Exception {

		model.addAttribute("formTab", "active");
		model.addAttribute("editMode", true);
		model.addAttribute("guidelist", guideRepository.findAllBySubjectId(id));
		return "userguidesubject";


	}

	@GetMapping("/linkurl/{id}")
	public String linkUrl(Model model, @PathVariable(name = "id") Long id) throws Exception {


		model.addAttribute("formTab", "active");
		model.addAttribute("editMode", true);
		model.addAttribute("guidelist", guideRepository.findAllByTeacherId(id));


		return "userteacherguide";
	}


	@GetMapping("/deliverlist/{username}")
	public String guide(Model model,@PathVariable(name = "username") String username) {
		User user1 = userRepository.findByUsername(username).get();
		this.usuario= user1;
		Long ide = user1.getId();
		model.addAttribute("Subjectlist", new Subject());
		model.addAttribute("teacher", teacherService.getAllTeacher());
		model.addAttribute("guide", guideRepository.findAll());
		model.addAttribute("formTab", "active");

		model.addAttribute("guideForm", new Deliver());

		model.addAttribute("guideList", guideRepository.findAll());
		model.addAttribute("listTab", "active");

		return "userdeliver";

	}

	@PostMapping("/fileup")
	public String upload(@RequestParam("file") MultipartFile file, Model model) {
		this.fileUrl = this.amazonClient.uploadFile(file);

		subido = true;

		model.addAttribute("Subjectlist", new Subject());
		model.addAttribute("teacher", teacherService.getAllTeacher());
		model.addAttribute("subject", subjectservice.getAllSubject());
		model.addAttribute("formTab", "active");

		model.addAttribute("guideForm", new Guide());
		model.addAttribute("subido", subido);

		model.addAttribute("guideList", guideRepository.findAll());
		model.addAttribute("listTab", "active");
		return guide(model,usuario.getUsername());

	}

	@PostMapping("/guidelist")
	public String saveGuides( @ModelAttribute("guideForm")Deliver deliver, BindingResult result, ModelMap model, Model modela ) throws Exception {
	//	@ModelAttribute("guideForm")Deliver deliver, BindingResult result, ModelMap model

		model.addAttribute("guideForm", new Deliver());

		deliver.setUse(usuario);
		deliver.setFile(fileUrl);

		deliverRepository.save(deliver);


		return linkUrla(modela, usuario.getUsername());
	}

	@GetMapping("/deliver/{username}")
	public String linkUrla(Model model, @PathVariable(name = "username") String username) throws Exception {
        User user1 = userRepository.findByUsername(username).get();
        Long ide = user1.getId();

		model.addAttribute("formTab", "active");
		model.addAttribute("editMode", true);
		model.addAttribute("guidelist", deliverRepository.findAllByUserId(ide));


		return "mideliver";
	}

}
