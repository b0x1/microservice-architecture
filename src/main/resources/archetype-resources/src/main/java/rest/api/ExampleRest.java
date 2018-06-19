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
	@Produces({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
	@Consumes(MediaType.APPLICATION_JSON)
	Boolean createExample(@NotNull ExampleDto exampleDto);
}
