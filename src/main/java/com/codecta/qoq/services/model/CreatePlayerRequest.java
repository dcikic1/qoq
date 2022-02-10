package com.codecta.qoq.services.model;


public class CreatePlayerRequest {
    private Integer health;

    private Integer damage;

    private Integer healingPoting;

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
}
