package com.codecta.qoq.repository;

import com.codecta.qoq.repository.entity.Game;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class GameRepository extends Repository<Game,Integer> {

    public GameRepository() {
        super(Game.class);
    }
}
