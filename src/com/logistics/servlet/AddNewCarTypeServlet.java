package com.logistics.servlet;

import com.logistics.bean.CarType;
import com.logistics.dao.CarTypeDao;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/10/27.
 */
@WebServlet(name = "AddNewCarTypeServlet",urlPatterns = "/manageSite/addNewCarType.do")
public class AddNewCarTypeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取车型
        request.setCharacterEncoding("utf-8");
        String carType=request.getParameter("newCarType");
       // System.out.println(sectionName);
        //插入数据库

        CarTypeDao carTypeDao=new CarTypeDao();
        int val=carTypeDao.insertNewCarType(carType);
        CarType carType1=null;
        //调用dao层，获取新加的车型
        if(val>0){
            carType1=carTypeDao.getCarTypeByType(carType);
        }
        //将列表转成json串

        JSONObject jsonObject=new JSONObject(carType1);
        //JSONArray jsonArray=new JSONArray(sections);
        response.setContentType("text/plain;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        String result=jsonObject.toString();
        PrintWriter out=response.getWriter();
        //响应客户端

        out.print(result);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
