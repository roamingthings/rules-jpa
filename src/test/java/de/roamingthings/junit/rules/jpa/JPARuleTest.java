package de.roamingthings.junit.rules.jpa;

import org.junit.Rule;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class JPARuleTest {

    @Rule
    public EntityManagerProvider emProvider = EntityManagerProvider.providerForPersistenceUnit("it-rules-jpa");

    @Test
    public void should_create_entity_manager() {
        EntityManager em = emProvider.em();
        assertNotNull(em);
    }

    @Test
    public void should_create_transaction() {
        EntityTransaction tx = emProvider.tx();
        assertNotNull(tx);
    }

    @Test
    public void should_persist_entities_with_different_ids() {
        EntityManager em = emProvider.em();
        EntityTransaction tx = emProvider.tx();

        final JPARuleTestEntity testEntity = new JPARuleTestEntity("html5", "html5 for javaee developers");
        final JPARuleTestEntity testEntity2 = new JPARuleTestEntity("html5-2", "html5 for javaee developers");

        tx.begin();
        em.persist(testEntity);
        em.persist(testEntity2);
        tx.commit();

        assertNotNull(testEntity.getId());
        assertThat(testEntity.getId(), not(testEntity2.getId()));
    }
}
