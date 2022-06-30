package com.project.library.controller;

import com.project.library.dao.PositionRepository;
import com.project.library.dao.ResourceRepository;
import com.project.library.model.Position;
import com.project.library.model.Resource;
import com.project.library.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class ResourceController {

    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private PositionRepository positionRepository;

    @GetMapping(path = "/resource/index")
    public String index(Model model){
        List<Resource> resources = resourceRepository.findAll();
        model.addAttribute("resources", resources);
        return "resources/index";
    }

    @GetMapping("/resource/create")
    public String create(Model model){
        model.addAttribute("resource", new Resource());
        model.addAttribute("pageTitle", "Add new Resource");

        List<Position> positions = positionRepository.findAll();
        model.addAttribute("positions", positions);

        return "resources/form";
    }

    @PostMapping("/resource/save")
    public String save(Resource resource, RedirectAttributes ra){
        resourceRepository.save(resource);
        ra.addFlashAttribute("message", "The resource has been saved successfully");
        return "redirect:/resource/index";
    }
    @GetMapping("/resource/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes ra){
        Long count = resourceRepository.countById(id);
        if (count != null || count !=0){
            //User user = userRepository.getUserById(id);
            Optional<Resource> resource = resourceRepository.findById(id);
            if (resource.isPresent()){
                model.addAttribute("resource", resource);
                model.addAttribute("pageTitle", "Edit : ' "+ resource.get().getTitle().toUpperCase()+" '" );

                List<Position> positions = positionRepository.findAll();
                model.addAttribute("positions", positions);

                return "resources/form";
            }
        }
        ra.addFlashAttribute("message", "The user hasn't been detected");
        return "redirect:/resource/index";
    }
    @GetMapping("/resource/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model, RedirectAttributes ra){
        Long count = resourceRepository.countById(id);
        if (count != null || count !=0){
            Resource resource = resourceRepository.getById(id);
            //User user = userRepository.getUserById(id);
            if (resource != null){
                model.addAttribute("resource", resource);
                model.addAttribute("pageTitle", "Detail Resource : "+ resource.getTitle()+" "+resource.getAuthor() );
                return "resources/detail";
            }
        }
        ra.addFlashAttribute("message", "The Resource hasn't been detected");
        return "redirect:/resource/index";
    }


    @GetMapping("/resource/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes ra ){
        Long count = resourceRepository.countById(id);
        if (count != null || count !=0){
            resourceRepository.deleteById(id);
            ra.addFlashAttribute("message", "The Resource has been deleted"+ id);
        }
        else if(count == null || count ==0){
            ra.addFlashAttribute("message", "The Resource not found"+ id);
        }
        return "redirect:/resource/index";
    }
}
