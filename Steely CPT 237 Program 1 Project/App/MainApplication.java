//FILE: MainApplication.java
//PROG: Marshall Chase Steely
//PURP: Launches the program. Loads in the csv file.

package edu.tridenttech.CPT237.Steely.Library.App;

import edu.tridenttech.CPT237.Steely.Library.Model.HomeLibrary;
import edu.tridenttech.CPT237.Steely.Library.View.LibraryWindow;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		HomeLibrary h = HomeLibrary.getInstance();
		h.loadLibrary("C:/Users/Marshall/workspace/Steely CPT 237 Final Project/Books.csv");

		LibraryWindow ui = new LibraryWindow(primaryStage);

		ui.show();

	}

	public static void main(String[] args) {
		Application.launch(args);

	}

}
