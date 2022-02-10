package com.codecta.qoq.services.impl;

import com.codecta.qoq.repository.*;
import com.codecta.qoq.repository.entity.*;
import com.codecta.qoq.services.GameService;
import com.codecta.qoq.services.model.CreateGameRequest;
import com.codecta.qoq.services.model.GameResponseDto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@ApplicationScoped
@Transactional
public class GameServiceImpl implements GameService {

    @Inject
    ItemReferenceRepository itemReferenceRepository;

    @Inject
    PlayerRepository playerRepository;

    @Inject
    MapRepository mapRepository;

    @Inject
    MonsterRepository monsterRepository;

    @Inject
    DungeonRepository dungeonRepository;

    @Inject
    ItemRepository itemRepository;

    @Inject
    LevelRepository levelRepository;

    @Inject
    GameRepository gameRepository;


    @Override
    public GameResponseDto create(CreateGameRequest request) {
        if (request == null)
            return null;

        List<ItemReference> itemReferenceList = itemReferenceRepository.findReferenceItemsExceptOOQ();
        List<Dungeon> dungeons = new ArrayList<>();

        Player newPlayer = new Player();
        newPlayer.setHealth(20);
        newPlayer.setNickname(request.getNickname());
        newPlayer.setDamage(3);
        newPlayer = playerRepository.save(newPlayer);

        Game newGame = new Game();
        newGame.setScore(0);
        newGame.setStatus("Active");
        newGame.setPlayer(newPlayer);
        newGame = gameRepository.save(newGame);

        Map newMap = new Map();
        newMap.setName("Map 1");
        newMap = mapRepository.save(newMap);


        int numberOfDungeons = generateRandomNumber(2, 4);

        for (int i = 0; i < numberOfDungeons; i++) {
            Item monsterItem = new Item();
            monsterItem.setItemReference(itemReferenceList.get(generateRandomNumber(0, itemReferenceList.size() - 1)));
            Monster newMonster = new Monster();
            newMonster.setName("Monster" + (i));
            newMonster.setHealth(i + generateRandomNumber(1, 3));
            newMonster.setDamage(i + 2);
            newMonster.setItem(monsterItem);
            newMonster = monsterRepository.save(newMonster);

            Dungeon newDungeon = new Dungeon();
            newDungeon.setName(generateDungeonName());
            newDungeon.setSeqNo((short) (i+1));
            newDungeon.setMap(newMap);
            newDungeon.setMonster(newMonster);
            newDungeon = dungeonRepository.save(newDungeon);
            dungeons.add(newDungeon);

            int numberOfItems = generateRandomNumber(1, 3);
            if (i == numberOfDungeons - 1) {
                Item newItem = new Item();
                newItem.setItemReference(itemReferenceRepository.findOrbOfQuarkusItemReference());
                newItem.setDungeon(newDungeon);
                itemRepository.save(newItem);
            } else {
                for (int j = 0; j < numberOfItems; j++) {
                    Item newItem = new Item();
                    newItem.setItemReference(itemReferenceList.get(generateRandomNumber(0, itemReferenceList.size() - 1)));
                    newItem.setDungeon(newDungeon);
                    itemRepository.save(newItem);
                }
            }

        }
        Level newLevel = new Level();
        newLevel.setMap(newMap);
        newLevel.setWeightFactor(1);
        newLevel.setStatus("Active");
        newLevel.setSeqNo((short) 1);
        newLevel.setGame(newGame);
        newLevel = levelRepository.save(newLevel);

        GameResponseDto gameResponseDto = createGameResponseDto(newGame, newPlayer, newLevel, dungeons.get(0), "Active");

        return gameResponseDto;
    }

