package com.codecta.qoq.repository.entity;


import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "PLAYER")
public class Player extends ModelObject<Integer> {

    @SequenceGenerator(
            name = "player_generator",
            sequenceName = "player_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "player_generator")
    @Id
    private Integer id;

    @Length(max = 60)
    private String nickname;

    private String character;

    @NotNull
    private Integer health;

    private Integer damage;

    @Column(name = "healing_poting")
    private Integer healingPoting;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public Integer getDamage() {
        return damage;
    }

    public void setDamage(Integer damage) {
        this.damage = damage;
    }

    public Integer getHealingPoting() {
        return healingPoting;
    }

    public void setHealingPoting(Integer healingPoting) {
        this.healingPoting = healingPoting;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }
}
