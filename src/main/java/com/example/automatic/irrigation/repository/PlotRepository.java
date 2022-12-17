package com.example.automatic.irrigation.repository;

import com.example.automatic.irrigation.entity.Plot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlotRepository extends JpaRepository<Plot, String> {

}
