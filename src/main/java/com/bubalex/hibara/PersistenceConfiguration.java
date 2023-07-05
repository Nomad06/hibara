package com.bubalex.hibara;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The Flyway configuration.
 */
@Configuration
public class PersistenceConfiguration {

    /**
     * Starts full text indexing
     *
     * @param entityManagerFactory entity manager factory
     * @return instance of the class for full text indexing run
     */
    @Bean
    public LuceneIndexServiceBean luceneIndexServiceBean(EntityManagerFactory entityManagerFactory) {
        LuceneIndexServiceBean luceneIndexServiceBean = new LuceneIndexServiceBean(entityManagerFactory);
        luceneIndexServiceBean.triggerIndexing();

        return luceneIndexServiceBean;
    }
}
