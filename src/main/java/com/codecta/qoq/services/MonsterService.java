package com.codecta.qoq.services;

import com.codecta.qoq.services.model.MonsterDto;

/**
 * Containing methods describing monster's actions
 * @author cikic_dino
 *
 */


public interface MonsterService {
    MonsterDto getMonsterById(Integer id);
}
