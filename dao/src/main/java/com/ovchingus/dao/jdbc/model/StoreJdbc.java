package com.ovchingus.dao.jdbc.model;


import com.ovchingus.dao.KeyDb;
import com.ovchingus.dao.hibernate.Store;
import com.ovchingus.dao.jdbc.Identified;

public class StoreJdbc extends Store implements Identified<KeyDb> {

    public StoreJdbc(Integer id, String name, String address) {
        //super(id, name, address);
    }

    public StoreJdbc(String name, String address) {
        super(name, address);
    }

    public StoreJdbc() {
    }

    @Override
    public KeyDb identifyKey() {
        return new KeyDb(getId());
    }
}