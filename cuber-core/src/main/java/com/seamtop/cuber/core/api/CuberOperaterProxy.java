package com.seamtop.cuber.core.api;

import com.seamtop.cuber.common.params.CalibrationConstants;
import com.seamtop.cuber.common.params.ParamsCalibration;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

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
        //执行主体方法
        Object result=method.invoke(target, args);
        return result;
    }
}
