#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.rest.impl;

import ${package}.rest.api.ExampleRest;
import ${package}.rest.model.ExampleDto;
import ${package}.service.api.ExampleService;
import ${package}.service.model.Example;
import org.dozer.Mapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

@ApplicationScoped
public class ExampleRestImpl implements ExampleRest {
	@Inject
	private Mapper mapper;

	@Inject
	private ExampleService exampleService;

	@Override
	public Boolean createExample(@NotNull ExampleDto exampleDto) {
		return exampleService.createExample(mapper.map(exampleDto, Example.class));
	}
}
