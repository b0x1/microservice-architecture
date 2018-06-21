#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.rest.provider;

import ${package}.service.exception.ServiceException;
import ${package}.rest.model.ErrorModel;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.log.Fields;
import io.opentracing.tag.Tags;
import org.jboss.resteasy.spi.DefaultOptionsMethodException;
import org.slf4j.Logger;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.rmi.ServerException;
import java.util.HashMap;

/**
 * This class holds the JAX-RS providers for exception mapping.
 */
public class JAXRSExceptionMappers {


    private static final String PATTERN_ERROR_MSG = "Handle response for exception '%s'[custom-msg: %s]";

    @Provider
    public static class OptionsHander implements ExceptionMapper<DefaultOptionsMethodException> {

        @Override
        public Response toResponse(DefaultOptionsMethodException exception) {
            final Response.ResponseBuilder response = Response.ok();
            response.header("Access-Control-Allow-Methods", "GET, PUT, POST, DELETE");
            response.header("Access-Control-Allow-Headers", "content-type");
            return response.build();

        }
    }

    @Provider
    public static class IncognitoExceptionExceptionMapper implements ExceptionMapper<ServiceException> {
        @Inject
        private Logger log;
        @Inject
        private Instance<Scope> scopeInstance;

        @Override
        public Response toResponse(ServiceException exception) {
            log.error(String.format(PATTERN_ERROR_MSG, exception.getClass().getName(), exception.getMessage()), exception);

            // append error on current open span
            final Span span = scopeInstance.get().span();
            JAXRSExceptionMappers.reportErrorOnSpan(span, exception);

            return Response.status(500).entity(new ErrorModel(exception)).build();
        }
    }

    private static void reportErrorOnSpan(final Span span,
                                          final Throwable t) {
        span.setTag(Tags.ERROR.getKey(), true);
        span.log(new HashMap<String, Object>() {{
            StringWriter sw = new StringWriter();
            t.printStackTrace(new PrintWriter(sw));

            put(Fields.EVENT, "error");
            put(Fields.ERROR_KIND, "Exception");
            put(Fields.ERROR_OBJECT, t);
            put(Fields.MESSAGE, t.getMessage());
            put(Fields.STACK, sw.toString());
        }});
    }
}
