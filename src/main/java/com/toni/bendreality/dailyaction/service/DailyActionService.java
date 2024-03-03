package com.toni.bendreality.dailyaction.service;

import com.toni.bendreality.dailyaction.model.DailyAction;
import com.toni.bendreality.dailyaction.model.DailyActionDTO;
import com.toni.bendreality.dailyaction.mapper.DailyActionMapper;
import com.toni.bendreality.dailyaction.model.DailyActionViewDTO;
import com.toni.bendreality.dailyaction.repository.DailyActionRepository;
import com.toni.bendreality.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.toni.bendreality.security.ApplicationConfiguration.getCurrentUserId;

@Service
@AllArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class DailyActionService {

    private final DailyActionRepository dailyActionRepository;

    private final UserRepository userRepository;
    private final DailyActionMapper dailyActionMapper;

    @Transactional
    public DailyActionViewDTO addDailyAction(DailyActionDTO dailyActionDTO) {
        DailyAction toSave = dailyActionMapper.toEntity(dailyActionDTO);
        toSave.setUser(userRepository.getReferenceById(getCurrentUserId()));
        return dailyActionMapper.toViewDTO(dailyActionRepository.save(toSave));
    }

    public DailyActionViewDTO getDailyActionForUser() {
        Optional<DailyAction> toFind = dailyActionRepository.getDailyActionByUserId(getCurrentUserId());
        if (toFind.isPresent()) {
            DailyAction found = toFind.get();
            return dailyActionMapper.toViewDTO(found);
        } else {
            throw new EntityNotFoundException("Couldn't find the daily action");
        }
    }

    @Transactional
    public DailyActionViewDTO updateDailyAction(DailyActionDTO dailyActionDTO) {
        Optional<DailyAction> toFind = dailyActionRepository.findById(getCurrentUserId());
        if (toFind.isPresent()) {
            DailyAction found = toFind.get();
            found.setVisionObjOne(dailyActionDTO.visionObjOne());
            found.setVisionObjTwo(dailyActionDTO.visionObjTwo());
            found.setVisionObjThree(dailyActionDTO.visionObjThree());
            found.setMindsetObj(dailyActionDTO.mindsetObj());
            found.setBodyObj(dailyActionDTO.bodyObj());
            found.setSocialObj(dailyActionDTO.socialObj());
            found.setGratefulFor(dailyActionDTO.gratefulFor());
            return dailyActionMapper.toViewDTO(dailyActionRepository.save(found));
        } else {
            throw new EntityNotFoundException("Couldn't update the daily action");
        }
    }
}
