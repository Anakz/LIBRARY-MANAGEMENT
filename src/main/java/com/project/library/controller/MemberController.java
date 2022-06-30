package com.project.library.controller;

import com.project.library.dao.MemberRepository;
import com.project.library.model.Member;
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
public class MemberController {

    @Autowired
    private MemberRepository memberRepository;

    @GetMapping(path = "/member/index")
    public String index(Model model){
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "members/index";
    }

    @GetMapping("/member/create")
    public String create(Model model){
        model.addAttribute("member", new Member());
        model.addAttribute("pageTitle", "Add new Member");
        return "members/formRegister";
    }
    @PostMapping(path = "/member/register")
    public String register(Member member, RedirectAttributes ra){
        //Member memberFound = memberRepository.findByEmailAndCin(member.getEmail(), member.getCin());
        Long countEmail = memberRepository.countByEmail(member.getEmail());
        Long countCin = memberRepository.countByCin(member.getCin());
        if (countEmail < 1 && countCin < 1){
            memberRepository.save(member);
            ra.addFlashAttribute("message", "The member has been register successfully "+ countCin+" " + countEmail);
            return "redirect:/member/index";
        }
        else{
            ra.addFlashAttribute("message", "This account already exist"+ countCin+" " + countEmail);
            return "redirect:/member/create";
        }
    }
    @PostMapping("/member/save")
    public String save(Member member, RedirectAttributes ra){
        memberRepository.save(member);
        ra.addFlashAttribute("message", "The member has been updated successfully");
        return "redirect:/member/index";
    }

    @GetMapping("/member/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model, RedirectAttributes ra){
        Long count = memberRepository.countById(id);
        if (count != null || count !=0){
            //User user = userRepository.getUserById(id);
            Optional<Member> member = memberRepository.findById(id);
            if (member.isPresent()){
                model.addAttribute("member", member);
                model.addAttribute("pageTitle", "Edit member : "+ member.get().getFirstName()+" "+member.get().getLastName() );
                return "members/formEdit";
            }
        }
        ra.addFlashAttribute("message", "The member hasn't been detected");
        return "redirect:/member/index";
    }
    @GetMapping("/member/detail/{id}")
    public String detail(@PathVariable("id") Long id, Model model, RedirectAttributes ra){
        Long count = memberRepository.countById(id);
        if (count != null || count !=0){
            Member member = memberRepository.getById(id);
            if (member != null){
                model.addAttribute("member", member);
                model.addAttribute("pageTitle", "Detail User : "+ member.getFirstName()+" "+member.getLastName() );
                return "members/detail";
            }
        }
        ra.addFlashAttribute("message", "The user hasn't been detected");
        return "redirect:/member/index";
    }

    @GetMapping("/member/delete/{id}")
    public String delete(@PathVariable("id") Long id, RedirectAttributes ra ){
        Long count = memberRepository.countById(id);
        if (count != null || count !=0){
            memberRepository.deleteById(id);
            ra.addFlashAttribute("message", "The member has been deleted"+ id);
        }
        else if(count == null || count ==0){
            ra.addFlashAttribute("message", "The member not found"+ id);
        }
        return "redirect:/member/index";
    }
}
