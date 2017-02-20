package com.ayadykin.message.system.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.ayadykin.message.system.model.User;

@Transactional
public interface UserDao extends CrudRepository<User, Long> {

	User findByName(String name);
}
