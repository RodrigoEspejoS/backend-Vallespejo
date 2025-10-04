package com.example.back_vallespejo.models.dao;

import com.example.back_vallespejo.models.entities.ListaMateriales;


import org.springframework.data.repository.CrudRepository;


public interface IListaMaterialesDAO extends CrudRepository<ListaMateriales, Long> {

}
