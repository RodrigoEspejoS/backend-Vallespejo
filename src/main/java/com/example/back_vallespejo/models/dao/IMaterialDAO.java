package com.example.back_vallespejo.models.dao;

import com.example.back_vallespejo.models.entities.Material;
import org.springframework.data.repository.CrudRepository;

public interface IMaterialDAO extends CrudRepository<Material, Long> {
}
