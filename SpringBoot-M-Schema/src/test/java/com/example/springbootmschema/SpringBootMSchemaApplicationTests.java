package com.example.springbootmschema;

import com.example.springbootmschema.utils.MySQLSchemaExporter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class SpringBootMSchemaApplicationTests {

	@Test
	void contextLoads() {
		MySQLSchemaExporter mysqldb = new MySQLSchemaExporter();
		mysqldb.writeScheamFile();
	}

}
