package com.project.PizzaExpress.service.FactoryRes;

import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.dao.ResourceDAO;
import com.project.PizzaExpress.entity.FactoryResEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoryResServiceImpl implements IFactoryResService{

    @Autowired
    private ResourceDAO resourceDao;

    //查
    @Override
    public List<FactoryResEntity> getAllFactoryRes() {
        return resourceDao.queryAll();
    }

    @Override
    public JSONObject getFactoryResByIdLike(String f_id){
        JSONObject result = new JSONObject();
        List<FactoryResEntity> query = resourceDao.queryLike("%" + f_id + "%");
        result.put("errorCode",0);
        result.put("SuccessQuery",query);
        result.put("total",query.size());
        return result;
    }

    @Override
    public JSONObject getFactoryResById(String f_id){
        JSONObject result = new JSONObject();
        List<FactoryResEntity> query = resourceDao.query(f_id);
        result.put("errorCode",0);
        result.put("SuccessQuery",query);
        result.put("total",query.size());
        return result;
    }

    @Override
    public JSONObject getFactoryResByNameLike(String r_name){
        JSONObject result = new JSONObject();
        List<FactoryResEntity> query = resourceDao.queryByNameLike("%" + r_name + "%");
        result.put("errorCode",0);
        result.put("SuccessQuery",query);
        result.put("total",query.size());
        return result;
    }

    @Override
    public JSONObject getFactoryResByName(String r_name){
        JSONObject result = new JSONObject();
        List<FactoryResEntity> query = resourceDao.queryByName(r_name);
        result.put("errorCode",0);
        result.put("SuccessQuery",query);
        result.put("total",query.size());
        return result;
    }

    //改
    public JSONObject addFactoryResNum(String f_id,String r_id,int num){
        JSONObject result = new JSONObject();
        resourceDao.addFactoryResNum(f_id,r_id,num);
        List<FactoryResEntity> query = resourceDao.queryByFidAndRid(f_id,r_id);
        result.put("errorCode",0);
        result.put("SuccessAddNum",query.get(0));
        return result;
    }
}
