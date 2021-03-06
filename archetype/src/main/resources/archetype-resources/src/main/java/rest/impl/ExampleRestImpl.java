#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.rest.impl;

import ${package}.rest.api.ExampleRest;
import ${package}.rest.model.ExampleDto;
import ${package}.service.api.ExampleService;
import ${package}.data.model.Example;
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
	public ExampleDto getExample(Long id) {
		return mapper.map(exampleService.getExample(id), ExampleDto.class);
	}

	@Override
	public ExampleDto createExample(@NotNull ExampleDto exampleDto) {
		return mapper.map(exampleService.createExample(mapper.map(exampleDto, Example.class)),
						  ExampleDto.class);
	}

	@Override
	public String getException() {
		return exampleService.getException();
	}

}
