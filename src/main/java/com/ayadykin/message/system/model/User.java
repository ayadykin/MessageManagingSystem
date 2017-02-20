package com.ayadykin.message.system.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@NotNull
	private String name;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private Set<Message> messages = new HashSet<>();

	public User(String name) {
		this.name = name;
	}

	public boolean removeMessage(Message message) {
		return messages.remove(message);
	}

	public boolean addMessage(Message message) {
		return messages.add(message);
	}

}
