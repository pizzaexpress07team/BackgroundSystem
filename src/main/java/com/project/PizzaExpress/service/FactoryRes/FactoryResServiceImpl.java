package com.project.PizzaExpress.service.FactoryRes;

import com.project.PizzaExpress.dao.ResourceDao;
import com.project.PizzaExpress.entity.FactoryResEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoryResServiceImpl implements IFactoryResService{

    @Autowired
    private ResourceDao resourceDao;

    @Override
    public List<FactoryResEntity> getAllFactoryRes() {
        return resourceDao.queryAll();
    }
}
