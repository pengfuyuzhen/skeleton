package controllers;

import api.CreateTagRequest;
import dao.TagDao;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//import java.util.List;

@Path("/tags")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TagController {
    final TagDao tags;

    public TagController(TagDao tags) {
        this.tags = tags;
    }

    @PUT
    @Path("/{tag}")
    public void toggleTag(@PathParam("tag") String tagLabel, @NotNull CreateTagRequest tag) {
        tags.insert(tagLabel, tag.receiptId);
    }
}
