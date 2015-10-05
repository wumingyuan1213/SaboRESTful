package com.test;

import com.sabo.SaboTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by canbin.zhang on 2015/10/5.
 */
public class Hello extends SaboTemplate {
    public static void go(HttpServletRequest req, HttpServletResponse resp, String[] params) {
        try {
            PrintWriter out = resp.getWriter();

            out.println("<h1>Hello world!<h1/>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
