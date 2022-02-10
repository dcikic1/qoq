package com.codecta.qoq.repository.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Map")
public class Map extends ModelObject<Integer> {

    @SequenceGenerator(
            name = "map_generator",
            sequenceName = "map_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "map_generator")
    @Id
    private Integer id;

    private String name;

    @OneToMany(mappedBy="map")
    private List<Dungeon> dungeons;

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

    public List<Dungeon> getDungeons() {
        return dungeons;
    }

    public void setDungeons(List<Dungeon> dungeons) {
        this.dungeons = dungeons;
    }
}
