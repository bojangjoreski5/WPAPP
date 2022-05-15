package mk.ukim.finki.web;

import mk.ukim.finki.model.cars.CabrioCars;
import mk.ukim.finki.model.cars.ClassicCars;
import mk.ukim.finki.service.ClassicCarsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/classic")
public class ClassicCarsController {
    private final ClassicCarsService classicCarsService;

    public ClassicCarsController(ClassicCarsService classicCarsService) {
        this.classicCarsService = classicCarsService;
    }

    @GetMapping
    public String getClassicCars(Model model){
        List<ClassicCars> classicCars;
        classicCars=this.classicCarsService.getClassicCars();
        model.addAttribute("classic", classicCars);
        return "Classic.html";
    }
    @PostMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        this.classicCarsService.deleteClassicCars(id);
        return "redirect:/classic";
    }
    @GetMapping("/edit-Classic/{id}")
    public String editCar(@PathVariable Long id, Model model) {
        if (this.classicCarsService.findById(id).isPresent()) {
            ClassicCars classicCars = this.classicCarsService.findById(id).get();
            model.addAttribute("classicCars", classicCars);
            model.addAttribute("bodyContent", "addClassic");
            return "addClassic.html";
        }
        return "redirect:/classicCars?error=ProductNotFound";
    }
    @GetMapping("/addClassic")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCar(Model model) {
        model.addAttribute("bodyContent", "addClassic");
        return "addClassic.html";
    }

    @PostMapping("/add-Classic")
    public String saveCar(
            @RequestParam(required = false) Long id,
            @RequestParam String make,
            @RequestParam String model,
            @RequestParam String fuelType,
            @RequestParam String color,
            @RequestParam Integer price) {
        if (id != null) {
            this.classicCarsService.edit(id, make, model, fuelType, color, price);
        } else {
            this.classicCarsService.save(make, model, fuelType, color, price);
        }
        return "redirect:/classic";
    }

}
