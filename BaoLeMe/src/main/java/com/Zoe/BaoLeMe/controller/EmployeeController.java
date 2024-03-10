package com.Zoe.BaoLeMe.controller;

import com.Zoe.BaoLeMe.common.R;
import com.Zoe.BaoLeMe.entity.Employee;
import com.Zoe.BaoLeMe.service.EmployeeService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     * @param request
     * @param employee
     * @return
     **/
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee){

        //md5加密
        String password=employee.getPassword();
        password=DigestUtils.md5DigestAsHex(password.getBytes());

        //查询数据库是否有用户名 mybatisPlus的写法，将查询语句封装成对象进行调用
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp=employeeService.getOne(queryWrapper);

        //判断是否有该用户,若没有返回报错
        if(emp==null){
            return R.error("该用户不存在，登陆失败");
        }

        //判断密码
        if(!emp.getPassword().equals(password)){
            return R.error("密码错误，登陆失败");
        }

        //判读员工状态
        if(emp.getStatus()==0)
        {
            return R.error("该用户已被禁用");
        }

        //登陆成功，将员工id存入session并返回登录成功结果
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);
    }
}
