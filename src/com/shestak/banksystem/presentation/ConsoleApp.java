package com.shestak.banksystem.presentation;

import com.shestak.banksystem.domain.dto.LoginDto;
import com.shestak.banksystem.domain.dto.SearchDto;
import com.shestak.banksystem.domain.dto.UserRegistrationDto;
import com.shestak.banksystem.domain.entity.Role;
import com.shestak.banksystem.domain.entity.User;
import com.shestak.banksystem.domain.service.*;

import java.util.Scanner;

public class ConsoleApp {

  private final UnitOfWork uow;
  private final AuthService auth;
  private final ProductService products;
  private final UserService users;

  private final Scanner sc = new Scanner(System.in);
  private User current;

  public ConsoleApp(UnitOfWork uow, AuthService auth, ProductService products, UserService users) {
    this.uow = uow;
    this.auth = auth;
    this.products = products;
    this.users = users;
  }

  public void start() {

    try { uow.load(); } catch (Exception ignored) {}

    while (true) {
      if (current == null) {
        if (!authMenu()) break;
      } else {
        if (!appMenu()) break;
      }
    }

    uow.commit();
    System.out.println("Bye!");
  }

  private boolean authMenu() {
    System.out.println("\n=== AUTH MENU ===");
    System.out.println("1) Register");
    System.out.println("2) Login");
    System.out.println("0) Exit");
    System.out.print("> ");

    String c = sc.nextLine().trim();
    switch (c) {
      case "1" -> register();
      case "2" -> login();
      case "0" -> { return false; }
      default -> System.out.println("Unknown option");
    }
    return true;
  }

  private boolean appMenu() {
    System.out.println("\n=== MAIN MENU ===");
    System.out.println("User: " + current.getEmail() + " (" + current.getRole() + ")");
    System.out.println("1) Products");
    if (current.getRole() == Role.ADMIN) System.out.println("2) Admin: Users roles");
    System.out.println("9) Logout");
    System.out.println("0) Exit");
    System.out.print("> ");

    String c = sc.nextLine().trim();
    switch (c) {
      case "1" -> productsMenu();
      case "2" -> {
        if (current.getRole() == Role.ADMIN) adminUsersMenu();
        else System.out.println("Access denied");
      }
      case "9" -> current = null;
      case "0" -> { return false; }
      default -> System.out.println("Unknown option");
    }
    return true;
  }

  private void register() {
    System.out.print("Email: ");
    String email = sc.nextLine();
    System.out.print("Password: ");
    String pass = sc.nextLine();

    try {
      current = auth.register(new UserRegistrationDto(email, pass));
      uow.commit();
      System.out.println("Registered + logged in.");
    } catch (Exception e) {
      System.out.println("ERROR: " + e.getMessage());
    }
  }

  private void login() {
    System.out.print("Email: ");
    String email = sc.nextLine();
    System.out.print("Password: ");
    String pass = sc.nextLine();

    try {
      current = auth.login(new LoginDto(email, pass));
      System.out.println("Logged in.");
    } catch (Exception e) {
      System.out.println("ERROR: " + e.getMessage());
    }
  }

  // PRODUCTS
  private void productsMenu() {
    while (true) {
      System.out.println("\n--- PRODUCTS ---");
      System.out.println("1) Create");
      System.out.println("2) List");
      System.out.println("3) Search");
      System.out.println("4) Update");
      System.out.println("5) Delete");
      System.out.println("0) Back");
      System.out.print("> ");

      String c = sc.nextLine().trim();
      switch (c) {
        case "1" -> createProduct();
        case "2" -> listProducts();
        case "3" -> searchProducts();
        case "4" -> updateProduct();
        case "5" -> deleteProduct();
        case "0" -> { return; }
        default -> System.out.println("Unknown option");
      }
    }
  }

  private void createProduct() {
    try {
      System.out.print("Name: ");
      String name = sc.nextLine();
      System.out.print("Qty: ");
      int qty = Integer.parseInt(sc.nextLine());

      var p = products.create(name, qty);
      uow.commit();
      System.out.println("Created id=" + p.getId());
    } catch (Exception e) {
      System.out.println("ERROR: " + e.getMessage());
    }
  }

  private void listProducts() {
    products.list(new SearchDto("")).forEach(p ->
        System.out.println(p.getId() + " | " + p.getName() + " | qty=" + p.getQuantity())
    );
  }

  private void searchProducts() {
    System.out.print("Query: ");
    String q = sc.nextLine();
    products.list(new SearchDto(q)).forEach(p ->
        System.out.println(p.getId() + " | " + p.getName() + " | qty=" + p.getQuantity())
    );
  }

  private void updateProduct() {
    try {
      System.out.print("ID: ");
      long id = Long.parseLong(sc.nextLine());

      System.out.print("New name (empty=skip): ");
      String name = sc.nextLine();

      System.out.print("New qty (empty=skip): ");
      String qtyStr = sc.nextLine().trim();
      Integer qty = qtyStr.isEmpty() ? null : Integer.parseInt(qtyStr);

      var p = products.update(id, name.isEmpty() ? null : name, qty);
      uow.commit();
      System.out.println("Updated id=" + p.getId());
    } catch (Exception e) {
      System.out.println("ERROR: " + e.getMessage());
    }
  }

  private void deleteProduct() {
    try {
      System.out.print("ID: ");
      long id = Long.parseLong(sc.nextLine());
      products.delete(id);
      uow.commit();
      System.out.println("Deleted.");
    } catch (Exception e) {
      System.out.println("ERROR: " + e.getMessage());
    }
  }


  private void adminUsersMenu() {
    while (true) {
      System.out.println("\n--- ADMIN: USERS ---");
      System.out.println("1) List users");
      System.out.println("2) Change role");
      System.out.println("0) Back");
      System.out.print("> ");

      String c = sc.nextLine().trim();
      switch (c) {
        case "1" -> users.list().forEach(u ->
            System.out.println(u.getId() + " | " + u.getEmail() + " | " + u.getRole())
        );
        case "2" -> changeRole();
        case "0" -> { return; }
        default -> System.out.println("Unknown option");
      }
    }
  }

  private void changeRole() {
    try {
      System.out.print("User ID: ");
      long id = Long.parseLong(sc.nextLine());

      System.out.print("Role (ADMIN/USER): ");
      String r = sc.nextLine().trim().toUpperCase();

      Role role = Role.valueOf(r);
      users.setRole(id, role);
      uow.commit();
      System.out.println("Role changed.");
    } catch (Exception e) {
      System.out.println("ERROR: " + e.getMessage());
    }
  }
}
