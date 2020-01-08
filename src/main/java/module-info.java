module Budget {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires org.flywaydb.core;

	exports overview;
	exports overview.controllers;
	exports transactions.controllers;

	opens transactions.controllers to javafx.fxml;
	opens category.controllers to javafx.fxml;
}