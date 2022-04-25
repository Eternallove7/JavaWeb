package com.study.fruit.dao;

import com.study.fruit.pojo.Fruit;

import java.util.List;

/**
 * @author RenAshbell
 * @create 2022-04-25-11:03
 */
public interface FruitDAO {
    // 获取所有的库存列表
    List<Fruit> getFruitList();
}
