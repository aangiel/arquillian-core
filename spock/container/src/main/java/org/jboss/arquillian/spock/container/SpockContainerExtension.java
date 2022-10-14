package org.jboss.arquillian.spock.container;

import org.jboss.arquillian.container.test.spi.client.deployment.AuxiliaryArchiveAppender;
import org.jboss.arquillian.core.spi.LoadableExtension;

public class SpockContainerExtension implements LoadableExtension {

    @Override
    public void register(ExtensionBuilder builder) {
        builder.service(AuxiliaryArchiveAppender.class, SpockDeploymentAppender.class);
    }
}
