package com_java_demo.service;

import com_java_demo.dao.AdminMapper;
import com_java_demo.entity.Admin;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("AdminService")

public class AdminService {
    @Resource
    protected AdminMapper adminMapper;


    public Admin getAdmin(int id){
        Admin a=this.adminMapper.selectByPrimaryKey(id);
        if(a!=null){
            return a;
        }
        return null;
    }

    public int insertAdmin(Admin admin){
        return this.adminMapper.insert(admin);
    }


}
