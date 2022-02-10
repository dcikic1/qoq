package com.codecta.qoq.services.model;

public class GameResponseDto {

    private Integer gameId;
    private Integer playerId;
    private Integer playerHealth;
    private String playerNickname;
    private Integer gameScore;
    private String gameStatus;
    private String mapName;
    private String dungeonName;
    private Short dungeonSeqNo;
    private Integer dungeonId;
    private String monsterName;
    private Integer monsterHealth;
    private Short levelSeqNo;


    public Integer getGameId() {
        return gameId;
    }

    public void setGameId(Integer gameId) {
        this.gameId = gameId;
    }

    public Integer getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Integer playerId) {
        this.playerId = playerId;
    }

    public Integer getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(Integer playerHealth) {
        this.playerHealth = playerHealth;
    }

    public String getPlayerNickname() {
        return playerNickname;
    }

    public void setPlayerNickname(String playerNickname) {
        this.playerNickname = playerNickname;
    }

    public Integer getGameScore() {
        return gameScore;
    }

    public void setGameScore(Integer gameScore) {
        this.gameScore = gameScore;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getDungeonName() {
        return dungeonName;
    }

    public void setDungeonName(String dungeonName) {
        this.dungeonName = dungeonName;
    }

    public Short getDungeonSeqNo() {
        return dungeonSeqNo;
    }

    public void setDungeonSeqNo(Short dungeonSeqNo) {
        this.dungeonSeqNo = dungeonSeqNo;
    }

    public String getMonsterName() {
        return monsterName;
    }

    public void setMonsterName(String monsterName) {
        this.monsterName = monsterName;
    }

    public Integer getMonsterHealth() {
        return monsterHealth;
    }

    public void setMonsterHealth(Integer monsterHealth) {
        this.monsterHealth = monsterHealth;
    }

    public Short getLevelSeqNo() {
        return levelSeqNo;
    }

    public void setLevelSeqNo(Short levelSeqNo) {
        this.levelSeqNo = levelSeqNo;
    }

    public Integer getDungeonId() {
        return dungeonId;
    }

    public void setDungeonId(Integer dungeonId) {
        this.dungeonId = dungeonId;
    }
}
