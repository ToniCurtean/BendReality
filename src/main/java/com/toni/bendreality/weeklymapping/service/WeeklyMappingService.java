package com.toni.bendreality.weeklymapping.service;

import com.toni.bendreality.dailyaction.model.DailyAction;
import com.toni.bendreality.dailyaction.model.DailyActionDTO;
import com.toni.bendreality.dailyaction.model.DailyActionViewDTO;
import com.toni.bendreality.user.repository.UserRepository;
import com.toni.bendreality.weeklymapping.mapper.WeeklyMappingMapper;
import com.toni.bendreality.weeklymapping.model.WeeklyMapping;
import com.toni.bendreality.weeklymapping.model.WeeklyMappingDTO;
import com.toni.bendreality.weeklymapping.model.WeeklyMappingViewDTO;
import com.toni.bendreality.weeklymapping.repository.WeeklyMappingRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.toni.bendreality.security.ApplicationConfiguration.getCurrentUserId;

@Service
@Slf4j
@AllArgsConstructor
@Transactional(readOnly = true)
public class WeeklyMappingService {

    private final WeeklyMappingMapper weeklyMappingMapper;
    private final WeeklyMappingRepository weeklyMappingRepository;
    private final UserRepository userRepository;


    @Transactional
    public WeeklyMappingViewDTO addWeeklyMapping(WeeklyMappingDTO weeklyMappingDTO) {
        WeeklyMapping toSave = weeklyMappingMapper.toEntity(weeklyMappingDTO);
        toSave.setUser(userRepository.getReferenceById(getCurrentUserId()));
        return weeklyMappingMapper.toViewDTO(weeklyMappingRepository.save(toSave));

    }

    public WeeklyMappingViewDTO getWeeklyMappingForUser() {
        Optional<WeeklyMapping> toFind = weeklyMappingRepository.getWeeklyMappingByUserId(getCurrentUserId());
        if (toFind.isPresent()) {
            WeeklyMapping found = toFind.get();
            return weeklyMappingMapper.toViewDTO(found);
        } else {
            throw new EntityNotFoundException("Couldn't find the weekly mapping");
        }
    }

    @Transactional
    public WeeklyMappingViewDTO updateWeeklyMapping(WeeklyMappingDTO weeklyMappingDTO) {
        Optional<WeeklyMapping> toFind = weeklyMappingRepository.findById(getCurrentUserId());
        if (toFind.isPresent()) {
            WeeklyMapping found = toFind.get();
            found.setVisionObjOne(weeklyMappingDTO.visionObjOne());
            found.setVisionObjTwo(weeklyMappingDTO.visionObjTwo());
            found.setVisionObjThree(weeklyMappingDTO.visionObjThree());
            found.setMindsetObj(weeklyMappingDTO.mindsetObj());
            found.setBodyObj(weeklyMappingDTO.bodyObj());
            found.setSocialObj(weeklyMappingDTO.socialObj());
            return weeklyMappingMapper.toViewDTO(weeklyMappingRepository.save(found));
        } else {
            throw new EntityNotFoundException("Couldn't update the weekly mapping");
        }
    }


}
