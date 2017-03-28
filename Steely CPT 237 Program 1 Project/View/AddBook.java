//FILE: AddBook.java
//PROG: Marshall Chase Steely
//PURP: Opens a window that allows you to add a book to the ComboBox.

package edu.tridenttech.CPT237.Steely.Library.View;

import edu.tridenttech.CPT237.Steely.Library.Model.Book;
import edu.tridenttech.CPT237.Steely.Library.Model.HomeLibrary;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class AddBook implements EventHandler<ActionEvent> {

	public Stage myStage;

	HomeLibrary h = HomeLibrary.getInstance();
	Book bk;
	private TextField title;
	private TextField author;
	RadioButton kindle;
	RadioButton print;
	Button add;
	Button cancel;
	ComboBox<Book> books;
	ToggleGroup group = new ToggleGroup();

	public AddBook(ComboBox<Book> b) {
		books = b;
		FlowPane pane = new FlowPane();
		Scene scene = new Scene(pane);
		myStage = new Stage();
		myStage.setScene(scene);
		myStage.setTitle("Add A Book");

		title = new TextField();
		title.setText("Enter Book Title");
		pane.getChildren().add(title);

		author = new TextField();
		author.setPromptText("Enter Author");
		pane.getChildren().add(author);

		add = new Button("ADD");
		pane.getChildren().add(add);
		add.setOnAction(this);

		cancel = new Button("Cancel");
		pane.getChildren().add(cancel);
		cancel.setOnAction(this);

		kindle = new RadioButton("Kindle");
		print = new RadioButton("Print");
		kindle.setToggleGroup(group);
		print.setToggleGroup(group);
		pane.getChildren().add(kindle);
		pane.getChildren().add(print);
	}

	public void toFront() {
		myStage.toFront();
	}

	public boolean isShowing() {
		return myStage.isShowing();
	}

	public void show() {
		myStage.show();
		myStage.toFront();

	}

	@Override
	public void handle(ActionEvent event) {
		Button button = (Button) (event.getSource());

		if (button == add) {
			String t = title.getText();
			String a = author.getText();
			boolean isKindleSelected = kindle.isSelected();

			if (isKindleSelected) {
				h.createKindle("Kindle Book", a, t);
			} else {
				h.createPrint("Print Book", a, t);
			}
			books.getItems().setAll(h.getAllBooks());
			myStage.close();
		}

		if (button == cancel) {
			myStage.close();
		}
	}

}
