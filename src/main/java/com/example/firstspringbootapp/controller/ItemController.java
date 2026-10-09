package com.example.firstspringbootapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.firstspringbootapp.entity.ItemEntity;
import com.example.firstspringbootapp.service.ItemService;

@RestController
@RequestMapping(method = RequestMethod.GET, path = "/item/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ItemController {

    @Autowired
    ItemService iserv;

    public ItemController() {}

    @GetMapping("/printAuthor")
    public String printAuthor() {
        return "Leah V. Barbaso"; 
    }

    // C - Create
    @PostMapping("/postNewItem")
    public ItemEntity postItem(@RequestBody ItemEntity Item) {
        return iserv.postItem(Item);
    }

    // R - Read
    @GetMapping("/getAllItems")
    public List<ItemEntity> getAllItems() {
        return iserv.getAllItems();
    }

    // U - Update
    @PutMapping("/putItem")
    public ItemEntity putItem(@RequestParam int id, @RequestBody ItemEntity newItemDetails) {
        return iserv.putItem(id, newItemDetails);
    }

    // D - Delete
    @DeleteMapping("/deleteItem/{id}")
    public String deleteItem(@PathVariable int id) {
        return iserv.deleteItem(id);
    }
}