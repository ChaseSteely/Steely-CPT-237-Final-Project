//FILE: SearchWIndow.java
//PROG: Marshall Chase Steely
//PURP: Window that allows user to search by author or use a keyword to search titles.

package edu.tridenttech.CPT237.Steely.Library.View;

import java.util.List;

import edu.tridenttech.CPT237.Steely.Library.Model.Book;
import edu.tridenttech.CPT237.Steely.Library.Model.HomeLibrary;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class SearchWindow implements EventHandler<ActionEvent> {
	private Stage myStage;
	private TextField title;
	Button search;
	HomeLibrary h = HomeLibrary.getInstance();
	Book bk;
	private TextArea ta;
	Button clear;
	Button done;
	Button tt;

	public SearchWindow() {

		FlowPane pane = new FlowPane();
		Scene scene = new Scene(pane);
		myStage = new Stage();
		myStage.setScene(scene);
		myStage.setTitle("Search");

		title = new TextField();
		title.setPromptText("To Search: enter a Keyword, Title, or Author.");
		title.setPrefWidth(400);
		pane.getChildren().add(title);

		search = new Button("Search for Author");
		search.setStyle("-fx-base: blue");
		pane.getChildren().add(search);
		search.setOnAction(this);

		tt = new Button("Search for Title");
		tt.setStyle("-fx-base: orange");
		pane.getChildren().add(tt);
		tt.setOnAction(this);

		clear = new Button("Clear");
		clear.setStyle("-fx-base: black");
		pane.getChildren().add(clear);
		clear.setOnAction(this);

		done = new Button("Done");
		done.setStyle("-fx-base: red");
		pane.getChildren().add(done);
		done.setOnAction(this);

		ta = new TextArea();
		ta.setPrefWidth(700);
		pane.getChildren().add(ta);

	}

	public void toFront() {
		myStage.toFront();
	}

	public boolean isShowing() {
		return myStage.isShowing();
	}

	@Override
	public void handle(ActionEvent event) {
		String ttle;
		ttle = title.getText();
		Button button = (Button) (event.getSource());

		if (button == search) {
			List<Book> authors = h.findBookByAuthor(ttle);

			if (authors != null) {
				for (Book b : authors) {
					ta.appendText("Title: " + b.getBookTitle() + " Author: " + b.getAuthor() + " Book Type: "
							+ b.getBookType() + "\n");
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Author Not Found");
				alert.show();
			}
		}

		if (button == tt) {
			List<Book> titles = h.keywordBooks(ttle);
			if (titles != null) {
				for (Book b : titles) {
					ta.appendText("Title: " + b.getBookTitle() + " Author: " + b.getAuthor() + " Book Type: "
							+ b.getBookType() + "\n");
				}
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Title Not Found");
				alert.show();
			}

		}

		if (button == clear) {
			title.clear();
			ta.clear();
		}

		if (button == done) {
			myStage.close();
		}
	}

	public void show() {

		myStage.show();
	}

}
