package tn.esprit.twin.ninja.beans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.twin.ninja.interfaces.MandateServicesLocal;

@Path("mandate")
@RequestScoped
public class MandateResource {
	@EJB(beanName = "MandateServices")
	MandateServicesLocal mandateService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllRessources(@QueryParam(value = "resourceId") String resid,
			@QueryParam(value = "dateM") String dateM) throws ParseException {

		String pattern = "yyyy-MM-dd";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		
	
	

		if ((resid == null) && (dateM == null)) {
		
			if (mandateService.getAll() == null)
				return Response.status(Response.Status.NOT_FOUND).build();

			if (mandateService.getAll().size() == 0)
				return Response.status(Response.Status.BAD_REQUEST).entity("Pas de contenu").build();

			else
				return Response.ok(mandateService.getAll(), MediaType.APPLICATION_JSON).build();

		} 
		else if ((resid != null) && (dateM == null)) {
			int resourceId = Integer.parseInt(resid);
			
			if (mandateService.getMandateByResource(resourceId) == null)
				return Response.status(Response.Status.NOT_FOUND).build();

			if (mandateService.getMandateByResource(resourceId).size() == 0)
				return Response.status(Response.Status.BAD_REQUEST).entity("Pas de contenu").build();

			else
				return Response.ok(mandateService.getMandateByResource(resourceId), MediaType.APPLICATION_JSON)
						.header("Access-Control-Allow-Origin", "*").build();

		} else if ((resid == null) && (dateM != null) ) 
		{
			Date FDate = simpleDateFormat.parse(dateM);
			
			if (mandateService.SearchMandateByDate(FDate) == null)
				return Response.status(Response.Status.NOT_FOUND).build();

			if (mandateService.SearchMandateByDate(FDate).size() == 0)
				return Response.status(Response.Status.BAD_REQUEST).entity("Pas de contenu").build();

			else
				return Response.ok(mandateService.SearchMandateByDate(FDate), MediaType.APPLICATION_JSON)
						.header("Access-Control-Allow-Origin", "*").build();

		}

		else
			return Response.status(Response.Status.BAD_REQUEST).entity("Requete eronnée").build();

	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("history")
	public Response getHistory() throws ParseException {

		if (!mandateService.DisplayHistory().isEmpty())
			return Response.ok(mandateService.DisplayHistory(), MediaType.APPLICATION_JSON).build();
		return Response.status(Status.NOT_FOUND).build();

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("assign/{projetId}/{resourceId}")
	public Response AssignResource(@PathParam(value = "projetId") int projetId,
			@PathParam(value = "resourceId") int resourceId) {
		mandateService.AssignResource(projetId, resourceId);
		return Response.status(Status.OK).build();

	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("delete/{mandateid}")
	public Response deleteMandate(@PathParam(value = "mandateid") int mandateid) {
		mandateService.ArchiveMandate(mandateid);
		return Response.status(Status.OK).build();

	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("archive")
	public Response getArchivedMandate() throws ParseException {

		if (!mandateService.ArchivedMandate().isEmpty())
			return Response.ok(mandateService.ArchivedMandate(), MediaType.APPLICATION_JSON).build();
		return Response.status(Status.NOT_FOUND).build();

	}

}