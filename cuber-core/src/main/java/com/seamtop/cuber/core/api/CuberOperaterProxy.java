package com.seamtop.cuber.core.api;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by feng on 2015/8/8.
 */
public class CuberOperaterProxy implements InvocationHandler{

    private Object target;

    public Object bind(Object target) {
        this.target = target;
        //取得代理对象
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), this);   //要绑定接口(这是一个缺陷，cglib弥补了这一缺陷)
    }

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        Object result=null;
        System.out.println("事物开始");
        System.out.println("args;"+args);
        for(int i=0;i<args.length;i++){
            System.out.println("args;"+args[i]);
        }
        //执行方法
        System.out.println("target:"+target);
        result=method.invoke(target, args);
        System.out.println("事物结束");
        return result;
    }
}
