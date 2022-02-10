package com.codecta.qoq.repository.entity;

import javax.persistence.*;

@Entity
@Table(name="item")
public class Item extends ModelObject<Integer> {

    @SequenceGenerator(
            name = "item_generator",
            sequenceName = "item_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_generator")
    @Id
    private Integer id;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "item_reference_id", referencedColumnName = "id")
    private ItemReference itemReference;

    @ManyToOne
    @JoinColumn(name="dungeon_id")
    private Dungeon dungeon;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ItemReference getItemReference() {
        return itemReference;
    }

    public void setItemReference(ItemReference itemReference) {
        this.itemReference = itemReference;
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }
}
