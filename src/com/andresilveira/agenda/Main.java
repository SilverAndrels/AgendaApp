package com.andresilveira.agenda;

import java.io.IOException;
import java.time.LocalDate;

import com.andresilveira.agenda.gui.PessoaController;
import com.andresilveira.agenda.model.Pessoa;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	private Stage primaryStage;
	private BorderPane principal;

	private ObservableList<Pessoa> dados = FXCollections.observableArrayList();

	public Main() {
		dados.add(new Pessoa("André", "Silveira", LocalDate.of(1999, 1, 19)));
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("AgendApp");

		initMainStage();
		carregarTela();
	}

	private void carregarTela() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("gui/principal.fxml"));
			AnchorPane pessoasView = (AnchorPane) loader.load();

			this.principal.setCenter(pessoasView);

			PessoaController controller = loader.getController();
			controller.setMain(this);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private void initMainStage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("gui/container.fxml"));
			this.principal = (BorderPane) loader.load();

			Scene cena = new Scene(principal);

			this.primaryStage.setScene(cena);
			this.primaryStage.show();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

	public static void main(String[] args) {
		launch(args);
	}

	public ObservableList<Pessoa> getDados() {
		return this.dados;
	}
}
