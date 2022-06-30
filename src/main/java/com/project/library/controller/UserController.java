package com.project.library.controller;

import com.project.library.dao.ResourceRepository;
import com.project.library.dao.UserRepository;
import com.project.library.model.Resource;
import com.project.library.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ResourceRepository resourceRepository;

    @GetMapping(path = "")
    public String home(Model model, HttpSession session){

        /*if (session.getAttribute("FIRSTNAME").equals("Anas")){
            return "redirect:/user/index";
        }*/
        /*if (session.getAttribute("FIRSTNAME") != null){
            return "redirect:/user/index";
        }*/
        List<Resource> resources = resourceRepository.findTop3ByOrderById();
        model.addAttribute("resources", resources);
        return "home";
    }

    @GetMapping(path = "/user/login")
    public String login(Model model, HttpSession session){
        if (session.getAttribute("FIRSTNAME") != null){
            return "redirect:/user/logout";
        }
        //Display a form
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Login User");
        return "users/login";
    }

    @PostMapping(path = "/user/verify")
    public String verify(User user, RedirectAttributes ra, HttpSession session){

        User userFound = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());

        if (userFound != null){
            ra.addFlashAttribute("message", "Welcome "+userFound.getFirstName());
            session.setAttribute("FIRSTNAME", userFound.getFirstName());
            session.setAttribute("LASTNAME", userFound.getLastName());
            session.setAttribute("ROLE", userFound.getType());
            return "redirect:/";
        }
        else{
            ra.addFlashAttribute("message", "Please, verify your identity");
            return "redirect:/user/login";
        }
    }

    @GetMapping(path = "/user/logout")
    public String logout(HttpSession session){
        session.removeAttribute("FIRSTNAME");
        session.removeAttribute("LASTNAME");
        session.removeAttribute("ROLE");
        return "redirect:/user/login";
    }

    @GetMapping(path = "/user/index")
    public String index(Model model, HttpSession session){

        if(session.getAttribute("FIRSTNAME") != null){
            if (session.getAttribute("ROLE").toString().toUpperCase().equals("ADMIN")){

                List<User> users = userRepository.findAll();
                model.addAttribute("users", users);
                return "users/index";
            }
            else{
                return "redirect:/";
            }
        }
        else{
            return "redirect:/user/login";
        }
    }

    @GetMapping("/user/create")
    public String create(Model model, HttpSession session){

        if(session.getAttribute("FIRSTNAME") != null){
            if (session.getAttribute("ROLE").toString().toUpperCase().equals("ADMIN")){
                model.addAttribute("user", new User());
                model.addAttribute("pageTitle", "Add new User");
                return "users/formRegister";
            }
            else{
                return "redirect:/";
            }
        }
        else{
            return "redirect:/user/login";
        }
    }

    @PostMapping("/user/register")
    public String register(User user, RedirectAttributes ra){

        //Verify if this user exist
        //User userFound = userRepository.findByEmailAndCin(user.getEmail(), user.getCin());
        Long countEmail = userRepository.countByEmail(user.getEmail());
        Long countCin = userRepository.countByCin(user.getCin());
        if (countEmail < 1 && countCin < 1){
            userRepository.save(user);
            ra.addFlashAttribute("message", "The user has been register successfully "+ countCin+" " + countEmail);
            return "redirect:/user/index";
        }
        else{
            ra.addFlashAttribute("message", "This account already exist"+ countCin+" " + countEmail);
            return "redirect:/user/create";
        }
    }

    @PostMapping("/user/save")
    public String save(User user, RedirectAttributes ra){

        userRepository.save(user);
        ra.addFlashAttribute("message", "The user has been updated successfully");
        return "redirect:/user/index";
    }

    @GetMapping("/user/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes ra){
        Long count = userRepository.countById(id);
        if (count != null || count !=0){
            //User user = userRepository.getUserById(id);
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()){
                model.addAttribute("user", user);
                model.addAttribute("pageTitle", "Edit User : "+ user.get().getFirstName()+" "+user.get().getLastName() );
                return "users/formEdit";
            }
        }
        ra.addFlashAttribute("message", "The user hasn't been detected");
        return "redirect:/user/index";
    }
    @GetMapping("/user/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model, RedirectAttributes ra){
        Long count = userRepository.countById(id);
        if (count != null || count !=0){
            User user = userRepository.getById(id);
            //User user = userRepository.getUserById(id);
            if (user != null){
                model.addAttribute("user", user);
                model.addAttribute("pageTitle", "Detail User : "+ user.getFirstName()+" "+user.getLastName() );
                return "users/detail";
            }
        }
        ra.addFlashAttribute("message", "The user hasn't been detected");
        return "redirect:/user/index";
    }

    @GetMapping("/user/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes ra ){
        Long count = userRepository.countById(id);
        if (count != null || count !=0){
            userRepository.deleteById(id);
            ra.addFlashAttribute("message", "The user has been deleted"+ id);
        }
        else if(count == null || count ==0){
            ra.addFlashAttribute("message", "The user not found"+ id);
        }
        return "redirect:/user/index";
    }
        /*
    @GetMapping("/delete")
    public String delete(Long id){
        userRepository.deleteById(id);
        return "redirect:/user/index";
    }*/
}
