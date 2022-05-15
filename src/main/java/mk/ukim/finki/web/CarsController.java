package mk.ukim.finki.web;

import mk.ukim.finki.model.Cars;
import mk.ukim.finki.model.Category;
import mk.ukim.finki.service.CarsService;
import mk.ukim.finki.service.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping({"/", "/cars"})
public class CarsController {
    private final CarsService carsService;
    private final CategoryService categoryService;
//    private Facebook facebook;
//    private ConnectionRepository connectionRepository;
    public CarsController(CarsService carsService, CategoryService categoryService) {
        this.carsService = carsService;
        this.categoryService = categoryService;
//        this.facebook = facebook;
//        this.connectionRepository = connectionRepository;
    }

    @GetMapping
    public String showCars(String name, Model model){
        List<Cars> cars;
        List<Category> categories;

        cars = this.carsService.listAll();
        categories = this.categoryService.listCategories();
        model.addAttribute("cars", cars);
        model.addAttribute("category", categories);
        return "home.html";
    }
    @PostMapping("/delete/{id}")
    public String deleteCar(@PathVariable Long id) {
        this.carsService.deleteById(id);
        return "redirect:/cars";
    }
//    @GetMapping("/fb")
//    public String getAllFeeds(Model model){
//        if (connectionRepository.getPrimaryConnection(Facebook.class)==null){
//            return "redirect:/connect/facebook";
//        }
//        PagedList<Post> posts = facebook.feedOperations().getFeed();
//        model.addAttribute("posts", posts);
//        model.addAttribute("profileName", posts.get(0).getFrom().getName());
//        return "profile";
//    }
    @GetMapping("/edit-form/{id}")
    public String editCar(@PathVariable Long id, Model model) {
        if (this.carsService.findById(id).isPresent()) {
            Cars cars = this.carsService.findById(id).get();
            List<Category> categories = this.categoryService.listCategories();
            model.addAttribute("categories", categories);
            model.addAttribute("cars", cars);
            model.addAttribute("bodyContent", "addCar");
            return "addCar";
        }
        return "redirect:/cars?error=ProductNotFound";
    }
    @GetMapping("/addCar")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addCar(Model model) {
        List<Category> categories = this.categoryService.listCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "addCar");
        return "addCar.html";
    }

    @PostMapping("/add-Car")
    public String saveCar(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam Integer price,
            @RequestParam Long categoryId) {
        if (id != null) {
            this.carsService.edit(id, name, price, categoryId);
        } else {
            this.carsService.save(name, price, categoryId);
        }
        return "redirect:/cars";
    }
//    @GetMapping
//    public List<Cars> findAll() {
//        return carsService.findAll();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Cars> findById(@PathVariable Long id) {
//        return this.carsService.findById(id)
//                .map(cars -> ResponseEntity.ok().body(cars))
//                .orElseGet(() -> ResponseEntity.notFound().build());
//
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<Cars> save(@RequestBody CarsDto carsDto) {
//        return this.carsService.save(carsDto)
//                .map(cars -> ResponseEntity.ok().body(cars))
//                .orElseGet(() -> ResponseEntity.badRequest().build());
//    }
//
//    @PutMapping("/edit/{id}")
//    public ResponseEntity<Cars> save(@PathVariable Long id, @RequestBody CarsDto carsDto) {
//        return this.carsService.edit(id, carsDto)
//                .map(cars -> ResponseEntity.ok().body(cars))
//                .orElseGet(() -> ResponseEntity.badRequest().build());
//    }
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity deleteById(@PathVariable Long id) {
//        this.carsService.deleteById(id);
//        if(this.carsService.findById(id).isEmpty()) return ResponseEntity.ok().build();
//        return ResponseEntity.badRequest().build();
//    }
}
