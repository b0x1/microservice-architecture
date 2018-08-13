#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.data;

import ${package}.data.model.Example;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class Repository {

    @PersistenceContext
    private EntityManager em;

    public Example findById(Long id) {
        return em.find(Example.class, id);
    }
}