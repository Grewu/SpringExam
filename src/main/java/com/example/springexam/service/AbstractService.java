package com.example.springexam.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface AbstractService<K, E> {
  E create(E t);
  Page<E> getAll(Pageable pageable);
  E getById(K id);
  E update(K id, E t);
  void delete(K id);
}
