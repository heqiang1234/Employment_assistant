package com.human.service;

import com.human.model.Employment;
import com.human.model.PageBean;

import java.util.List;

public interface EmploymentService {
    List<Employment> selectAllEmployment();

    int selectCount();

    int deleteByPrimaryKey(Integer id);

    PageBean<Employment> findByPage(int currentPage);
}
