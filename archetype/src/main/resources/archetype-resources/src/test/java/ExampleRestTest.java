#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import ${package}.rest.api.ExampleRest;
import ${package}.rest.model.ExampleDto;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.resteasy.client.jaxrs.ProxyBuilder;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.exporter.ZipExporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.net.URL;

@RunWith(Arquillian.class)
public class ExampleRestTest {

    @ArquillianResource
    private URL deploymentUrl;

    private WebTarget webTarget;

    private ExampleRest exampleRest;
    private final String ENDPOINT = "/rest-api";

    @Before
    public void before() {
        webTarget = ClientBuilder.newClient().target(deploymentUrl + ENDPOINT);
        exampleRest = ProxyBuilder.builder(ExampleRest.class, webTarget)
                .defaultConsumes(MediaType.APPLICATION_JSON)
                .build();
    }

    @Test
    @RunAsClient
    public void testConfigMap() {
        // -- Given --
        ExampleDto result = exampleRest.getExample(1);

        // -- Then --
        Assert.assertNotNull(result);
        Assert.assertEquals("test-property", result.getSomeProperty());
    }


    @Test
    @RunAsClient
    public void testExceptionHandling() {
        // -- When --
        Response result = webTarget.path("/example/exception").request().get();

        // -- Then --
        Assert.assertEquals(500, result.getStatus());
    }

    @Deployment
    public static Archive createDeployment() {
        Archive archive = ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackages(true, "com.example")
                .addAsLibraries(
                        Maven.resolver()
                                .loadPomFromFile("pom.xml")
                                .importCompileAndRuntimeDependencies()
                                .resolve()
                                .withTransitivity()
                                .asFile())
                .addAsResource("META-INF/beans.xml")
                .addAsResource("META-INF/services/org.eclipse.microprofile.config.spi.Converter")
                .addAsResource("project-stages.yml");

        //If we want to see what has been packaged:
//        archive.as(ZipExporter.class).exportTo(new File(System.getenv("HOME") + "/" + archive.getName()), true);

        return archive;
    }
}
