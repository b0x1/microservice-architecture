#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.rest.api;

import ${package}.rest.model.ExampleDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/example")
@Api(value = "Endpoint")
public interface ExampleRest {

	@ApiOperation("Create an Example object")
	@POST
	@Path("/example")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
	@Consumes(MediaType.APPLICATION_JSON)
	ExampleDto createExample(@NotNull ExampleDto exampleDto);


	@ApiOperation("Get an example object")
	@GET
	@Path("/example/{id}")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
	@Consumes(MediaType.APPLICATION_JSON)
	ExampleDto getExample(@NotNull @PathParam("id") Long exampleId);

	@ApiOperation("Throw an exception")
	@ApiResponse(code=500, message="Intentional exception")
	@GET
	@Path("exception")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, })
	String getException();
}
