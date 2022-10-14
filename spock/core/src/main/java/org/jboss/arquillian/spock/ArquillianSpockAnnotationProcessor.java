package org.jboss.arquillian.spock;

import org.spockframework.runtime.extension.IAnnotationDrivenExtension;
import org.spockframework.runtime.extension.IMethodInterceptor;
import org.spockframework.runtime.model.SpecInfo;


public class ArquillianSpockAnnotationProcessor implements IAnnotationDrivenExtension<ArquillianSpock> {

    @Override
    public void visitSpecAnnotation(ArquillianSpock annotation, SpecInfo spec) {
        IMethodInterceptor interceptor = new ArquillianSpockMethodInterceptor(spec);
        spec.addSetupSpecInterceptor(interceptor);
        spec.addCleanupSpecInterceptor(interceptor);
        spec.addInitializerInterceptor(interceptor);
    }
}
