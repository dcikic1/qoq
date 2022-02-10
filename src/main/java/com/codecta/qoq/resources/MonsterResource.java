package com.codecta.qoq.resources;

import com.codecta.qoq.services.model.MonsterDto;
import com.codecta.qoq.services.MonsterService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/monster")
public class MonsterResource {

    @Inject
    MonsterService monsterService;

    @GET
    @Path("/player/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public MonsterDto getMonsterById (Integer id){return monsterService.getMonsterById(id);}


}
