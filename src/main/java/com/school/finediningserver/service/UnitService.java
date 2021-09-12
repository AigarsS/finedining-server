package com.school.finediningserver.service;

import com.school.finediningserver.dto.UnitDto;
import com.school.finediningserver.model.Unit;
import com.school.finediningserver.repositories.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UnitService {

    private final UnitRepository unitRepository;
    private final ModelMapper modelMapper;

    public List<UnitDto> getAll() {
        List<Unit> units = unitRepository.findAll();

        return units.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    public Unit getByDenotation(String denotation){
        return  unitRepository.findByDenotation(denotation);
    }

    private UnitDto convertToDto(Unit unit) {
        UnitDto unitDto = modelMapper.map(unit, UnitDto.class);

        return unitDto;
    }
}
