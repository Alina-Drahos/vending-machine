/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachineapi.controller;

import com.mycompany.vendingmachineapi.dao.ItemDao;
import com.mycompany.vendingmachineapi.dto.Item;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alinc
 */
@RestController
@RequestMapping("/api")
public class VendingMachineAPIController {
    @Autowired
    ItemDao item;
    
    @GetMapping("/items")
    public List<Item> getAllItems(){
        return item.getAllItems();
    }
    
    @PutMapping("/items/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Item> purchaseItem(@PathVariable int id){
     return item.dispenseItem(id);
    }
 
}
