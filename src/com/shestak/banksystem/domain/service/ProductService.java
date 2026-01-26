package com.shestak.banksystem.domain.service;

import com.shestak.banksystem.domain.dto.SearchDto;
import com.shestak.banksystem.domain.entity.Product;
import com.shestak.banksystem.domain.repository.ProductRepository;
import com.shestak.banksystem.util.Validate;

import java.util.List;

public class ProductService {
    private final ProductRepository repo;

    public ProductService(ProductRepository repo) {
        this.repo = repo;
    }

    public Product create(String name, int qty) {
        Validate.notBlank(name, "Name required");
        if (qty < 0) throw new IllegalArgumentException("Quantity must be >= 0");
        return repo.save(new Product(name, qty));
    }

    public List<Product> list(SearchDto s) {
        if (s == null || s.query() == null || s.query().isBlank()) return repo.findAll();
        String q = s.query().toLowerCase();
        return repo.findAll().stream()
            .filter(p -> p.getName() != null && p.getName().toLowerCase().contains(q))
            .toList();
    }

    public Product update(Long id, String name, Integer qty) {
        Product p = repo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Product not found: " + id));
        if (name != null && !name.isBlank()) p.setName(name);
        if (qty != null) {
            if (qty < 0) throw new IllegalArgumentException("Quantity must be >= 0");
            p.setQuantity(qty);
        }
        return repo.save(p);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
