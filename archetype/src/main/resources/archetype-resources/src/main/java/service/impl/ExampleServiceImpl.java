#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.service.impl;

import ${package}.service.model.Example;
import ${package}.service.api.ExampleService;
import ${package}.service.exception.ServiceException;
import io.opentracing.contrib.cdi.Traced;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;


@ApplicationScoped
@Traced
public class ExampleServiceImpl implements ExampleService {
	@Inject
	Logger LOG;

	@Inject @ConfigProperty(name="service.map.data")
	private Example example;

	@Override
	public Example getExample(int id) {
		return example;
	}

	@Override
	public Example createExample(Example example) {
		return null;
	}

	@Override
	public String getException() {
		LOG.warn("Exception is intentional.");

		throw new ServiceException("You are exceptional.");
	}
}
