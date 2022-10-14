package org.jboss.arquillian.spock;

import org.jboss.arquillian.test.spi.LifecycleMethodExecutor;
import org.jboss.arquillian.test.spi.TestRunnerAdaptor;
import org.jboss.arquillian.test.spi.TestRunnerAdaptorBuilder;
import org.spockframework.runtime.extension.AbstractMethodInterceptor;
import org.spockframework.runtime.extension.IMethodInvocation;
import org.spockframework.runtime.model.SpecInfo;

import java.lang.reflect.Method;

public final class ArquillianSpockMethodInterceptor extends AbstractMethodInterceptor {

    private final TestRunnerAdaptor adaptor = TestRunnerAdaptorBuilder.build();
    private final Class<?> spec;

    public ArquillianSpockMethodInterceptor(SpecInfo spec) {
        this.spec = retrieveSpecClass(spec);
    }

    @Override
    public void interceptInitializerMethod(IMethodInvocation invocation) throws Throwable {

        Method method = invocation
            .getMethod()
            .getFeature()
            .getFeatureMethod()
            .getReflection();

        adaptor.before(
            invocation.getInstance(),
            method,
            LifecycleMethodExecutor.NO_OP);

        invocation.proceed();
    }

    @Override
    public void interceptSetupSpecMethod(IMethodInvocation invocation) throws Throwable {
        adaptor.beforeSuite();
        adaptor.beforeClass(spec, LifecycleMethodExecutor.NO_OP);

        invocation.proceed();
    }

    @Override
    public void interceptCleanupSpecMethod(IMethodInvocation invocation) throws Throwable {
        adaptor.afterClass(spec, LifecycleMethodExecutor.NO_OP);
        adaptor.afterSuite();

        invocation.proceed();
    }

    private Class<?> retrieveSpecClass(SpecInfo spec) {
        try {
            return Class.forName(String.format("%s.%s", spec.getPackage(), spec.getName()));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
