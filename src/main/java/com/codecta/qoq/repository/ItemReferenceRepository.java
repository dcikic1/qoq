package com.codecta.qoq.repository;

import com.codecta.qoq.repository.entity.ItemReference;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;

@ApplicationScoped
@Transactional(Transactional.TxType.MANDATORY)
public class ItemReferenceRepository extends Repository<ItemReference, Integer> {

    public ItemReferenceRepository() {
        super(ItemReference.class);
    }

    public List<ItemReference> findReferenceItemsExceptOOQ() {
       return entityManager.createQuery("select i from ItemReference i where i.orbOfQuarkusInd <> true ", ItemReference.class).getResultList();
    }

    public  ItemReference findOrbOfQuarkusItemReference(){
        return  entityManager.createQuery("select i from ItemReference i where i.orbOfQuarkusInd = true ",ItemReference.class).getSingleResult();
    }

}
