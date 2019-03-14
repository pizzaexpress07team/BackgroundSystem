package com.project.PizzaExpress.Datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

public class DataSourceProperty {

        private String driverClassName;
        private String url;
        private String username;
        private String password;

        public void setDriverClassName(String driverClassName) {
                this.driverClassName = driverClassName;
        }

        public void setUrl(String url) {
                this.url = url;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getDriverClassName() {
                return driverClassName;
        }

        public String getUrl() {
                return url;
        }

        public String getUsername() {
                return username;
        }

        public String getPassword() {
                return password;
        }

}
