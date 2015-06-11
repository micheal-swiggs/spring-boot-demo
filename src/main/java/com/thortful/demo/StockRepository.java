package com.thortful.demo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "stocks", path = "stocks")
public interface StockRepository extends PagingAndSortingRepository<Stock, Integer>
{
    List<Stock> findByName(@Param("name") String name);
}
