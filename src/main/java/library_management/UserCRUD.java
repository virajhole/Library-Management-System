package library_management;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class UserCRUD {

	public Connection getConnection() throws Exception {
		FileReader fileReader = new FileReader("user_config.properties");
		Properties properties = new Properties();
		properties.load(fileReader);
		Class.forName(properties.getProperty("className"));
		Connection con = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("user"),
				properties.getProperty("password"));

		return con;

	}

	public void signUp(User user) throws Exception {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO USER(id,name,phone,email,password) VALUES(?,?,?,?,?)");
		preparedStatement.setInt(1, user.getId());
		preparedStatement.setString(2, user.getName());
		preparedStatement.setLong(3, user.getPhone());

		preparedStatement.setString(4, user.getEmail());
		preparedStatement.setString(5, user.getPassword());

		int result = preparedStatement.executeUpdate();

		if (result != 0) {
			System.out.println("inserted");
		} else {
			System.out.println("not inserted");
		}

		connection.close();
	}

	public boolean logIn(User user) throws Exception {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT PASSWORD FROM USER WHERE EMAIL=?");

		preparedStatement.setString(1, user.getEmail());
		String password = null;
		ResultSet resultSet = preparedStatement.executeQuery();

		while (resultSet.next()) {
			password = resultSet.getString("password");

		}
		connection.close();
		if (user.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	public void addBook(Book book) throws Exception {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO BOOK(id,name,author,price,gener) VALUES(?,?,?,?,?)");
		preparedStatement.setInt(1, book.getId());
		preparedStatement.setString(2, book.getName());
		preparedStatement.setString(3, book.getAuthor());

		preparedStatement.setInt(4, book.getPrice());
		preparedStatement.setString(5, book.getGener());

		int result = preparedStatement.executeUpdate();

		if (result != 0) {
			System.out.println("Inserted");
		} else {
			System.out.println("Not Inserted");
		}

		connection.close();
     displayBooks(book);
	}

	public void findBookById(Book book) throws Exception {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM BOOK WHERE ID=? ");
		preparedStatement.setInt(1, book.getId());
		ResultSet resultSet = preparedStatement.executeQuery();

		boolean exist = false;
		boolean notExist = false;
		while (resultSet.next()) {
			exist = true;
			System.out.println("id :" + resultSet.getInt("id"));
			System.out.println("name :" + resultSet.getString("name"));
			System.out.println("Author :" + resultSet.getString("author"));
			System.out.println("Price :" + resultSet.getInt("price"));
			System.out.println("Gener :" + resultSet.getString("gener"));
		}
		if (exist) {

		} else {
			System.out.println("plz enter correct !");
		}

		connection.close();
	}

	public void findBookByAuthor(Book book) throws Exception {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM BOOK WHERE AUTHOR=? ");
		preparedStatement.setString(1, book.getAuthor());
		ResultSet resultSet = preparedStatement.executeQuery();
		boolean exist = false;
		boolean notExist = false;
		while (resultSet.next()) {

			System.out.println("id :" + resultSet.getInt("id"));
			System.out.println("name :" + resultSet.getString("name"));
			System.out.println("Author :" + resultSet.getString("author"));
			System.out.println("Price :" + resultSet.getInt("price"));
			System.out.println("Gener :" + resultSet.getString("gener"));
		}
		if (exist) {

		} else {
			System.out.println("plz enter correct !");
		}
		connection.close();
	}

	public void findBookByGenre(Book book) throws Exception {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM BOOK WHERE GENER=? ");
		preparedStatement.setString(1, book.getGener());
		ResultSet resultSet = preparedStatement.executeQuery();
		boolean exist = false;
		boolean notExist = false;
		while (resultSet.next()) {

			System.out.println("id :" + resultSet.getInt("id"));
			System.out.println("name :" + resultSet.getString("name"));
			System.out.println("Author :" + resultSet.getString("author"));
			System.out.println("Price :" + resultSet.getInt("price"));
			System.out.println("Gener :" + resultSet.getString("gener"));
		}
		if (exist) {

		} else {
			System.out.println("plz enter correct !");
		}
		connection.close();

	}

	public void updateBook(Book book) throws Exception {

		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection
				.prepareStatement("UPDATE BOOK SET NAME=?, AUTHOR=?, PRICE=?, GENER=? WHERE ID=?");

		preparedStatement.setString(1, book.getName());
		preparedStatement.setString(2, book.getAuthor());
		preparedStatement.setInt(3, book.getPrice());
		preparedStatement.setString(4, book.getGener());

		preparedStatement.setInt(5, book.getId());

		int result = preparedStatement.executeUpdate();

		if (result != 0) {
			System.out.println("Updated");
		} else {
			System.out.println("Not Updated");
		}

		connection.close();
		displayBooks(book);
	}

	public void displayBooks(Book book) throws Exception {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM BOOK");

		ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			System.out.println("ID : " + resultSet.getInt("id"));
			System.out.println("Name: " + resultSet.getString("name"));
			System.out.println("Author: " + resultSet.getString("author"));
			System.out.println("Price: " + resultSet.getInt("price"));
			System.out.println("Gener: " + resultSet.getString("gener"));
			System.out.println("******************************************");
		}
		connection.close();
	}

}
