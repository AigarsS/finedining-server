package com.school.finediningserver.controller;

import com.school.finediningserver.dto.BasketDto;
import com.school.finediningserver.model.User;
import com.school.finediningserver.service.BasketService;
import com.school.finediningserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.text.ParseException;
import java.util.List;

@CrossOrigin("http://localhost:8081")
//@CrossOrigin("https://finedining.herokuapp.com")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/baskets")
public class BasketController {

    private final UserService userService;
    private final BasketService basketService;

    @GetMapping
    public List<BasketDto> getUserBaskets(Principal principal) {
// FIXME: Implement after Spring Security fix
//        User user = userService.getSessionUser(principal.getName());
        User user = userService.getUserByName("master");

        return basketService.getAllByUser(user);
    }

    @GetMapping(value = "/{id}")
    public BasketDto getBasket(@PathVariable Long id) {
        return basketService.get(id);
    }

//FIXME: returns basket with ingredients as null after creation
    @PostMapping
    public BasketDto createBasket(@RequestBody BasketDto basketDto) throws ParseException {
        return basketService.save(basketDto);
    }

    @DeleteMapping(value = "/{id}")
    public BasketDto deleteBasket(@PathVariable Long id){
        return basketService.delete(id);
    }


}
