package library_management;

import java.util.Scanner;

public class MainController {

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		UserCRUD crud = new UserCRUD();
		User user = new User();
		Book book = new Book();
		System.out.println("Library Mangament");
		System.out.println("1.Signup \n2.Login");
		int choice = sc.nextInt();

		switch (choice) {
		case 1: {
			System.out.println("Enter id");
			int id = sc.nextInt();
			System.out.println("Enter name");
			String name = sc.next();
			System.out.println("Enter phone");
			long phone = sc.nextLong();
			System.out.println("Enter email");
			String email = sc.next();
			System.out.println("Enter password");
			String password = sc.next();

			user.setId(id);
			user.setName(name);
			user.setPhone(phone);
			user.setEmail(email);
			user.setPassword(password);
			crud.signUp(user);
		}
			break;
		case 2: {

			System.out.println("Enter email");
			String email = sc.next();
			System.out.println("Enter password");
			String password = sc.next();

			user.setEmail(email);
			user.setPassword(password);
			boolean result = crud.logIn(user);
			if (result) {
				System.out.println("Login successfully");
				System.out.println(
						"1.Add book \n2.find bookbyid \n3.find book by Auther \n4.find book by genre \n5.update book \n6.Display books");
				int key = sc.nextInt();
				switch (key) {
				case 1: {
					System.out.println("Enter id");
					int id = sc.nextInt();
					System.out.println("Enter Name");
					String name = sc.next();
					System.out.println("Enter Author");
					String author = sc.next();
					System.out.println("Enter Price");
					int price = sc.nextInt();
					System.out.println("Enter gener");
					String gener = sc.next();

					book.setId(id);
					book.setName(name);
					book.setAuthor(author);
					book.setPrice(price);
					book.setGener(gener);
					crud.addBook(book);

				}
					break;
				case 2: {
					System.out.println("Enter id");
					int id = sc.nextInt();

					book.setId(id);

					crud.findBookById(book);

					break;
				}
				case 3: {
					System.out.println("Enter Author");
					String genre = sc.next();

					book.setAuthor(genre);

					crud.findBookByAuthor(book);

					break;
				}
				case 4: {
					System.out.println("Enter gener");
					String gener = sc.next();

					book.setGener(gener);

					crud.findBookByGenre(book);

					break;
				}
				case 5: {
					System.out.println("Enter id");
					int id = sc.nextInt();
					System.out.println("Enter Name");
					String name = sc.next();
					System.out.println("Enter Author");
					String author = sc.next();
					System.out.println("Enter Price");
					int price = sc.nextInt();
					System.out.println("Enter gener");
					String gener = sc.next();
					book.setId(id);
					book.setName(name);
					book.setAuthor(author);
					book.setPrice(price);
					book.setGener(gener);
					 
					crud.updateBook(book);

					break;
				}
				case 6: {

					crud.displayBooks(book);

					break;
				}
				default: {

					break;
				}

				}
			} else {
				System.out.println("Login failed");
			}

		}
			break;

		default:
			break;
		}
	}

}
