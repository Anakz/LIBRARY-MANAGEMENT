package com.project.library.controller;

import com.project.library.dao.PositionRepository;
import com.project.library.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class PositionController {

    @Autowired
    private PositionRepository positionRepository;

    @GetMapping(path = "/position/index")
    public String index(Model model){
        List<Position> positions = positionRepository.findAll();
        model.addAttribute("positions", positions);
        return "positions/index";
    }
}
