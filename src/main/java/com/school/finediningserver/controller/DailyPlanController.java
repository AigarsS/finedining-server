package com.school.finediningserver.controller;

import com.school.finediningserver.dto.DailyPlanDto;
import com.school.finediningserver.model.User;
import com.school.finediningserver.service.DailyPlanService;
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
@RequestMapping("/api/daily_plans")
public class DailyPlanController {

    private final DailyPlanService dailyPlanService;
    private  final UserService userService;

    @GetMapping
    public List<DailyPlanDto> getAllByUser(Principal principal) {
// FIXME: Implement after Spring Security fix
//        User user = userService.getSessionUser(principal.getName());
        User user = userService.getUserByName("master");

        return dailyPlanService.getAllByUser(user);
    }

    @GetMapping(value = "/{id}")
    public DailyPlanDto getDailyPlan(@PathVariable Long id) {
        return dailyPlanService.get(id);
    }

    @PostMapping
    public DailyPlanDto createDailyPlan(@RequestBody DailyPlanDto dailyPlanDto) throws ParseException {
        return dailyPlanService.save(dailyPlanDto);
    }

    @DeleteMapping(value = "/{id}")
    public DailyPlanDto deleteDailyPlan(@PathVariable Long id){
        return  dailyPlanService.delete(id);
    }

}
