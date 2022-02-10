package com.codecta.qoq.resources;


import com.codecta.qoq.services.PlayerService;
import com.codecta.qoq.services.model.CreatePlayerRequest;
import com.codecta.qoq.services.model.PlayerDto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/player")
public class PlayerResource {

    @Inject
    PlayerService playerService;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<PlayerDto> findAll() {
        return playerService.findAll();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PlayerDto getPlayerById(@PathParam("id") Integer id) {
        return playerService.getPlayerById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public PlayerDto create(CreatePlayerRequest request) {
        return playerService.create(request);
    }
}
