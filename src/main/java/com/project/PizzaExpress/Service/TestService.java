package com.project.PizzaExpress.Service;

import com.project.PizzaExpress.DAO.PizzaDAO;
import com.project.PizzaExpress.Entity.PizzaEntity;
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
