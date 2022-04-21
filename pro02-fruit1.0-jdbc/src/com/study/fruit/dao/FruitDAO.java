package com.study.fruit.dao;

import com.study.fruit.pojo.Fruit;

import java.util.List;

/**
 * @author RenAshbell
 * @create 2022-04-21-16:51
 */
public interface FruitDAO {
    // 查询库存列表
    List<Fruit> getFruitList();

    // 新增库存
    boolean addFruit(Fruit fruit);

    // 修改库存
    boolean updateFruit(Fruit fruit);

    // 提供名称查询特定库存
    Fruit getFruitByFname(String fname);

    // 删除特定库存记录
    boolean delFruit(String fname);
}
