package com.leslie.bookingForm.controller;

import com.leslie.bookingForm.model.Booking;
import com.leslie.bookingForm.repository.BookingRepository;
import com.leslie.bookingForm.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookingController {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private BookingService bookingService;

    @GetMapping("/booking")
    public ModelAndView addBooking(){

        ModelAndView bookingMav = new ModelAndView("booking");
        Booking newBooking = new Booking();
        bookingMav.addObject("booking", newBooking);
        return bookingMav;
    }

    @PostMapping("/saveBooking")
    public String saveBooking(Booking booking) {
        bookingService.addBooking(booking);
        return "test"; // to be amended to homepage.etc after booking is submitted
    }

//    @GetMapping("/test")
//    public ModelAndView viewAllBooking() {
//        ModelAndView viewAllBookingsMav = new ModelAndView("test");
//        viewAllBookingsMav.addObject("booking", bookingRepository.findAll());
//        return viewAllBookingsMav;
//    }

//    @GetMapping("/admin")
//    public String admin(Model model) {
//        model.addAttribute("booking", bookingRepository.findAll());
//        return "adminview";
//    }

    @GetMapping("/admin")
    public String adminPage(Model model, String keyword){
        List<Booking> list;
        if(keyword != null){
            list = bookingService.getBookingByKeyword(keyword);

        }
        else{
            list = bookingService.getAllBookings(model);
        }
        model.addAttribute("booking", list);
        return "adminview";
    }

    @GetMapping("admin/update")
    public ModelAndView showUpdateForm(@RequestParam Long id){
        ModelAndView bookingMav = new ModelAndView("updatebooking");
        Booking booking = bookingRepository.findById(id).get();
        bookingMav.addObject("booking", booking);
        return bookingMav;
    }

    @PostMapping("/admin/saveupdate")
    public String saveUpdate(Booking booking) {
        bookingService.addBooking(booking);
        return "redirect:/admin";
    }

    @GetMapping("admin/delete")
    public String deleteBooking(@RequestParam Long id){
        bookingService.deleteBooking(id);
        return "redirect:/admin";
    }




}
