package com.codecta.qoq.repository.entity;

import javax.persistence.*;

@Entity
@Table(name = "item_reference")
public class ItemReference extends ModelObject<Integer> {

    @SequenceGenerator(
            name = "item_reference_generator",
            sequenceName = "item_reference_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_reference_generator")
    @Id
    private Integer id;

    @Column(name = "strength_ind")
    private Boolean strengthInd;

    @Column(name = "strength_amount")
    private Integer strengthAmount;

    @Column(name = "damage_ind")
    private Boolean damageInd;

    @Column(name = "damage_amount")
    private Integer damageAmount;

    @Column(name = "healing_potion_ind")
    private Boolean healingPotionInd;

    @Column(name = "healing_amount")
    private Integer healingAmount;

    @Column(name = "orb_of_quarkus_ind")
    private Boolean orbOfQuarkusInd;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getStrengthInd() {
        return strengthInd;
    }

    public void setStrengthInd(Boolean strengthInd) {
        this.strengthInd = strengthInd;
    }

    public Integer getStrengthAmount() {
        return strengthAmount;
    }

    public void setStrengthAmount(Integer strengthAmount) {
        this.strengthAmount = strengthAmount;
    }

    public Boolean getDamageInd() {
        return damageInd;
    }

    public void setDamageInd(Boolean damageInd) {
        this.damageInd = damageInd;
    }

    public Integer getDamageAmount() {
        return damageAmount;
    }

    public void setDamageAmount(Integer damageAmount) {
        this.damageAmount = damageAmount;
    }

    public Boolean getHealingPotionInd() {
        return healingPotionInd;
    }

    public void setHealingPotionInd(Boolean healingPotionInd) {
        this.healingPotionInd = healingPotionInd;
    }

    public Integer getHealingAmount() {
        return healingAmount;
    }

    public void setHealingAmount(Integer healingAmount) {
        this.healingAmount = healingAmount;
    }

    public Boolean getOrbOfQuarkusInd() {
        return orbOfQuarkusInd;
    }

    public void setOrbOfQuarkusInd(Boolean orbOfQuarkusInd) {
        this.orbOfQuarkusInd = orbOfQuarkusInd;
    }
}
