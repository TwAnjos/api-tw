package com.tw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tw.model.Tw;

public interface ITwRepository extends JpaRepository<Tw, Long> {

}
