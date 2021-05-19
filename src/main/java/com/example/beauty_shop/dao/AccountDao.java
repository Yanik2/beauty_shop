package com.example.beauty_shop.dao;

import com.example.beauty_shop.entity.entities.Account;

import java.util.Optional;

public interface AccountDao {
    Optional<Account> findByName(String name);
}
