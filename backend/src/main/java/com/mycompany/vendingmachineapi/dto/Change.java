/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachineapi.dto;

import java.math.BigDecimal;

/**
 *
 * @author alinc
 */
public class Change {
    
    private int quarters;
    private int dimes;
    private int nickels;
    private int pennies;
    
    public Change(Item item, BigDecimal money) {
        calculateChange(money,item);

    }

    private  void calculateChange(BigDecimal money,Item item) {
        int change= (money.subtract(item.getPrice()).multiply(new BigDecimal("100"))).intValue();
        while (change>0) {
            if(change>25){
                quarters++;
                change-=25;
            }
            else if(change>10){
                dimes++;
                change-=10;
            }
            else if(change>5){
                nickels++;
                change-=5;
            }
            else{
                pennies++;
                change--;
            }

        }
    }
    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public int getPennies() {
        return pennies;
    } 

}
