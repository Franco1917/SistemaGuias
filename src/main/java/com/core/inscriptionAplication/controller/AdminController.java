package com.core.inscriptionAplication.controller;

import javax.validation.Valid;

import com.core.inscriptionAplication.entity.*;
import com.core.inscriptionAplication.repository.AuthRepository;
import com.core.inscriptionAplication.repository.DeliverRepository;
import com.core.inscriptionAplication.repository.GuideRepository;
import com.core.inscriptionAplication.serviceimpl.AmazonClient;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.core.inscriptionAplication.service.SubjectService;
import com.core.inscriptionAplication.service.TeacherService;
import com.core.inscriptionAplication.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.sql.SQLException;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Autowired 
	UserService userService;
	
	@Autowired
	SubjectService subjectservice;
	@Autowired
	TeacherService teacherservice;
	@Autowired
	AuthRepository authRepository;
	@Autowired
	GuideRepository guideRepository;
	@Autowired
	DeliverRepository deliverRepository;


	Deliver deliver;

	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
	public String fileUrl;

	public boolean subido;


	private Set<Authority> authorities;

	@Autowired
	private AmazonClient amazonClient;





	@PostMapping("/fileup")
	public String upload(@RequestParam("file") MultipartFile file, Model model){
		 this.fileUrl=this.amazonClient.uploadFile(file);

		 subido= true;

		model.addAttribute("Subjectlist", new Subject());
		model.addAttribute("teacher",teacherservice.getAllTeacher());
		model.addAttribute("subject", subjectservice.getAllSubject());
		model.addAttribute("formTab","active");

		model.addAttribute("guideForm", new Guide());
		model.addAttribute("subido", subido);

		model.addAttribute("guideList", guideRepository.findAll());
		model.addAttribute("listTab","active");
		 return "guides";
	}



	
	@GetMapping("/userForm")
	public String getUserForm(Model model) {
		model.addAttribute("userForm", new User());
		
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("listTab","active");
		return "userlist";
	}
		
		@GetMapping("/list")
		public String getSubjectList(Model model) {
			model.addAttribute("Subjectlist", new Subject());
			model.addAttribute("teacher",teacherservice.getAllTeacher());
			model.addAttribute("subjectList", subjectservice.getAllSubject());
			model.addAttribute("formTab","active");
			return "subjectlist";
}
          @GetMapping("/deliver")
		  public String deliver(Model model ){
			  model.addAttribute("teacherlist", new Deliver());

			  model.addAttribute("formTab", "active");
			  model.addAttribute("editMode", true);
			  model.addAttribute("guidelist", deliverRepository.findAll());

			  return "deliveradmin";

		  }

	@GetMapping("/editdeliver/{id}")
	public String getEditDeliverForm(Model model, @PathVariable(name="id") Long id) throws Exception {
		//Subject subjectedit= subjectservice.getSubjectById(id);

		Deliver teacheredit= deliverRepository.findById(id).get();
		this.deliver=teacheredit;


		model.addAttribute("teacherlist", teacheredit);

		model.addAttribute("guidelist",deliverRepository.findAll());

		model.addAttribute("formTab","active");
		model.addAttribute("editMode",true);

		return "deliveradmin";
	}






		
		@PostMapping("/list")
		public String postUserForm(@Valid @ModelAttribute("Subjectlist")Subject subject, BindingResult result, ModelMap model) 
		 {
			if(result.hasErrors()) {
				model.addAttribute("Subjectlist", subject);
			
				model.addAttribute("formTab","active");
			}else {
				model.addAttribute("Subjectlist", new Subject());
				subjectservice.createSubject1(subject);
			}
			model.addAttribute("teacher",teacherservice.getAllTeacher());
			model.addAttribute("subjectList", subjectservice.getAllSubject());
			return "subjectlist";
			}
		@GetMapping ("/fileurl/{id}")
		public String getEditUserForm(Model model, @PathVariable(name="id") Long id) throws Exception {

		model.addAttribute("formTab","active");
		model.addAttribute("editMode",true);
			model.addAttribute("guidelist", guideRepository.findAllBySubjectId(id));
		return "guidessubject";
		}

	@GetMapping("/linkurl/{id}")
	public String linkUrl(Model model, @PathVariable(name="id") Long id) throws Exception {


		model.addAttribute("formTab","active");
		model.addAttribute("editMode",true);
		model.addAttribute("guidelist", guideRepository.findAllByTeacherId(id));


		return "guidesteacher";
	}
		
		@GetMapping("/deleteSubject/{id}")
		public String deleteUser(Model model, @PathVariable(name="id") Long id) {
			try {
				subjectservice.deleteSubject(id);
			} catch (Exception e) {
				model.addAttribute("deleteError","The user could not be deleted.");
			}
			return getSubjectList(model);
		}

		
		@GetMapping("/teacherlist")
		public String getTeacherList(Model model) {
			model.addAttribute("teacherlist", new Teacher());
			
			model.addAttribute("teacherList", teacherservice.getAllTeacher());
			//model.addAttribute("formTab","active");
			return "teacherlist";
		}
		
		@PostMapping("/teacherlist")
		public String postTeacherForm(@Valid @ModelAttribute("teacherlist")Teacher teacher, BindingResult result, ModelMap model) 
		 {
			if(result.hasErrors()) {
				model.addAttribute("teacherlist", teacher);
			
				model.addAttribute("formTab","active");
			}else {
				model.addAttribute("teacherlist", new Teacher());
				
				teacherservice.createSubject1(teacher);
			}
			model.addAttribute("teacherList",teacherservice.getAllTeacher());
			return "teacherlist";


		 }
	@PostMapping("/deliverylist")
	public String deliverypost(@Valid @ModelAttribute("teacherlist")Deliver deliver, BindingResult result, ModelMap model) {

		model.addAttribute("teacherlist", new Deliver());
		this.deliver.setNote(deliver.getNote());


		deliverRepository.save(this.deliver);
		model.addAttribute("formTab", "active");
		model.addAttribute("editMode", true);
		model.addAttribute("guidelist", deliverRepository.findAll());


return "deliveradmin";
	}


	@PostMapping("/userlist")
	public String postUserrForm(@Valid @ModelAttribute("userForm")User user, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			model.addAttribute("userForm", user);

			model.addAttribute("formTab", "active");
		} else {
			model.addAttribute("userForm", new User());

            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

			userService.createSubject1(user);



		}
		model.addAttribute("userList", userService.getAllUsers());
		return "userList";
	}

		@GetMapping("/editTeacher/{id}")
		public String getEditTeacherForm(Model model, @PathVariable(name="id") Long id) throws Exception {
		//Subject subjectedit= subjectservice.getSubjectById(id);
			Teacher teacheredit= teacherservice.getTeacherById(id);
		model.addAttribute("teacherlist", teacheredit);
		
		model.addAttribute("teacherList",teacherservice.getAllTeacher());
		
		model.addAttribute("formTab","active");
		model.addAttribute("editMode",true);
		
		return "teacherlist";
		}
         
		@GetMapping("/deleteTeacher/{id}")
		public String deleteTeacher(Model model, @PathVariable(name="id") Long id) {
			try {
				//subjectservice.deleteSubject(id);
				teacherservice.deleteTeacher(id);
			} catch (Exception e) {
				model.addAttribute("deleteError","The user could not be deleted.");
			}
			return getTeacherList(model);
		}

		@GetMapping("/guidelist")
	public String guide(Model model){
			model.addAttribute("Subjectlist", new Subject());
			model.addAttribute("teacher",teacherservice.getAllTeacher());
			model.addAttribute("subject", subjectservice.getAllSubject());
			model.addAttribute("formTab","active");

			model.addAttribute("guideForm", new Guide());

			model.addAttribute("guideList", guideRepository.findAll());
			model.addAttribute("listTab","active");



		return "guides";
		}
		@PostMapping("/guidelist")
		public String saveGuides(@Valid @ModelAttribute("guideForm")Guide guide, BindingResult result, ModelMap model){


			model.addAttribute("guideForm", new Guide());

            guide.setTeacher(guide.getSubject().getTeacher());
            guide.setFile(fileUrl);

			guideRepository.save(guide);


			return "guides";
		}

}


