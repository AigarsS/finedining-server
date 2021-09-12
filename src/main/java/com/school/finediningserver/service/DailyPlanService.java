package com.school.finediningserver.service;

import com.school.finediningserver.dto.DailyPlanDto;
import com.school.finediningserver.model.Basket;
import com.school.finediningserver.model.DailyPlan;
import com.school.finediningserver.model.User;
import com.school.finediningserver.repositories.DailyPlanRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DailyPlanService {

    private final DailyPlanRepository dailyPlanRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public List<DailyPlanDto> getAllByUser(User user) {
        List<DailyPlan> dailyPlans = dailyPlanRepository.findByUser(user);

        return dailyPlans.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public DailyPlanDto get(Long id) {
        Optional<DailyPlan> dailyPlanOptional = dailyPlanRepository.findById(id);
        return convertToDto(dailyPlanOptional.get());
    }

    public List<DailyPlan> getByBasket(Basket basket) {
        return dailyPlanRepository.findByTargetDateBetween(basket.getStartDate(), basket.getEndDate());
    }

    public DailyPlanDto save(DailyPlanDto dailyPlanDto) throws ParseException {
        DailyPlan dailyPlan = convertToEntity(dailyPlanDto);
        DailyPlan dailyPlanCreated = dailyPlanRepository.save(dailyPlan);

        return convertToDto(dailyPlanCreated);
    }

    public DailyPlanDto delete(Long id) {
        Optional<DailyPlan> dailyPlanOptional = dailyPlanRepository.findById(id);
        dailyPlanRepository.delete(dailyPlanOptional.get());

        return convertToDto(dailyPlanOptional.get());
    }

    private DailyPlanDto convertToDto(DailyPlan dailyPlan) {
        DailyPlanDto dailyPlanDto = modelMapper.map(dailyPlan, DailyPlanDto.class);

        return dailyPlanDto;
    }

    private DailyPlan convertToEntity(DailyPlanDto dailyPlanDto) throws ParseException {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        DailyPlan dailyPlan = modelMapper.map(dailyPlanDto, DailyPlan.class);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getUserByName(authentication.getName());

        Date date = new SimpleDateFormat("dd-MM-yyyy").parse(dailyPlanDto.getTargetDate());
        dailyPlan.setTargetDate(date);
        dailyPlan.setUser(user);

        return dailyPlan;
    }
}
