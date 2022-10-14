package org.jboss.arquillian.spock.container;


import org.jboss.arquillian.container.test.spi.client.deployment.CachedAuxilliaryArchiveAppender;
import org.jboss.arquillian.spock.ArquillianSpock;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;

public class SpockDeploymentAppender extends CachedAuxilliaryArchiveAppender {
    @Override
    protected Archive<?> buildArchive() {
        return ShrinkWrap
            .create(JavaArchive.class, "arquillian-spock.jar")
            .addPackage(ArquillianSpock.class.getPackage().getName());
    }
}
