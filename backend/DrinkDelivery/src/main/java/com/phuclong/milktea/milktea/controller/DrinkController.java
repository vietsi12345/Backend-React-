package com.phuclong.milktea.milktea.controller;

import com.phuclong.milktea.milktea.model.Drink;
import com.phuclong.milktea.milktea.model.Restaurant;
import com.phuclong.milktea.milktea.model.User;
import com.phuclong.milktea.milktea.request.CreateDrinkRequest;
import com.phuclong.milktea.milktea.service.DrinkService;
import com.phuclong.milktea.milktea.service.RestaurantService;
import com.phuclong.milktea.milktea.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drink")
public class DrinkController {
    @Autowired
    private DrinkService drinkService;
    @Autowired
    private UserService userService;
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity<List<Drink>> searchDrink(@RequestParam String name,
                                             @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Drink> drinks = drinkService.searchDrink(name);

        return new ResponseEntity<>(drinks, HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Drink>> getRestaurantDrink(@PathVariable Long restaurantId,
                                                          @RequestParam(required = false) boolean vagetarian,
                                                          @RequestParam(required = false) boolean nonveg,
                                                          @RequestParam(required = false) boolean seasonal,
                                                          @RequestParam(required = false) String drinkCategory,
                                                          @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        List<Drink> drinks = drinkService.
                getRestaurantsDrink(restaurantId,
                        vagetarian, nonveg,
                        seasonal, drinkCategory);

        return new ResponseEntity<>(drinks, HttpStatus.OK);
    }
}
