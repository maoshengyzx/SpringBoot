package com.example.springbootddl.db_enum;

public enum DbEnum {

    MYSQL,
    ORACLE,
    POSTGRESQL,
    SQLSERVER

    // Add more database types as needed

    ;

    public static DbEnum fromString(String dbType) {
        for (DbEnum db : DbEnum.values()) {
            if (db.name().equalsIgnoreCase(dbType)) {
                return db;
            }
        }
        return null;
    }
}
