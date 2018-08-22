#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import ${package}.service.api.ExampleService;
import ${package}.service.exception.ServiceException;
import ${package}.data.model.Example;
import ${package}.data.Repository;
import io.opentracing.contrib.cdi.Traced;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.enterprise.inject.Alternative;

@ApplicationScoped
@Traced
@Alternative
public class ExampleServiceMock implements ExampleService {
	@Inject
	Logger LOG;

	@Inject
	Repository repository;

	@Inject @ConfigProperty(name="service.map.data")
	private Example example;

	@Override
	public Example getExample(Long id) {
		Example e = repository.findById(id);
		if (e == null) {
			return example;
		} else {
			return e;
		}
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
