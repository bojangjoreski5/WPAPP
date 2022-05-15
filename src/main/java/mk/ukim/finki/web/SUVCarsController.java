package mk.ukim.finki.web;

import mk.ukim.finki.model.cars.OldtimerCars;
import mk.ukim.finki.model.cars.SUVCars;
import mk.ukim.finki.model.cars.SportCars;
import mk.ukim.finki.service.SUVCarsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/suv")
public class SUVCarsController {

    private final SUVCarsService suvCarsService;

    public SUVCarsController(SUVCarsService suvCarsService) {
        this.suvCarsService = suvCarsService;
    }

    @GetMapping
    public String getSUVCars(Model model){
        List<SUVCars> suvCars;
        suvCars=this.suvCarsService.getSUVCars();
        model.addAttribute("suv", suvCars);
        return "SUV.html";
    }
    @PostMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        this.suvCarsService.deleteSUVCars(id);
        return "redirect:/suv";
    }
    @GetMapping("/edit-Suv/{id}")
    public String editCar(@PathVariable Long id, Model model) {
        if (this.suvCarsService.findById(id).isPresent()) {
            SUVCars suvCars = this.suvCarsService.findById(id).get();
            model.addAttribute("suvCars", suvCars);
            model.addAttribute("bodyContent", "addSuv");
            return "addSuv.html";
        }
        return "redirect:/suvCars?error=ProductNotFound";
    }
    @GetMapping("/addSuv")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCar(Model model) {
        model.addAttribute("bodyContent", "addSuv");
        return "addSuv.html";
    }

    @PostMapping("/add-Suv")
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
            this.suvCarsService.edit(id, make, model, fuelType, year, color, power, price);
        } else {
            this.suvCarsService.save(make, model, fuelType, year, color, power, price);
        }
        return "redirect:/suv";
    }
}
