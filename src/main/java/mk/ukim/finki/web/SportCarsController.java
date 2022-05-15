package mk.ukim.finki.web;

import mk.ukim.finki.model.cars.OldtimerCars;
import mk.ukim.finki.model.cars.SportCars;
import mk.ukim.finki.service.SportCarsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/sport")
public class SportCarsController {
    private final SportCarsService sportCarsService;

    public SportCarsController(SportCarsService sportCarsService) {
        this.sportCarsService = sportCarsService;
    }


    @GetMapping
    public String getSportCars(Model model){
        List<SportCars> sportCars;
        sportCars=this.sportCarsService.getSportCars();
        model.addAttribute("sport", sportCars);
        return "Sport.html";
    }
    @PostMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        this.sportCarsService.deleteSportCars(id);
        return "redirect:/sport";
    }
    @GetMapping("/edit-Sport/{id}")
    public String editCar(@PathVariable Long id, Model model) {
        if (this.sportCarsService.findById(id).isPresent()) {
            SportCars sportCars = this.sportCarsService.findById(id).get();
            model.addAttribute("sportCars", sportCars);
            model.addAttribute("bodyContent", "addSport");
            return "addSport.html";
        }
        return "redirect:/sportCars?error=ProductNotFound";
    }
    @GetMapping("/addSport")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCar(Model model) {
        model.addAttribute("bodyContent", "addSport");
        return "addSport.html";
    }

    @PostMapping("/add-Sport")
    public String saveCar(
            @RequestParam(required = false) Long id,
            @RequestParam String make,
            @RequestParam String model,
            @RequestParam String fuelType,
            @RequestParam String color,
            @RequestParam Integer power,
            @RequestParam Integer price) {
        if (id != null) {
            this.sportCarsService.edit(id, make, model, fuelType, color, power, price);
        } else {
            this.sportCarsService.save(make, model, fuelType, color, power, price);
        }
        return "redirect:/sport";
    }
}
