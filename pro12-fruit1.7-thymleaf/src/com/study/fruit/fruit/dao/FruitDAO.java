package com.study.fruit.fruit.dao;


import com.study.fruit.fruit.pojo.Fruit;

import java.util.List;

/**
 * @author RenAshbell
 * @create 2022-04-25-11:03
 */
public interface FruitDAO {
    // 获取指定页码上的库存列表 , 每页显示5条
    List<Fruit> getFruitList(String keyword,Integer pageNo);

    // 根据fid获取特定的水果库存信息
    Fruit getFruitByFid(Integer fid);

    // 修改指定的库存记录
    void updateFruit(Fruit fruit);

    // 根据fid删除指定的库存记录
    void delFruit(Integer fid);

    // 添加库存记录
    void addFruit(Fruit fruit);

    // 查询库存总记录条数
    int getFruitCount(String keyword);
}
