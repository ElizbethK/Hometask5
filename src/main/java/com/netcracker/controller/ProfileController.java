package com.netcracker.controller;

import com.netcracker.model.Person;
import com.netcracker.util.CSVHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;


@Controller
public class ProfileController {


    @GetMapping("/profile")
    public String profileForm(Model model){
        model.addAttribute("person", new Person());
        return "profile";
    }

    @PostMapping("/people")
    public String profileSubmit(@ModelAttribute Person person){
        String[] csv = { person.getSurname(), person.getName(), person.getPatronymic(),
                String.valueOf(person.getAge()), String.valueOf(person.getSalary()),
                person.getEmail(), person.getJob() };
        String CSV_PATH = "./csv.csv";
        File file = new File(CSV_PATH);
        boolean isAppendMode = file.exists();
        CSVHandler.acceptPerson(isAppendMode, csv);
        return "result";
    }

}
