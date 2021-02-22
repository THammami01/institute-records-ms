module SGRN {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.sql;
    requires mariadb.java.client;
	requires sqlite.jdbc;

	opens main;
}