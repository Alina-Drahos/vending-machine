/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachineapi.dao;

import com.mycompany.vendingmachineapi.dto.Item;
import java.util.List;

/**
 *
 * @author alinc
 */
public interface ItemDao {
    
    List<Item> getAllItems();
    List<Item> dispenseItem(int itemId);
    Item addItem(Item newItem);
    List<Item> deleteItem(int itemId);
    Item getItembyId(int Id);

}
