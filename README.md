# `junit-rules` - Common JUnit Rules

This project is derived from the `rulez` project published by Adam Bien at [github](https://github.com/AdamBien/rulz)

##JPA EntityManager provider

###Installation

```xml
    <dependency>
        <groupId>de.roamingthings.junit-rules</groupId>
        <artifactId>rules-jpa</artifactId>
        <version>[RECENT_VERSION]</version>
        <scope>test</scope>
    </dependency>
```
###Sample use

```java
import de.roamingthings.junit.rules.jpa.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import static org.junit.Assert.assertNotNull;
import org.junit.Rule;
import org.junit.Test;


public class WorkshopTest {

    @Rule
    public EntityManagerProvider emProvider = EntityManagerProvider.persistenceUnit("it");

    @Test
    public void crud() {
        EntityManager em = emProvider.em();
        assertNotNull(em);
        EntityTransaction tx = emProvider.tx();
        assertNotNull(tx);
        tx.begin();
        em.merge(new Workshop("html5", "html5 for javaee developers"));
        tx.commit();
    }
}
```
