package com.codecta.qoq.repository.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "game")
public class Game extends ModelObject<Integer> {


    @SequenceGenerator(
            name = "game_generator",
            sequenceName = "game_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_generator")
    @Id
    private Integer id;

    private String status;

    private Integer score;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    private Player player;

    @OneToMany(mappedBy="game")
    private List<Level> levels;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Level> getLevels() {
        return levels;
    }
}
