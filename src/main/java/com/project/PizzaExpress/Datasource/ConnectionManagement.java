package com.project.PizzaExpress.Datasource;

import java.sql.Connection;

public class ConnectionManagement {

    public Connection getConnection() throws Exception
    {
        return new DataSourceConfig().dataSource().getConnection();
    }

}
