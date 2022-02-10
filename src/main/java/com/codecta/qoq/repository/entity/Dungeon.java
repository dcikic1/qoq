package com.codecta.qoq.repository.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="dungeon")
public class Dungeon extends ModelObject<Integer> {

    @SequenceGenerator(
            name = "dungeon_generator",
            sequenceName = "dungeon_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dungeon_generator")
    @Id
    private Integer id;

    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "monster_id", referencedColumnName = "id")
    private Monster monster;

    @Column(name = "seq_no")
    private Short seqNo;

    @ManyToOne
    @JoinColumn(name="map_id")
    private Map map;

    @OneToMany(mappedBy="dungeon")
    private List<Item> items;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Short getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Short seqNo) {
        this.seqNo = seqNo;
    }
}
