package com.codecta.qoq.services.impl;

import com.codecta.qoq.repository.PlayerRepository;
import com.codecta.qoq.repository.entity.Player;
import com.codecta.qoq.services.PlayerService;
import com.codecta.qoq.services.model.CreatePlayerRequest;
import com.codecta.qoq.services.model.PlayerDto;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Transactional
public class PlayerServiceImpl implements PlayerService {

    @Inject
    PlayerRepository playerRepository;

    @Override
    public List<PlayerDto> findAll() {
        List<Player> players = playerRepository.findAll();
        List<PlayerDto> playerDtos = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        for(Player player : players){
            playerDtos.add(modelMapper.map(player,PlayerDto.class));
        }
        return  playerDtos;
    }

    @Override
    public PlayerDto getPlayerById(Integer id) {

        Player player= playerRepository.findById(id);
        if(player == null){
            return null;
        }
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(player,PlayerDto.class);
    }

    @Override
    public PlayerDto create(  CreatePlayerRequest request) {
        if(request ==  null)
            return  null;
        Player newPlayer = new Player();
        newPlayer.setDamage(request.getDamage());
        newPlayer.setHealingPoting(request.getHealingPoting());
        newPlayer.setHealth(request.getHealth());
        newPlayer.setCreatedOn(LocalDateTime.now());
        newPlayer = playerRepository.save(newPlayer);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(newPlayer,PlayerDto.class);
    }

    @Override
    public PlayerDto decreaseHealth(Integer id, Integer healthDecrease) {

        Player player  = new Player();
        player = playerRepository.findById(id);
        player.setHealth(player.getHealth()-healthDecrease);
        player = playerRepository.save(player);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(player,PlayerDto.class);

    }
}
