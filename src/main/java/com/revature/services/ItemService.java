package com.revature.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.revature.models.Cart;
import com.revature.models.Item;
import com.revature.repositories.CartRepository;
import com.revature.repositories.ItemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    
    @Autowired
    private ItemRepository itemDAO;

    @Autowired
    private UserService userService;

    @Autowired
    private CartRepository cartDAO;

    public List<Item> findAll() {
        return itemDAO.findAll();
    }

    public void addToCart(String item, int quantity) {
        for (int i = 0; i < quantity; i++) {
            itemDAO.save(new Cart(userService.getCurrentUser().getId(), itemDAO.findByDescription(item).getId()));
        }
    }

    public List<Item> getCartItems() {
        List<Cart> items = cartDAO.findByUserId(userService.getCurrentUser().getId());
        List<Item> cartItems = new ArrayList<Item>();
        
        for (int i = 0; i < items.size(); i++) {
            cartItems.add(itemDAO.findById(items.get(i).getItemId()));
        }

        userService.getCurrentUser().setCart(cartItems);

        return cartItems;
    }

    public void placeOrder() {
        List<Item> itemsCart = userService.getCurrentUser().getCart();
        String uuid = UUID.randomUUID().toString();
        for (int i = 0; i < itemsCart.size(); i++) {
            // cartItems.get(i).setQuantity(0);
        }
    }
}
