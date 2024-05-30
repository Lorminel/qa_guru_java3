package com.lorminel.data;

import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;

public enum DataSourceProvider {
    INSTANCE;

    private DataSource dataSource;

    public DataSource getDataSource() {
        if (dataSource == null) {
            PGSimpleDataSource pgDataSource = new PGSimpleDataSource();
            pgDataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
            pgDataSource.setUser("postgres");
            pgDataSource.setPassword("secret");
            dataSource = pgDataSource;
        }
        return dataSource;
    }
}
