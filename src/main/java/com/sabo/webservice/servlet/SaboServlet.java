package com.sabo.webservice.servlet;

import com.sabo.price.SaboPrice;
import com.sabo.tools.SaboConfig;
import com.sabo.tools.SaboServicesReg;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by canbin.zhang on 2015/10/5.
 */
public class SaboServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        /**
         *  Parse SaboServices.json && SaboConfig.json
         */
        //TODO
        String appPath;

        appPath = config.getServletContext().getRealPath("") + File.separator + "WEB-INF" + File.separator;
        try {
            SaboServicesReg.reg(appPath + "SaboServices.json");
            SaboConfig.config(appPath + "SaboConfig.json");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] params = null;

        assert SaboServicesReg.services != null;
        params = req.getPathInfo().trim().split("/");
        try {
            if (!SaboPrice.weight(params)) {
                ((Method)SaboServicesReg.services.get(params[1])).invoke(null, req, resp, params);
            } else {// If the price of the operation is too high then make it async.
                /**
                 *  TODO
                  */
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
        SaboServicesReg.services = null;
        SaboConfig.config = null;
    }
}
