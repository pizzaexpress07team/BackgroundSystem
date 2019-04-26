package com.project.PizzaExpress.service.FactoryRes;

import com.project.PizzaExpress.dao.ResourceDAO;
import com.project.PizzaExpress.entity.FactoryResEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoryResServiceImpl implements IFactoryResService{

    @Autowired
    private ResourceDAO resourceDao;

    @Override
    public List<FactoryResEntity> getAllFactoryRes() {
        return resourceDao.queryAll();
    }

    @Override
    public List<FactoryResEntity> getFactoryRes(String f_id){
        List<FactoryResEntity> query = resourceDao.queryLike("%" + f_id + "%");
        return query;
    }
}
