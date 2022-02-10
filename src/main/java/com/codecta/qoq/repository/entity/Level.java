package com.codecta.qoq.repository.entity;


import javax.persistence.*;

@Entity
@Table(name = "level")
public class Level extends ModelObject<Integer> {

    @SequenceGenerator(
            name = "level_generator",
            sequenceName = "level_seq",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "level_generator")
    @Id
    private Integer id;

    @Column(name = "seq_no")
    private Short seqNo;

    @Column(name = "weight_factor")
    private Integer weightFactor;

    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "map_id", referencedColumnName = "id")
    private Map map;

    @ManyToOne
    @JoinColumn(name="game_id")
    private Game game;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Short seqNo) {
        this.seqNo = seqNo;
    }

    public Integer getWeightFactor() {
        return weightFactor;
    }

    public void setWeightFactor(Integer weightFactor) {
        this.weightFactor = weightFactor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
