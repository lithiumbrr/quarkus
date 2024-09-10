package br.com.quarkus.form.api;

import br.com.quarkus.form.entity.Event;
import br.com.quarkus.form.repository.EventRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/event")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EventResource {

    private static final Logger LOGGER = Logger.getLogger(EventResource.class.getName());

    @Inject
    EventRepository repository;

    @GET
    @Path("{id}")
    public Event getSingle(Long id) {
        Event event = repository.findById(id);
        if (event == null) {
            throw new WebApplicationException("Event with id of " + id + " does not exist.", 404);
        }
        return event;
    }
    @GET
    public List<Event> getAllEvents() {
        return repository.listAll();
    }

    @POST
    public void addEvent(Event event) {
        repository.persist(event);
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateInstitution(@PathParam("id") Long eventId) {
        Event event = repository.findById(eventId);
        if (event != null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Event updatedEvent = buildEvent(event);

        repository.persist(updatedEvent);
        return Response.ok(updatedEvent).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteInstitution(@PathParam("id") Long eventId) {
        Event event = repository.findById(eventId);
        if (event == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        repository.delete(event);
        return Response.noContent().build();
    }

    private Event buildEvent(Event event) {
        Event newEvent = new Event();
        event.setName(newEvent.getName());
        event.setInitialDate(event.getInitialDate());
        event.setFinalDate(event.getFinalDate());
        return newEvent;
    }
}
