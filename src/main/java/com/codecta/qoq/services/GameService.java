package com.codecta.qoq.services;

import com.codecta.qoq.services.model.CreateGameRequest;
import com.codecta.qoq.services.model.GameResponseDto;

import javax.inject.Named;

/**
 * Contains create game API.
 * @author dino_cikic
 */

public interface GameService {


     GameResponseDto create(CreateGameRequest createGameRequest);

     GameResponseDto flee(Integer id,Integer dungeonId);

     GameResponseDto fight(Integer id, Integer dungeonId);
}
