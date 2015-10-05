package com.sabo.tools;

import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by canbin.zhang on 2015/10/5.
 */
public final class SaboServicesReg {
    public static Map<String, Method> services =  new HashMap<>();

    public static void reg(String fileName) throws Exception {
        FileReader servicesJsonFile;
        char[] buf = new char[1024 * 1024]; /* I think this file is less than 1M!  :) */
        JSONObject jsonObject;
        int count;

        servicesJsonFile = new FileReader(fileName);
        count = servicesJsonFile.read(buf);
        if (count <= 0 || count > 1024 * 1024) {
            //TODO use log4j to deal with the log!
            throw new Exception("Fail to register services!");
        }
        jsonObject = new JSONObject(new String(buf));
        for (String key : jsonObject.keySet()) {
            Method method;

            method = Class.forName((String) jsonObject.get(key)).getDeclaredMethod("go", HttpServletRequest.class,
                    HttpServletResponse.class, String[].class);
            services.put(key, method);
        }
        servicesJsonFile.close();
    }
}
