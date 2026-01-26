package com.shestak.banksystem.domain.repository;

import com.shestak.banksystem.domain.entity.BaseEntity;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryRepository<T extends BaseEntity> implements IRepository<T>, Serializable {
    private static final long serialVersionUID = 1L;

    protected Map<Long, T> data = new LinkedHashMap<>();
    protected AtomicLong seq = new AtomicLong(1);

    @Override
    public T save(T entity) {
        if (entity.getId() == null) entity.setId(seq.getAndIncrement());
        data.put(entity.getId(), entity);
        return entity;
    }

    @Override
    public Optional<T> findById(Long id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public void deleteById(Long id) {
        data.remove(id);
    }

    public void copyFrom(InMemoryRepository<T> other) {
        this.data = other.data;
        this.seq = other.seq;
    }
}
