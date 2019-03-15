package com.project.PizzaExpress.service;

import com.project.PizzaExpress.dao.PizzaDAO;
import com.project.PizzaExpress.entity.PizzaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @Autowired
    private PizzaDAO pizzaDAO;

    public void save(PizzaEntity pe)
    {
        pizzaDAO.save(pe);
    }
}
