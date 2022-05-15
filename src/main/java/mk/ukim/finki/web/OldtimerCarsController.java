package mk.ukim.finki.web;

import mk.ukim.finki.model.cars.ClassicCars;
import mk.ukim.finki.model.cars.OldtimerCars;
import mk.ukim.finki.service.OldtimerCarsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/oldtimer")
public class OldtimerCarsController {
    private final OldtimerCarsService oldtimerCarsService;

    public OldtimerCarsController(OldtimerCarsService oldtimerCarsService) {
        this.oldtimerCarsService = oldtimerCarsService;
    }

    @GetMapping
    public String getOldtimerCars(Model model){
        List<OldtimerCars> oldtimerCars;
        oldtimerCars=this.oldtimerCarsService.getOldtimerCars();
        model.addAttribute("oldtimer", oldtimerCars);
        return "Oldtimer.html";
    }
    @PostMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        this.oldtimerCarsService.deleteOldtimerCars(id);
        return "redirect:/oldtimer";
    }
    @GetMapping("/edit-Oldtimer/{id}")
    public String editCar(@PathVariable Long id, Model model) {
        if (this.oldtimerCarsService.findById(id).isPresent()) {
            OldtimerCars oldtimerCars = this.oldtimerCarsService.findById(id).get();
            model.addAttribute("oldtimerCars", oldtimerCars);
            model.addAttribute("bodyContent", "addClassic");
            return "addOldtimer.html";
        }
        return "redirect:/oldtimerCars?error=ProductNotFound";
    }
    @GetMapping("/addOldtimer")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCar(Model model) {
        model.addAttribute("bodyContent", "addOldtimer");
        return "addOldtimer.html";
    }

    @PostMapping("/add-Oldtimer")
    public String saveCar(
            @RequestParam(required = false) Long id,
            @RequestParam String make,
            @RequestParam String model,
            @RequestParam String fuelType,
            @RequestParam Integer year,
            @RequestParam String color,
            @RequestParam Integer price) {
        if (id != null) {
            this.oldtimerCarsService.edit(id, make, model, fuelType, year, color, price);
        } else {
            this.oldtimerCarsService.save(make, model, fuelType, year, color, price);
        }
        return "redirect:/oldtimer";
    }
}
