package br.com.quarkus.form.api;

import br.com.quarkus.form.entity.Institution;
import br.com.quarkus.form.repository.InstitutionRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/institution")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class InstitutionResource {

    private static final Logger LOGGER = Logger.getLogger(InstitutionResource.class.getName());

    @Inject
    InstitutionRepository repository;

    @GET
    @Path("{id}")
    public Institution getSingle(Long id) {
        Institution institution = repository.findById(id);
        if (institution == null) {
            throw new WebApplicationException("Institution with id of " + id + " does not exist.", 404);
        }
        return institution;
    }

    @GET
    public List<Institution> getAllInstitutions() {
        return repository.listAll();
    }

    @POST
    public void addInstitution(Institution institution) {
        repository.persist(institution);
    }

    @PUT
    @Path("/{id}")
    public Response updateInstitution(@PathParam("id") Long institutionId, Institution updatedInstitution) {
        Institution institution = repository.findById(institutionId);
        if (institution != null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        updatedInstitution = buildInstitution(institution);

        repository.persist(updatedInstitution);
        return Response.ok(updatedInstitution).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteInstitution(@PathParam("id") Long institutionId) {
        Institution institution = repository.findById(institutionId);
        if (institution == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        repository.delete(institution);
        return Response.noContent().build();
    }

    private Institution buildInstitution(Institution institution) {
        Institution newInstitution = new Institution();
        institution.setName(institution.getName());
        institution.setType(institution.getType());
        return newInstitution;
    }
}
