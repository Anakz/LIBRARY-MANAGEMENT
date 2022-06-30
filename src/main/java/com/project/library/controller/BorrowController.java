package com.project.library.controller;

import com.project.library.dao.BorrowRepository;
import com.project.library.dao.CopyRepository;
import com.project.library.dao.MemberRepository;
import com.project.library.model.Borrow;
import com.project.library.model.Copy;
import com.project.library.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Controller
public class BorrowController {

    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CopyRepository copyRepository;

    @GetMapping(path = "/borrow/index")
    public String index(Model model){
        List<Borrow> borrows = borrowRepository.findAll();
        model.addAttribute("borrows", borrows);
        return "borrows/index";
    }

    @GetMapping("/borrow/create")
    public String create(Model model){
        model.addAttribute("borrow", new Borrow());
        model.addAttribute("pageTitle", "Add new Borrow");

        List<Member> members = memberRepository.findAll();
        List<Copy> copies = copyRepository.findAll();

        model.addAttribute("members", members);
        model.addAttribute("copies", copies);
        return "borrows/form";
    }
    @PostMapping("/borrow/save")
    public String save(Borrow borrow, RedirectAttributes ra, Model model){

        /*model.addAttribute()
        if (borrow.getMember().getId() == null){
            return "borrows/form";
        }*/
        Calendar c = Calendar.getInstance();
        c.setTime(borrow.getStartDate());
        c.add(Calendar.DATE, 7);
        Date dt =new Date(c.getTimeInMillis());
        borrow.setEndDate(dt);
        borrow.setDuration(7);
        borrowRepository.save(borrow);
        ra.addFlashAttribute("message", "The borrow has been saved successfully");
        return "redirect:/borrow/index";
    }

    //borrow.setDuration(7);
    //borrow.setEndDate(borrow.getStartDate()+ 578777);
    //LocalDateTime.from(dt.toInstant()).plusDays(1);
    //borrow.setEndDate(LocalDateTime.from(borrow.getStartDate().toInstant()).plusDays(7));

    @GetMapping("/borrow/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes ra){
        Long count = borrowRepository.countById(id);
        if (count != null || count !=0){
            Optional<Borrow> borrow = borrowRepository.findById(id);
            if (borrow.isPresent()){
                model.addAttribute("borrow", borrow);
                model.addAttribute("pageTitle", "Edit borrow : "+ borrow.get().getMember().getLastName()+" "+borrow.get().getCopy().getResource().getAuthor() );

                List<Member> members = memberRepository.findAll();
                List<Copy> copies = copyRepository.findAll();

                model.addAttribute("members", members);
                model.addAttribute("copies", copies);
                return "borrows/form";
            }
        }
        ra.addFlashAttribute("message", "The borrow hasn't been detected");
        return "redirect:/borrow/index";
    }
    @GetMapping("/borrow/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model, RedirectAttributes ra){
        Long count = borrowRepository.countById(id);
        if (count != null || count !=0){
            Borrow borrow = borrowRepository.getById(id);
            if (borrow != null){
                model.addAttribute("borrow", borrow);
                model.addAttribute("pageTitle", "Detail Borrow : "+ borrow.getMember().getLastName()+" "+borrow.getCopy().getResource().getAuthor() );
                return "borrows/detail";
            }
        }
        ra.addFlashAttribute("message", "The borrow hasn't been detected");
        return "redirect:/borrow/index";
    }

    @GetMapping("/borrow/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes ra ){
        Long count = borrowRepository.countById(id);
        if (count != null || count !=0){
            borrowRepository.deleteById(id);
            ra.addFlashAttribute("message", "The borrow has been deleted"+ id);
        }
        else if(count == null || count ==0){
            ra.addFlashAttribute("message", "The borrow not found"+ id);
        }
        return "redirect:/borrow/index";
    }
}
