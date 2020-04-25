/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachineapi.controller;

import com.mycompany.vendingmachineapi.dao.InsufficientFundsException;
import com.mycompany.vendingmachineapi.dao.ItemDao;
import com.mycompany.vendingmachineapi.dao.OutOfStockException;
import com.mycompany.vendingmachineapi.dto.Change;
import com.mycompany.vendingmachineapi.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping(value="/api")
@CrossOrigin(origins = "*")
public class VendingMachineAPIController {

    @Autowired
    ItemDao item;

    @GetMapping("/items")
    public List<Item> getAllItems() {
        return item.getAllItems();
    }

    @PutMapping("/money/{totalAmount}/item/{id}")
    public Change purchaseItem(@PathVariable BigDecimal totalAmount, @PathVariable int id) throws InsufficientFundsException, OutOfStockException {
        Change change= new Change(item.getItembyId(id),totalAmount);
         item.dispenseItem(id,totalAmount);
         return change ;
    }


}
