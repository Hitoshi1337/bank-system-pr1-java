package com.shestak.banksystem;

import com.shestak.banksystem.domain.repository.ProductRepository;
import com.shestak.banksystem.domain.repository.UserRepository;
import com.shestak.banksystem.domain.service.*;
import com.shestak.banksystem.domain.storage.FileStore;
import com.shestak.banksystem.presentation.ConsoleApp;

public class Main {
  public static void main(String[] args) {
    UserRepository userRepo = new UserRepository();
    ProductRepository productRepo = new ProductRepository();

    FileStore store = new FileStore("data");
    UnitOfWork uow = new UnitOfWork(store, userRepo, productRepo);

    EmailService email = new EmailService();
    AuthService auth = new AuthService(userRepo, email);
    ProductService products = new ProductService(productRepo);
    UserService users = new UserService(userRepo);

    new ConsoleApp(uow, auth, products, users).start();
  }
}
