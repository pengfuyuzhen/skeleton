package controllers;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.servlet.http.HttpSession;
import io.dropwizard.jersey.sessions.Session;

// For a Java class to be eligible to receive ANY requests
// it must be annotated with at least @Path
@Path("")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldController {

    // You can specify additional @Path steps; they will be relative
    // to the @Path defined at the class level
    @GET
    @Path("/hello")
    public String helloWorld(@Session HttpSession session) {
        return "Hello World " + session.toString();
    }

    @GET
    @Path("/netid")
    @Produces(MediaType.TEXT_PLAIN)
    public String getNetId() {
        return "fp73";
    }
}
