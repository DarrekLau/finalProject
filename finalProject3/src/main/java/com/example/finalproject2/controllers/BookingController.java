package com.example.finalproject2.controllers;

import com.example.finalproject2.models.Booking;
import com.example.finalproject2.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;

    @GetMapping("/booking")
    public ModelAndView addBooking(){

        ModelAndView bookingMav = new ModelAndView("booking");
        Booking newBooking = new Booking();
        bookingMav.addObject("booking", newBooking);
        return bookingMav;
    }

    @PostMapping("/saveBooking")
    public String saveBooking(@ModelAttribute Booking booking) {
        bookingRepository.save(booking);
        return "test"; // to be amended to homepage.etc after booking is submitted
    }

    @GetMapping({"/test"})
    public ModelAndView viewAllBooking() {
        ModelAndView viewAllBookingsMav = new ModelAndView("test");
        viewAllBookingsMav.addObject("booking", bookingRepository.findAll());
        return viewAllBookingsMav;
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("booking", bookingRepository.findAll());
        return "adminview";
    }



}
