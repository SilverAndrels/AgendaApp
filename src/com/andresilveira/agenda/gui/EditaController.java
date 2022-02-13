package com.andresilveira.agenda.gui;

import com.andresilveira.agenda.model.Pessoa;
import com.andresilveira.agenda.util.DateUtil;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditaController {

	@FXML
	private TextField nome;
	@FXML
	private TextField sobrenome;
	@FXML
	private TextField dataNascimento;

	private Stage dialogStage;
	private Pessoa pessoa;
	private boolean okClicked = false;

	@FXML
	private void initialize() {

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;

		nome.setText(pessoa.getNome());
		sobrenome.setText(pessoa.getSobrenome());
		dataNascimento.setText(DateUtil.format(pessoa.getDataNascimento()));
	}

	public boolean isClicked() {
		return this.okClicked;
	}

	@FXML
	public void handleCancelar() {
		this.dialogStage.close();
	}

	@FXML
	public void handleSalvar() {
		if (isValidaEntrada()) {
			pessoa.setNome(nome.getText());
			pessoa.setSobrenome(sobrenome.getText());
			pessoa.setDataNascimento(DateUtil.parse(dataNascimento.getText()));

			okClicked = true;
			dialogStage.close();
		}
	}

	private boolean isValidaEntrada() {
		String erros = "";

		if (nome.getText() == null || nome.getText().isEmpty() || nome.getText().length() <= 3) {
			erros += "Nome inválido\n";
		}

		if (sobrenome.getText() == null || sobrenome.getText().isEmpty() || sobrenome.getText().length() <= 3) {
			erros += "Sobrenome inválido\n";
		}

		if (dataNascimento.getText() == null || dataNascimento.getText().length() == 0) {
			erros += "Aniversário inválido\n";
		} else {
			if (!DateUtil.validDate(dataNascimento.getText())) {
				erros += "Aniversário Inválido. Use o formato dd/mm/yyyy!\n";
			}
		}
		if (erros.length() == 0) {
			return true;
		} else {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Campos Inválidos");
			alert.setHeaderText("Por favor, corrija as informações");
			alert.setContentText(erros);
			alert.showAndWait();
			return false;
		}
	}
}
