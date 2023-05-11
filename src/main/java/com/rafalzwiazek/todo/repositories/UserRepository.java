package com.rafalzwiazek.todo.repositories;

import com.rafalzwiazek.todo.data.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
   List<User> findByUsername(String name);

   List<User> findByPassword(String password);
}
