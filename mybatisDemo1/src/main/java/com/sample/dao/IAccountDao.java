package com.sample.dao;

import com.sample.domain.AccountUser;

import java.util.List;

public interface IAccountDao {
    List<AccountUser> findAll();
}
