package com.Zoe.BaoLeMe.service.impl;

import com.Zoe.BaoLeMe.entity.Employee;
import com.Zoe.BaoLeMe.mapper.EmployeeMapper;
import com.Zoe.BaoLeMe.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
