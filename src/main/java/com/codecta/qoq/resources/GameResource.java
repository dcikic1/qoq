package com.codecta.qoq.resources;

import com.codecta.qoq.services.GameService;
import com.codecta.qoq.services.model.CreateGameRequest;
import com.codecta.qoq.services.model.GameResponseDto;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/game")
public class GameResource {

    @Inject
    GameService gameService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public GameResponseDto create(CreateGameRequest request) {
        return gameService.create(request);
    }

    @POST
    @Path("/{id}/flee/from-dungeon/{dungeonId}")
    @Produces(MediaType.APPLICATION_JSON)
    public GameResponseDto flee(@PathParam("id") Integer id,@PathParam("dungeonId") Integer dungeonId) {return gameService.flee(id,dungeonId);}

    @POST
    @Path("/{id}/fight/from-dungeon/{dungeonId}")
    @Produces(MediaType.APPLICATION_JSON)
    public GameResponseDto fight(@PathParam("id") Integer id,@PathParam("dungeonId") Integer dungeonId) {return gameService.fight(id,dungeonId);}

}
