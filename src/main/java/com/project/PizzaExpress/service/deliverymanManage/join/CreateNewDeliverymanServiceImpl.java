package com.project.PizzaExpress.service.deliverymanManage.join;

import com.alibaba.fastjson.JSONObject;
import com.project.PizzaExpress.dao.DeliverymanDAO;
import com.project.PizzaExpress.dao.FactoryDAO;
import com.project.PizzaExpress.dao.UserDAO;
import com.project.PizzaExpress.entity.DeliverymanEntity;
import com.project.PizzaExpress.entity.FactoryEntity;
import com.project.PizzaExpress.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.UUID;

@Service
public class CreateNewDeliverymanServiceImpl implements ICreateNewDeliverymanService{

    @Autowired
    private DeliverymanDAO deliverymanDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private FactoryDAO factoryDAO;

    @Override
    public JSONObject joinDeliveryman(String uid, String f_id) {
        JSONObject result = new JSONObject();
        List<UserEntity> userList = userDAO.queryUserInfo(uid);
        if (userList == null || userList.size() == 0)
        {
            result.put("errorCode", 5);
            result.put("errorMsg", "No such user");
        }
        else if (userList.size() == 1)
        {
            List<FactoryEntity> factoryList = factoryDAO.query(f_id);
            if (factoryList == null || factoryList.size() == 0)
            {
                result.put("errorCode", 3);
                result.put("errorMsg", "No such factory");
            }
            else if (factoryList.size() == 1)
            {

                String name = userList.get(0).getUsername();//todo name != username
                String phone = userList.get(0).getPhone();
                if (name == null || name.equals("") || phone == null || phone.equals(""))
                {
                    result.put("errorCode", 2);
                    result.put("errorMsg", "Lack of necessary information");
                }
                else
                {
                    DeliverymanEntity de = new DeliverymanEntity();
                    de.setD_name(name);
                    de.setD_phone(phone);
                    de.setF_id(f_id);
                    de.setUid(uid);
                    String d_id = UUID.randomUUID().toString().replaceAll("-", "");
                    de.setD_id(d_id);
                    deliverymanDAO.insert(de);
                    List<DeliverymanEntity> results = deliverymanDAO.queryById(d_id);
                    if (results.size() == 1)
                    {
                        result.put("errorCode", 0);
                        result.put("d_id", d_id);
                    }
                    else
                    {
                        result.put("errorCode", 1);
                        result.put("errorMsg", "System Error : Insert Error");
                    }
                }
            }
            else
            {
                result.put("errorCode", 4);
                result.put("errorMsg", "Factory ID duplicated");
            }
        }
        else
        {
            result.put("errorCode", 6);
            result.put("errorMsg", "User ID duplicated");
        }
        return result;
    }

    @Override
    public JSONObject insertDeliveryman(String deliverInfo){
        JSONObject result = new JSONObject();
        if (deliverInfo == null || deliverInfo.equals(""))
        {
            result.put("errorCode", 3);
            result.put("errorMsg", "No deliveryman information");
        }else{
            DeliverymanEntity deliverymanEntity = DeliverymanEntity.fromJsonString(deliverInfo,false);
            if(deliverymanEntity == null){
                result.put("errorCode", 4);
                result.put("errorMsg", "Lack of necessary deliveryman information");
            } else if(deliverymanDAO.queryById(deliverymanEntity.getD_id()).size() != 0){
                result.put("errorCode", 1);
                result.put("errorMsg", "Deliveryman item exists");
            }else{
                deliverymanEntity.setState(0); //配送员的初始状态应设为0 表示还没接订单
                deliverymanDAO.insert(deliverymanEntity);
                List<DeliverymanEntity> query = deliverymanDAO.queryById(deliverymanEntity.getD_id());
                if (query.size() == 1)
                {
                    result.put("errorCode", 0);
                    result.put("SuccessInsert", deliverymanEntity);
                }
                else
                {
                    result.put("errorCode", 2);
                    result.put("errorMsg", "System Error : Insert Error");
                }
            }
        }
        return result;
    }
}
