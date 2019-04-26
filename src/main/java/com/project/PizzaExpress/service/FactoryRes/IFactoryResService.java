package com.project.PizzaExpress.service.FactoryRes;

import com.project.PizzaExpress.entity.FactoryResEntity;

import java.util.List;

public interface IFactoryResService {
    List<FactoryResEntity> getAllFactoryRes();

    List<FactoryResEntity> getFactoryRes(String f_id);
}
