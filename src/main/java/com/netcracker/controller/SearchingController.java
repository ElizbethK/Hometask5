package com.netcracker.controller;


import com.netcracker.model.Person;
import com.netcracker.util.CSVHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SearchingController {
    @GetMapping("/findperson")
    public String findperson(Model model) {
        model.addAttribute("person", new Person());
        return "findperson";
    }

    @PostMapping("/findperson")
    public String findPersonResult(@ModelAttribute Person person) {
       return (CSVHandler.findThisPerson(person)) != null ? "successfulsearch" : "unsuccessfulsearch";
    }

}
