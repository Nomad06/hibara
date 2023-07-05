package com.bubalex.hibara;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.massindexing.MassIndexer;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.transaction.annotation.Transactional;

/**
 * Requires for indexing.
 */
@Slf4j
@Transactional
public class LuceneIndexServiceBean {

    private final EntityManager entityManager;

    public LuceneIndexServiceBean(EntityManagerFactory entityManagerFactory) {
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    /**
     * Starts indexing.
     */
    public void triggerIndexing() {
        try {
            SearchSession searchSession = Search.session(entityManager);
            MassIndexer indexer =
                    searchSession
                            .massIndexer()
                            .limitIndexedObjectsTo(15000)
                            .idFetchSize(15000)
                            .batchSizeToLoadObjects(2500)
                            .threadsToLoadObjects(Runtime.getRuntime().availableProcessors());

            indexer.startAndWait();
        } catch (InterruptedException e) {
           // log.error("Thread interrupted during full text indexing process", e);
            Thread.currentThread().interrupt();
        }
    }

}
