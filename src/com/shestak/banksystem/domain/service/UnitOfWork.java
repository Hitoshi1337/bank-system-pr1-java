package com.shestak.banksystem.domain.service;

import com.shestak.banksystem.domain.repository.ProductRepository;
import com.shestak.banksystem.domain.repository.UserRepository;
import com.shestak.banksystem.domain.storage.FileStore;

public class UnitOfWork {
    public final UserRepository users;
    public final ProductRepository products;

    private final FileStore store;

    public UnitOfWork(FileStore store, UserRepository users, ProductRepository products) {
        this.store = store;
        this.users = users;
        this.products = products;
    }

    public void load() {
        UserRepository loadedUsers = store.load("users", UserRepository.class);
        ProductRepository loadedProducts = store.load("products", ProductRepository.class);

        if (loadedUsers != null) users.copyFrom(loadedUsers);
        if (loadedProducts != null) products.copyFrom(loadedProducts);
    }

    public void commit() {
        store.save("users", users);
        store.save("products", products);
    }
}
