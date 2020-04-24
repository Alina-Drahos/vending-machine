/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.vendingmachineapi.dao;

import com.mycompany.vendingmachineapi.dto.Item;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author alinc
 */
@Repository
public class ItemDaoDB implements ItemDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Item> getAllItems() {
        final String SELECT_ALL_PRODUCTS="SELECT * FROM item";
        return jdbc.query(SELECT_ALL_PRODUCTS, new ItemMapper());
    }

    @Transactional
    @Override
    public List<Item> dispenseItem(int itemId) {
        final String UPDATE_COUNT="UPDATE item"
                + " SET amount=(amount-1) WHERE id=?";
        jdbc.update(UPDATE_COUNT, itemId);
        final String SELECT_ALL_PRODUCTS="SELECT * FROM item";
        return jdbc.query(SELECT_ALL_PRODUCTS, new ItemMapper());
    }

    @Override
    public Item addItem(Item newItem) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Item> deleteItem(int itemId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static final class ItemMapper implements RowMapper<Item> {

        @Override
        public Item mapRow(ResultSet rs, int i) throws SQLException {
            Item item = new Item(rs.getInt("id"), rs.getBigDecimal("price"),
                    rs.getInt("amount"), rs.getString("productname"));
            return item;
        }

    }

}
