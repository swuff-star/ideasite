package com.codingdojo.ideasite.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.codingdojo.ideasite.models.Comment;
import com.codingdojo.ideasite.models.FileUploadUtil;
import com.codingdojo.ideasite.models.Idea;
import com.codingdojo.ideasite.models.IdeaForm;
import com.codingdojo.ideasite.models.LoginUser;
import com.codingdojo.ideasite.models.User;
import com.codingdojo.ideasite.services.CommentService;
import com.codingdojo.ideasite.services.IdeaService;
import com.codingdojo.ideasite.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
@Controller
public class HomeController {
	
	private static final String UPLOAD_DIR = "~/Downloads/";
    
    @Autowired
    private UserService userServ;
    
    @Autowired
    private IdeaService ideaServ;
    
    @Autowired
    private CommentService commentService;
    
    @GetMapping("/")
    public String index(Model model) {
    
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
        return "index";
    }
    
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser, 
            BindingResult result, Model model, HttpSession session) {
        
    	User registeredUser = userServ.register(newUser, result);
        
        if(result.hasErrors()) {

            model.addAttribute("newLogin", new LoginUser());
            return "index";
        }
        
        session.setAttribute("userId", registeredUser.getId());
    
        return "redirect:/home";
    }
    
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, 
            BindingResult result, Model model, HttpSession session) {
        
    	User user = userServ.login(newLogin, result);
    
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "index";
        }
    
        session.setAttribute("userId", user.getId());
    
        return "redirect:/home";
    }
    
    @GetMapping("/home")
    public String home(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/";
        }

        User user = userServ.findUser(userId);
        model.addAttribute("username", user.getUserName());

        List<Idea> ideas = ideaServ.ideasAlphabetical();
        model.addAttribute("ideas", ideas);

        return "home";
    }

    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
    
    @GetMapping("/ideas/new")
    public String createProduct(Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/";
        }
        
        model.addAttribute("ideaForm", new IdeaForm());
        return "create_concept";
    }
    
    @PostMapping("/ideas")
    public String saveIdea(@Valid @ModelAttribute("ideaForm") IdeaForm prodForm,
                           BindingResult result,
                           Model model,
                           HttpSession session,
                           @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/";
        }
        
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getAllErrors());
            model.addAttribute("prodForm", prodForm);
            
            return "create_conceptt";
        }
        
        
        String uploadDir = "userContent/"; 

        User user = userServ.findUser(userId);
        
        Idea idea = new Idea();
        idea.setpName(prodForm.getName());
        idea.setpDesc(prodForm.getDesc());
        idea.setUser(user);
        
        String fileName = StringUtils.cleanPath(imageFile.getOriginalFilename());
        
        String fullDir = "/" + uploadDir + fileName;
   	
        idea.setpImage(fullDir);
        
        ideaServ.createIdea(idea);
        
        FileUploadUtil.saveFile(uploadDir, fileName, imageFile);
        
        return "redirect:/home";
    }

    @GetMapping("/ideas/{id}")
    public String ideaInfo(@PathVariable("id") Long id, Model model, HttpSession session) {
        Idea idea = ideaServ.findIdea(id);
        model.addAttribute("idea", idea);

        List<Comment> comments = idea.getComments();
        model.addAttribute("comments", comments);
        
        List<String> commenterNames = comments.stream().map(Comment::getCommenterName).collect(Collectors.toList());
        model.addAttribute("commenterNames", commenterNames);


        String imageData = FileUploadUtil.convertImageToBase64(idea.getImageData());
        model.addAttribute("imageData", imageData);
        
        Long userId = (Long) session.getAttribute("userId");
        
        if (userId == null) {
            return "redirect:/";
        }
        
        if (userId != null && idea.getUser().getId().equals(userId)) {
        	model.addAttribute("isOwner", true);
        } else {
        	model.addAttribute("isOwner", false);
        }

        model.addAttribute("newComment", new Comment());

        return "concept_info";
    }
    
    @PostMapping("/ideas/{ideaId}/add-comment")
    public String addComment(@PathVariable("ideaId") Long ideaId, @ModelAttribute Comment newComment, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }

        Idea idea = ideaServ.findIdea(ideaId);

        User user = userServ.findUser(userId);

        newComment.setIdea(idea);
        newComment.setUser(user);

        commentService.createComment(newComment);

        return "redirect:/ideas/" + ideaId;
    }
 
    @PostMapping("/ideas/{id}/delete")
    public String deleteIdea(@PathVariable("id") Long pId, HttpSession session) {
    	Long userId = (Long) session.getAttribute("userId");
        Idea idea = ideaServ.findIdea(pId);
        
        if (userId == null || !idea.getUser().getId().equals(userId)) {
        	return "redirect:/home";
        }
        
        ideaServ.deleteIdea(pId);
        
        return "redirect:/home";
    }

    
    @GetMapping("/ideas/{id}/edit")
    public String editIdea(@PathVariable("id") Long gameId, Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        Idea idea = ideaServ.findIdea(gameId);
        
        if (userId == null || !idea.getUser().getId().equals(userId)) {
            return "redirect:/home";
        }
        
        IdeaForm ideaForm = new IdeaForm();
        ideaForm.setName(idea.getpName());
        ideaForm.setDesc(idea.getpDesc());
        
        model.addAttribute("ideaForm", ideaForm);
        model.addAttribute("idea", idea);
        
        return "edit_concept";
    }
    
    @PostMapping("/ideas/{id}/update")
    public String updateIdea(@PathVariable("id") Long pId, @Valid @ModelAttribute("ideaForm") IdeaForm ideaForm,
                             BindingResult result, Model model, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        Idea idea = ideaServ.findIdea(pId);
        
        if (userId == null) {
            return "redirect:/home";
        }
        
        if (!idea.getUser().getId().equals(userId)) {
            return "redirect:/home";
        }
        
        if (result.hasErrors()) {
            return "edit_concept";
        }
        
        idea.setpName(ideaForm.getName());
        idea.setpDesc(ideaForm.getDesc());
        
        ideaServ.updateIdeaFromIdea(idea);
        
        return "redirect:/home";
    }

    @GetMapping("/users/{userId}/concepts")
    public String userProducts(@PathVariable("userId") Long userId, Model model) {
        List<Idea> userIdeas = ideaServ.findIdeaByUserId(userId);
        model.addAttribute("userIdeas", userIdeas);
        return "user_concepts";
    }
}


