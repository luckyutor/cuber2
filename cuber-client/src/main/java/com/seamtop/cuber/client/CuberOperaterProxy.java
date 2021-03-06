package com.seamtop.cuber.client;

import com.seamtop.cuber.common.conf.CuberConfiger;

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
        return Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        //判断是否启用cuber服务
        if(CuberConfiger.clientConfigProperties == null){
            CuberConfiger.loadConfiguration();
        }
        if("false".equals(CuberConfiger.clientConfigProperties.get("cuber.flag"))){
            return null;
        }
        //统一访问权限认证(暂略)
        //执行主体方法
        Object result=method.invoke(target, args);
        return result;
    }
}
