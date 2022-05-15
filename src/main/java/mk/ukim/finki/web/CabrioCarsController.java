package mk.ukim.finki.web;

import mk.ukim.finki.model.Cars;
import mk.ukim.finki.model.Category;
import mk.ukim.finki.model.cars.CabrioCars;
import mk.ukim.finki.service.CabrioCarsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cabrio")
public class CabrioCarsController {
    private final CabrioCarsService cabrioCarsService;

    public CabrioCarsController(CabrioCarsService cabrioCarsService) {
        this.cabrioCarsService = cabrioCarsService;
    }

    @GetMapping
    public String getCabrioCars(Model model){
        List<CabrioCars> cabrioCars;
        cabrioCars =this.cabrioCarsService.getCabrioCars();
        model.addAttribute("cabrio", cabrioCars);
        return "Cabrio.html";
    }
    @PostMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        this.cabrioCarsService.deleteById(id);
        return "redirect:/cabrio";
    }
    @GetMapping("/edit-Cabrio/{id}")
    public String editCar(@PathVariable Long id, Model model) {
        if (this.cabrioCarsService.findById(id).isPresent()) {
            CabrioCars cabrioCars = this.cabrioCarsService.findById(id).get();
            model.addAttribute("cabrioCars", cabrioCars);
            model.addAttribute("bodyContent", "addCabrio");
            return "addCabrio.html";
        }
        return "redirect:/cabrioCars?error=ProductNotFound";
    }
    @GetMapping("/addCabrio")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCar(Model model) {
        model.addAttribute("bodyContent", "addCabrio");
        return "addCabrio.html";
    }

    @PostMapping("/add-Cabrio")
    public String saveCar(
            @RequestParam(required = false) Long id,
            @RequestParam String make,
            @RequestParam String model,
            @RequestParam String fuelType,
            @RequestParam Integer year,
            @RequestParam String color,
            @RequestParam Integer power,
            @RequestParam Integer price) {
        if (id != null) {
            this.cabrioCarsService.edit(id, make, model, fuelType, year, color, power, price);
        } else {
            this.cabrioCarsService.save(make, model, fuelType, year, color, power, price);
        }
        return "redirect:/cabrio";
    }

}
