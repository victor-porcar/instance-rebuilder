package com.github.victormpcmun.ir.instancerebuilder;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.framework.ProxyFactory;

import java.lang.reflect.Method;
import java.util.function.Supplier;

public class InstanceRebuilder<T> {

    private Supplier<T> instanceSupplier;
    private T instance;
    private boolean rebuildRequestInFuture;
    private long rebuildAfterInstant;

    public static InstanceRebuilder  build() {
        InstanceRebuilder instanceRebuilder = new InstanceRebuilder();
        return instanceRebuilder;
    }

    public static <T> InstanceRebuilder build(Supplier<T> instanceSupplier) {
        InstanceRebuilder instanceRebuilder = new InstanceRebuilder();
        instanceRebuilder.setInstanceSupplier(instanceSupplier);
        return instanceRebuilder;
    }

    public void setInstanceSupplier(Supplier<T> instanceSupplier) {
        this.instanceSupplier = instanceSupplier;
    }


    public T getInstanceProxy() {
        if (instance ==null) {
            instance = buildInstanceFromSupplier();
        }
        ProxyFactory factory = new ProxyFactory(instance);
        factory.setExposeProxy(true);
        factory.addAdvice((MethodInterceptor) invocation -> {

            Method method = invocation.getMethod();
            Object[] arguments = invocation.getArguments();

            synchronized (this) {

                if (rebuildRequestInFuture && (now() > rebuildAfterInstant)) {
                    rebuildRequestInFuture = false;
                    instance = buildInstanceFromSupplier();
                }
            }

            try {
                return method.invoke(instance, arguments);
            } catch (Exception e) {
                return null;
            }
        });

        T proxy = (T) factory.getProxy();
        return proxy;
    }

    public boolean rebuildInstanceInMilliseconds(long milliseconds) {
        if (!rebuildRequestInFuture) {
            rebuildRequestInFuture =true;
            rebuildAfterInstant = now() + milliseconds;
            return true;
        }
        return false;

    }

    public void rebuildInstance() {
        synchronized(this) {
            rebuildRequestInFuture =false;
            instance = buildInstanceFromSupplier();
        }
    }

    public T getActualInstance() {
        return instance;
    }

    //--------------------

    private T buildInstanceFromSupplier() {
        return instanceSupplier.get();
    }

    private long now() {
        return System.currentTimeMillis();
    }

}
