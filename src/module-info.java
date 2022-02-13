module AgendaApp {
	requires javafx.controls;
	
	opens com.andresilveira.agenda to javafx.graphics, javafx.fxml;
	requires javafx.fxml;
	requires javafx.graphics;
	opens com.andresilveira.agenda.gui to javafx.fxml, javafx.graphics;
}
