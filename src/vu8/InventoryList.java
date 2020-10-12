/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vu8;

import java.util.ArrayList;

/**
 *
 * @author Nam Vu
 */
public class InventoryList {
    private ArrayList<Inventory> invList;
    public InventoryList(){
        invList = new ArrayList<Inventory>();
    }
    public void add(Inventory inventory){
        invList.add(inventory);
    }
    public Inventory get(int index){
        return invList.get(index);
    }
    public int length(){
        return invList.size();
    }
}
