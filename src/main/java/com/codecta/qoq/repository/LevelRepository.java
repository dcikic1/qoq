package com.codecta.qoq.repository;

import com.codecta.qoq.repository.entity.Level;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class LevelRepository extends Repository<Level,Integer> {

    public LevelRepository() {
        super(Level.class);
    }
}
