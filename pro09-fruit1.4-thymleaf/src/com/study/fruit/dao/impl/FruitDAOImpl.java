package com.study.fruit.dao.impl;

import com.study.fruit.pojo.Fruit;
import com.study.fruit.dao.FruitDAO;
import com.study.myssm.basedao.BaseDAO;

import java.util.List;

/**
 * @author RenAshbell
 * @create 2022-04-25-11:03
 */
public class FruitDAOImpl extends BaseDAO<Fruit> implements FruitDAO {

    @Override
    public List<Fruit> getFruitList() {
        return super.executeQuery("select * from t_fruit");
    }
}
