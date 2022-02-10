package com.codecta.qoq.services.impl;

import com.codecta.qoq.repository.MonsterRepository;
import com.codecta.qoq.repository.entity.Monster;
import com.codecta.qoq.services.model.MonsterDto;
import com.codecta.qoq.services.MonsterService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional
public class MonsterServiceImpl implements MonsterService {

    @Inject
    MonsterRepository monsterRepository;

    @Override
    public MonsterDto getMonsterById(Integer id) {
        Monster monster= monsterRepository.findById(id);
        if(monster == null){
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(monster,MonsterDto.class);
    }
}
