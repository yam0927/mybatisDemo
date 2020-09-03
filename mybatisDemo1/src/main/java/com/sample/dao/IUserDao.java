package com.sample.dao;

import com.sample.domain.QueryVo;
import com.sample.domain.User;

import java.util.List;

public interface IUserDao {
    List<User> findAll();
    User findById(Integer userID);
    int saveUser(User user);
    int updateUser(User user);
    List<User> findByName(String username);
    List<User> findByName2(String username);
    List<User> findByVo(QueryVo vo);
    List<User> findByVo2(QueryVo vo);
    int count();

}
