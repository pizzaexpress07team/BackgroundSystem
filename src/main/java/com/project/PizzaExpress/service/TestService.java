package com.project.PizzaExpress.service;

import com.project.PizzaExpress.dao.TestDAO;
import com.project.PizzaExpress.entity.PizzaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private TestDAO testDAO;

    public void save(PizzaEntity pe)
    {

        testDAO.save(pe);
    }
}
