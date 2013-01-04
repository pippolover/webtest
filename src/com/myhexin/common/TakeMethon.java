package com.myhexin.common;

import java.lang.reflect.Method;

import com.thoughtworks.selenium.SeleniumException;

public class TakeMethon
{
    private String selemsg = "错误原因不明";

    public TakeMethon(String Msg)
    {
        this.selemsg = Msg;
    }

    public TakeMethon()
    {

    }

    public void TakeMethonWrapper()
    {
        for (StackTraceElement st : Thread.currentThread().getStackTrace())
        {
            String ClassName = st.getClassName();
           
            
            try
            {
                Method[] m = Class.forName(ClassName).getDeclaredMethods();
                for (Method m1 : m)
                {
                   m1.setAccessible(true);
                   
                    if (m1.getName().equals(st.getMethodName()))
                    {
                        boolean flag=m1.isAnnotationPresent(UIMessage.class);
                        if (flag)
                        {

                            UIMessage msg = (UIMessage) (m1.getAnnotation(UIMessage.class));
                            String message = !msg.description().equals("") ? msg.description()
                                    : "没有函数描述";
                            String resultString = "函数作用:" + message
                                    + "\t\t\t元素定位错误:" + selemsg;
                            SeleniumException eee = new SeleniumException(
                                                                          resultString);
                            throw eee;
                        }
                       
                       
                    }
                    else
                    {
                        continue;
                    }
                }
            }

            catch (NullPointerException exc)
            {
                continue;
            }
            catch (ClassNotFoundException exc)
            {
                continue;
            }
            catch (SecurityException exc)
            {
                continue;
            }
            catch (NoClassDefFoundError e) {
                continue;
            }
        }
    }

    public void TakeMethonWrapper_assert()
    {
        int i = Thread.currentThread().getStackTrace().length;
        StackTraceElement m2 = Thread.currentThread().getStackTrace()[3];
        String classname = m2.getClassName();
        String methodname = m2.getMethodName();
        try
        {
            Method[] m = Class.forName(classname).getDeclaredMethods();
            for (Method m1 : m)
            {
                if (m1.getName().equals(methodname))
                {

                    if (m1.isAnnotationPresent(UIMessage.class))
                    {

                        UIMessage msg = (UIMessage) (m1.getAnnotation(UIMessage.class));
                        String message = !msg.description().equals("")? msg.description()
                                : "没有函数描述";
                        String resultString = "函数作用:" + message + "\t\t\t断言错误:"
                                + selemsg;
                        AssertionError eee = new AssertionError(resultString);
                        throw eee;
                    }
                    else
                    {

                        String resultString = "函数作用:" + "没有函数描述" + "\t\t\t断言错误:"
                                + selemsg;
                        AssertionError eee = new AssertionError(resultString);
                        throw eee;
                    }
                }
                else
                {
                    continue;
                }
            }
        }
        catch (Exception e)
        {
            // TODO: handle exception
        }
    }
}
