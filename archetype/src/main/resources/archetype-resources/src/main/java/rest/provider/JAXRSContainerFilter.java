#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.rest.provider;

import org.slf4j.Logger;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;


public class JAXRSContainerFilter {

    @Provider
    public static class CorsResponseFilter implements ContainerResponseFilter {
        @Override
        public void filter(ContainerRequestContext requestContext,
                           ContainerResponseContext responseContext) {
            responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        }
    }

    @Provider
    public static class RequestLogFilter implements ContainerRequestFilter {
        @Inject
        Logger LOG;

        @Override
        public void filter(ContainerRequestContext requestContext) {
            LOG.debug(requestContext.getMethod() + requestContext.getHeaders());
        }
    }
}
