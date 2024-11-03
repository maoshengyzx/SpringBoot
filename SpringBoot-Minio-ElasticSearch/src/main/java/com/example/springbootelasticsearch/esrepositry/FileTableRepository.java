package com.example.springbootelasticsearch.esrepositry;

import com.example.springbootelasticsearch.entity.FileTable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Administrator
 */
public interface FileTableRepository extends PagingAndSortingRepository<FileTable,Long> {

}
