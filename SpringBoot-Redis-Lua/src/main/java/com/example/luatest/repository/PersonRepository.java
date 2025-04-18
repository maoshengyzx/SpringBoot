package com.example.luatest.repository;

import com.example.luatest.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * ClassName: PersonRepository
 * Package: com.example.luatest.repository
 * Description:
 * person的crud
 * 主要针对Redis的操作
 *
 * @Author ms
 * @Create 2024/11/30 10:02
 * @Version 1.0
 */
public interface PersonRepository extends PagingAndSortingRepository<Person, String> {
}
