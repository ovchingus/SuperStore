package com.ovchingus.service.func;


import com.ovchingus.service.func.model.ShopItem;

import java.util.Map;

public interface ServiceMethods {

    /**
     * Создать магазин
     */
    public void createStore(Integer storeId, String name, String address);

    /**
     * Создать товар
     */
    public void createProduct(Integer productId, String name);

    /**
     * Создать список товаров из одьектов ShopItem
     */
    public ShopItem createShopItem(String storeName, String productName, Integer qty, Double price);

    /**
     * Завезти партию товаров в магазин (набор товар-количество
     * с возможностью установить/изменить цену
     */
    //public void insertProductListToStore(Integer storeId,  String , List<ShopItem> list);
    public void insertProductToStore(String storeName, String productName, Integer qty, Double price);

    /**
     * Найти магазин, в котором определенный товар самый дешевый
     */
    public String findStoreWithCheapestProduct(String productName);

    /**
     * Понять, какие товары можно купить в магазине на некоторую
     * сумму (например, на 100 рублей можно купить три мороженки
     * или две шоколадки) я так понял тут про уникальный товар а
     * не про набор разных
     */
    public Map<String, Integer> findProductListForSum(Double budget);

    /**
     * Купить партию товаров в магазине (параметры - сколько
     * каких товаров купить, метод возвращает общую стоимость
     * покупки либо её невозможность, если товара не хватает)
     */
    //public Integer buyListOfProductsInOneStore(String store, Map<String, Integer> map);
    public Integer buyProductsInOneStore(String storeName, String productName, Integer qty);

    /**
     * Найти, в каком магазине партия товаров (набор товар-количество) имеет
     * наименьшую сумму (в целом). Например, «в каком магазине дешевле всего купить
     * 10 гвоздей и 20 шурупов». Наличие товара в магазинах учитывается!
     */
    public String findStoreWithCheapestShopList(String query);

}
