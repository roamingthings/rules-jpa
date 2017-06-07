/**
 * Copyright 2016 Alexander Sparkowsky
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.roamingthings.junit.rules.jpa;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EntityManagerProvider implements TestRule {

    private EntityManager em;
    private EntityTransaction tx;

    private EntityManagerProvider(String unitName) {
        this.em = Persistence.createEntityManagerFactory(unitName).createEntityManager();
        this.tx = this.em.getTransaction();
    }

    public final static EntityManagerProvider providerForPersistenceUnit(String unitName) {
        return new EntityManagerProvider(unitName);
    }

    public EntityManager em() {
        return this.em;
    }

    public EntityTransaction tx() {
        return this.tx;
    }

    public EntityManager begin() {
        this.tx.begin();
        return em;
    }

    public void commit() {
        this.tx.commit();
    }

    public void rollback() {
        this.tx.rollback();
    }

    public void clear() {
        this.em.clear();
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {

            @Override
            public void evaluate() throws Throwable {
                base.evaluate();
                clear();
            }

        };
    }
}
