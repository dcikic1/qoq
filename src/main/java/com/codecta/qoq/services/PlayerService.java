package com.codecta.qoq.services;

import com.codecta.qoq.services.model.CreatePlayerRequest;
import com.codecta.qoq.services.model.PlayerDto;

import java.util.List;

/**
 * Contains methods for player manipulation. Methods defined:
 * <ul>
 * <li> {@link #findAll}</li>
 * <li> {@link #getPlayerById}</li>
 * <li> {@link #create}</li>
 * </ul>
 *
 * @author cikic_dino
 */
public interface PlayerService {

    /**
     * Retrieves all players from database
     *
     * @return {@link List} of {@link PlayerDto}
     */
    List<PlayerDto> findAll();

    /**
     * Retrieves plyayer by id from database
     *
     * @param id player id
     * @return {@link PlayerDto}
     */
    PlayerDto getPlayerById(Integer id);

    /**
     * Creates new player and persists it into database
     *
     * @param request {@link CreatePlayerRequest}
     * @return {@link PlayerDto}
     */
    PlayerDto create(CreatePlayerRequest request);

    PlayerDto decreaseHealth(Integer id, Integer healthDecrease);
}
