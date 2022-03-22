package com.nowcoder.community.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * 处理浏览器的请求
 */
@Controller
@RequestMapping("/alpha")
public class AlphaController {

    @RequestMapping("/hello")
    @ResponseBody
    public String sayHello(){
        return "lsj Spring Boot!";
    }

    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response){
        //获取请求数据
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> enumeration = request.getHeaderNames();
        while (enumeration.hasMoreElements()){
            String name = enumeration.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ":" + value);
        }
        System.out.println(request.getParameter("code"));

        //返回响应数据
        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer =null;
        try {
             writer = response.getWriter();
            writer.write("<h1>牛客网<h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            writer.close();
        }
    }

    //GET请求
    // /students?current=1&limit=20
    @RequestMapping(path = "/students", method = RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "10")int limit){
        System.out.println(current);
        System.out.println(limit);
        return "some students";
    }
    @RequestMapping(path = "/student/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable int id){
        System.out.println(id);
        return "a student";
    }

    //POST 请求 浏览器向服务器提交数据的时候（表单提交需要网页）
    //POST获取参数，参数的名字和表单属性中name的名字一致就会传过来。
    @RequestMapping(path = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age){
        System.out.println(name);
        System.out.println(age);
        return "success";
    }

    /**
     * 重点理解下面一个model？？？
     * @return
     */
    //上面都是请求，下面是响应
    //响应HTML数据(不加注解，默认返回html）
    @RequestMapping(path = "/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "老樊");
        mav.addObject("age",30);
        mav.setViewName("/demo/view"); // 默认加html
        return mav;

    }

    /**
     * 这里不理解！
     * @param model
     * @return
     */
    //上面是把model和view装到一个对象里
    //下面是把model数据装到参数里，把View视图直接返回给servlet
    @RequestMapping(path = "/school", method = RequestMethod.GET)
    public String getSchool(Model model){
        model.addAttribute("name","上海大学");
        model.addAttribute("age",80);
        return "/demo/view"; //意思返回视图？

    }

    //响应异步JSON（异步请求）
    //JAVA面向对象，万物皆对象，浏览器解析对象（JS对象）。
    //JAVA对象-> JSON字符串->JS对象

    @RequestMapping(path = "/emp", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> getEmp(){
        Map<String, Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",23);
        emp.put("salary",80000.00);
        return emp;
    }

    @RequestMapping(path = "/emps", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String,Object>> getEmps(){
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String, Object> emp = new HashMap<>();
        emp.put("name","张三");
        emp.put("age",23);
        emp.put("salary",80000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","王涛");
        emp.put("age",24);
        emp.put("salary",90000.00);
        list.add(emp);

        emp = new HashMap<>();
        emp.put("name","lsj");
        emp.put("age",24);
        emp.put("salary",3000000.00);
        list.add(emp);


        return list;
    }



}
