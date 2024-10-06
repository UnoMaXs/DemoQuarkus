package com.example.resource;

import com.example.model.Persona;
import com.example.service.PersonaServiceAPI;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.*;

@ApplicationScoped
@Path("/persona/api")
public class personaResource {

    @Inject
    PersonaServiceAPI personaServiceAPI;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@QueryParam("id") Integer id){
        if (id == null){
            return Response.ok(personaServiceAPI.getAll()).status(Response.Status.OK).build();
        }
        return Response.ok(personaServiceAPI.get(id)).status(Response.Status.OK).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(Persona persona) {
        return Response.ok(personaServiceAPI.save(persona)).status(Response.Status.CREATED).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(Persona p){
        return Response.ok(personaServiceAPI.update(p)).status(Response.Status.OK).build();
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Integer id){
        personaServiceAPI.delete(id);
        return Response.ok().status(Response.Status.NO_CONTENT).build();
    }

}
