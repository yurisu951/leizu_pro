package com.chloe.leizu_pro.utils;

import com.chloe.leizu_pro.bean.Cart;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class CartPageUtils {

//    思路错误的失败惨物
    public static Cart setAttributes(JSONObject jsonObject, Cart cart){
        Set<String> keys = jsonObject.keySet();
        for (String key : keys){
            Class<? extends Cart> clazz = cart.getClass();
            Method[] methods = clazz.getMethods();
            for (Method method: methods) {
                if (method.getName().equals("set" +key.toUpperCase().charAt(0) + key.substring(1))){
                    Class<?> parameterType = (method.getParameterTypes())[0];
                    try{
                        if (parameterType.getName().contains("String")){
                            String value = (String) jsonObject.get(key);
                            method.invoke(cart, value);
                        }

                        if (parameterType.getName().contains("Integer")){
                            Integer value = (Integer) jsonObject.get(key);
                            method.invoke(cart, value);
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return cart;
    }
}
