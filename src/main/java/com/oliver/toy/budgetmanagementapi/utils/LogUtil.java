package com.oliver.toy.budgetmanagementapi.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class LogUtil {
    /**
     * get Stack Trace
     * @param e
     * @return
     */
    public static String getStackTrace(Exception e){
        StringWriter errors = new StringWriter();
        e.printStackTrace(new PrintWriter(errors));
        return errors.toString();
    }
}
