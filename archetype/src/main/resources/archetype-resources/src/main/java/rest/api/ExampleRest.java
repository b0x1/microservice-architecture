#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.rest.api;

import ${package}.rest.model.ExampleDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/endpoint")
@Api(value = "Endpoint")
public interface ExampleRest {

	@ApiOperation("Create an Example object")
	@POST
	@Path("create")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN})
	@Consumes(MediaType.APPLICATION_JSON)
	String createExample(@NotNull ExampleDto exampleDto);

	@ApiOperation("Throw an exception")
	@GET
	@Path("exception")
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN, })
	String getException();
}
