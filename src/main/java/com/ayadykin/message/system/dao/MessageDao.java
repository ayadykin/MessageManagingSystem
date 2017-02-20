package com.ayadykin.message.system.dao;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import com.ayadykin.message.system.model.Message;

@Transactional
public interface MessageDao extends CrudRepository<Message, Long>{

}
