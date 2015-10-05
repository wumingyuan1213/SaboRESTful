package com.sabo.tools;

import org.json.JSONObject;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by canbin.zhang on 2015/10/5.
 */
public final class SaboConfig {
    /**
     *  Static setting parameters
     */
    public static Map<String, String> config = new HashMap<>();

    public static void config(String fileName) throws Exception {
        FileReader fileReader;
        JSONObject jsonObject;
        char[] buf = new char[1024 * 1024];
        int count;

        fileReader = new FileReader(fileName);
        if ((count = fileReader.read(buf)) <= 0) {
            /**
             *  TODO
             */
            throw new Exception("Fail to get setting json file!");
        }
        jsonObject = new JSONObject(new String(buf));
        for (String key : jsonObject.keySet()) {
            config.put(key, (String) jsonObject.get(key));
        }
        fileReader.close();
    }
}
