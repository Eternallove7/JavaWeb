package com.study.fruit.view;

import com.study.fruit.controller.Menu;

/**
 * @author RenAshbell
 * @create 2022-04-21-16:29
 */
public class Client {
    public static void main(String[] args) {
        Menu m = new Menu();
        boolean flag = true;
        while (flag){
            int slt = m.showMainMenu();
            switch (slt){
                case 1:
                    // 显示库存列表
                    m.showFruitList();
                    break;
                case 2:
                    m.addFruit();
                    break;
                case 3:
                    m.showFruitInfo();
                    break;
                case 4:
                    m.delFruit();
                    break;
                case 5:
                    flag=m.exit();
                    break;
                default:
                    System.out.println("输入错误，请重新输入!");
                    break;
            }
        }
        System.out.println("谢谢使用，再见！");


    }
}