    private GameResponseDto createGameResponseDto(Game newGame, Player newPlayer, Level newLevel, Dungeon dungeon, String status) {
        GameResponseDto gameResponseDto = new GameResponseDto();
        gameResponseDto.setPlayerId(newPlayer.getId());
        gameResponseDto.setPlayerHealth(newPlayer.getHealth());
        gameResponseDto.setPlayerNickname(newPlayer.getNickname());
        gameResponseDto.setDungeonName(dungeon.getName());
        gameResponseDto.setDungeonSeqNo(dungeon.getSeqNo());
        gameResponseDto.setDungeonId(dungeon.getId());
        gameResponseDto.setGameScore(newGame.getScore());
        gameResponseDto.setGameStatus(newGame.getStatus());
        gameResponseDto.setLevelSeqNo(newLevel.getSeqNo());
        gameResponseDto.setMapName(newLevel.getMap().getName());
        gameResponseDto.setMonsterHealth(dungeon.getMonster().getHealth());
        gameResponseDto.setMonsterName(dungeon.getMonster().getName());
        gameResponseDto.setGameId(newGame.getId());
        gameResponseDto.setGameStatus(status);
        return gameResponseDto;
    }
    @Override
    public GameResponseDto flee(Integer id, Integer dungeonId) {
        Game game = gameRepository.findById(id);
        Dungeon activeDungeon = dungeonRepository.findById(dungeonId);
        Player player = game.getPlayer();
        player.setHealth(player.getHealth() - activeDungeon.getMonster().getDamage());
        playerRepository.update(player);

        if (player.getHealth() <= 0) {
            setGameAsInactive(game);
            return createGameResponseDto(game, player, game.getLevels().get(0), activeDungeon, "Game Over");
        }

        List<Dungeon> dungeons = game.getLevels().get(0).getMap().getDungeons();
        Dungeon nextDungeon = getNextDungeon(activeDungeon, dungeons);

        if (nextDungeon == null) {
            setGameAsInactive(game);
            return createGameResponseDto(game, player, game.getLevels().get(0), activeDungeon, "Game Over");
        }

        if (isGameWon(game, player)) {
            setGameAsInactive(game);
            return createGameResponseDto(game, player, game.getLevels().get(0), nextDungeon, "Win");
        }
        return createGameResponseDto(game, player, game.getLevels().get(0), nextDungeon, "Active");

    }

    @Override
    public GameResponseDto fight(Integer id, Integer dungeonId) {
        Game game = gameRepository.findById(id);
        Dungeon activeDungeon = dungeonRepository.findById(dungeonId);
        Player player = game.getPlayer();
        Monster activeMonster = activeDungeon.getMonster();

        while(player.getHealth()>0 && activeMonster.getHealth()>0)
        {
            Integer playerHealth=player.getHealth();
            Integer monsterHealth=activeMonster.getHealth();
            Integer playerDamage=player.getDamage();
            Integer monsterDamage=activeMonster.getDamage();

            monsterHealth=monsterHealth-playerDamage*generateRandomNumber(1,5);
            playerHealth=playerHealth-monsterDamage*generateRandomNumber(1,5);

            player.setHealth(playerHealth);
            activeMonster.setHealth(monsterHealth);

        }
        List<Item>items=activeDungeon.getItems();
        for(Item item: items){
            if(item.getItemReference().getHealingPotionInd())
                player.setHealth(player.getHealth() + item.getItemReference().getHealingAmount());
            if(item.getItemReference().getDamageInd())
                player.setDamage(player.getDamage() + item.getItemReference().getDamageAmount());
        }
        playerRepository.update(player);

        if(activeMonster.getHealth()<0){
            List<Dungeon> dungeons = game.getLevels().get(0).getMap().getDungeons();
            Dungeon nextDungeon = getNextDungeon(activeDungeon, dungeons);

            return createGameResponseDto(game, player, game.getLevels().get(0), nextDungeon, "Active");

        }
        else {
            setGameAsInactive(game);
            return createGameResponseDto(game, player, game.getLevels().get(0), activeDungeon, "Game Over");

        }


    }

    private void setGameAsInactive(Game game) {
        game.setStatus("Inactive");
        gameRepository.update(game);
    }

    private boolean isGameWon(Game game, Player player) {
        return false;
    }

    private Dungeon getNextDungeon(Dungeon activeDungeon, List<Dungeon> dungeons) {
        for (Dungeon dungeon : dungeons) {
            if (dungeon.getSeqNo().equals((short)(activeDungeon.getSeqNo() + 1))) {
                return dungeon;
            }
        }
        return null;
    }


    private int generateRandomNumber(int low, int high) {
        Random r = new Random();
        return r.nextInt(high - low) + low;
    }

    private String generateDungeonName() {
        List<String> names = Arrays.asList("The Misty Vault",
                "The Yawning Catacombs",
                "The Obliterated Labyrinth",
                "The Eclipse Pits",
                "The Glowing Cells",
                "The Sad Pits");
        return names.get(generateRandomNumber(0, names.size() - 1));
    }

}


