package com.example.finalproject2.controllers;

import com.example.finalproject2.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Autowired
    private BookingRepository bookingRepository;

    @RequestMapping("/home")
    public String showHomePage () {
        return "displaying the home page contents";
    }

    @RequestMapping("/admin")
    public String protectedPage () {
        return "displaying the protected page contents";
    }

//    @RequestMapping("/admin")
//    public String admin(Model model) {
//        model.addAttribute("booking", bookingRepository.findAll());
//        return "adminview";
//    }
//
//    @Autowired
//    BookingRepository bookingRepository;
//
//    @RequestMapping("/home")
//    public String showHomePage () {
//        return "displaying the home page contents";
//    }
//
//    @RequestMapping("/protected")
//    public String protectedPage (Model model) {
//        model.addAttribute("booking", bookingRepository.findAll());
//        return model.toString();
//    }
}