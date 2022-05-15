package mk.ukim.finki.web;

import mk.ukim.finki.model.BookNow;
import mk.ukim.finki.model.User;
import mk.ukim.finki.service.BookNowService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/book-now")
public class BookNowController {
    private final BookNowService bookNowService;

    public BookNowController(BookNowService bookNowService) {
        this.bookNowService = bookNowService;
    }

    @GetMapping
    public String getBookNowPage(@RequestParam(required = false) String error,
                                      HttpServletRequest req,
                                      Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String username = req.getRemoteUser();
        BookNow bookNow = this.bookNowService.getActiveBookNow(username);
        model.addAttribute("booked", this.bookNowService.listAllBookedCars(bookNow.getId()));
        model.addAttribute("bodyContent", "bookNow");
        return "book-now.html";
    }
    @PostMapping("/add-cars/{id}")
    public String addCarToBookNow(@PathVariable Long id, HttpServletRequest req, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            this.bookNowService.bookNowCar(user.getUsername(), id);
            return "redirect:/book-now";
        } catch (RuntimeException exception) {
            return "redirect:/book-now?error=" + exception.getMessage();
        }
    }
    @PostMapping("/delete-booked/{id}")
    public String deleteCar(@PathVariable Long id) {
        this.bookNowService.deleteById(id);
        return "redirect:/cars";
    }
}
