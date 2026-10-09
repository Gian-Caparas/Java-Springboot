package com.example.firstspringbootapp.service;

import java.util.List;
import java.util.NoSuchElementException;
import javax.naming.NameNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.firstspringbootapp.entity.ItemEntity;
import com.example.firstspringbootapp.repository.ItemRepository;

@Service
public class ItemService {

    @Autowired
    ItemRepository irepo;

    public ItemService() {}
    
    public ItemEntity postItem(ItemEntity Item) {
        return irepo.save(Item);
    }

    public List<ItemEntity> getAllItems() {
        return irepo.findAll();
    }

    @SuppressWarnings("finally")
    public ItemEntity putItem(int id, ItemEntity newItemDetails) {
        ItemEntity Item = new ItemEntity();
        try {
            Item = irepo.findById(id).get();
            Item.setName(newItemDetails.getName());
            Item.setUnit(newItemDetails.getUnit());
            Item.setPrice(newItemDetails.getPrice());
        } catch (NoSuchElementException ex) {
            throw new NameNotFoundException("Item " + id + " does not exist!");
        } finally {
            return irepo.save(Item);
        }
    }
    public String deleteItem(int id) {
        String msg = "";
        if (irepo.findById(id) != null) {
            irepo.deleteById(id);
            msg = "Item " + id + " is successfully deleted!";
        } else
            msg = id + "does not exist!";
        return msg;
    }

    @ExceptionHandler
    public ResponseEntity<NameErrorResponse> handleException(NameNotFoundException ex) {
        NameErrorResponse error = new NameErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // generic exception
    @ExceptionHandler
    public ResponseEntity<NameErrorResponse> handleException(Exception ex) {
        NameErrorResponse error = new NameErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}