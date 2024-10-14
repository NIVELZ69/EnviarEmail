package dad.enviarEmail;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class enviarEmailController implements Initializable {

	private Alert errorAlert;
	private Alert enviadoAlert;

	@FXML
	private TextField asuntoTextField;

	@FXML
	private Button cerrarButton;

	@FXML
	private CheckBox conexionCheckbox;

	@FXML
	private TextField contraseñaRemitenteTextField;

	@FXML
	private TextField emailDestinatarioTextField;

	@FXML
	private TextField emailRemitenteTextField;

	@FXML
	private Button enviarButton;

	@FXML
	private TextArea mensajeTextArea;

	@FXML
	private TextField puertoTextField;

	@FXML
	private BorderPane root;

	@FXML
	private TextField servidorTextField;

	@FXML
	private Button vaciarButton;

	public void setRoot(BorderPane root) {
		this.root = root;
	}

	public BorderPane getRoot() {
		return root;
	}

	@FXML
	void onCerrarAction(ActionEvent event) {
		System.exit(0);
	}

	@FXML
	void onEnviarAction(ActionEvent event) {
		String servidor = servidorTextField.getText();
		String puerto = puertoTextField.getText();
		boolean SLL = conexionCheckbox.isSelected();
		String eRemitente = emailRemitenteTextField.getText();
		String cRemitente = contraseñaRemitenteTextField.getText();
		String destinatario = emailDestinatarioTextField.getText();
		String asunto = asuntoTextField.getText();
		String mensaje = mensajeTextArea.getText();

		try {
			Email email = new SimpleEmail();
			email.setHostName(servidor);
			email.setSmtpPort(Integer.parseInt(puerto));
			email.setAuthenticator(new DefaultAuthenticator(eRemitente, cRemitente));
			email.setSSLOnConnect(SLL);

			email.setFrom(eRemitente);
			email.setSubject(asunto);
			email.setMsg(mensaje);
			email.addTo(destinatario);
			email.send();

			enviadoAlert = new Alert(Alert.AlertType.INFORMATION);
			enviadoAlert.setTitle("Mensaje enviado");
			enviadoAlert.setHeaderText("Mensaje enviado con éxito a " + destinatario + ".");
			enviadoAlert.show();
		} catch (Exception e) {
			errorAlert = new Alert(Alert.AlertType.ERROR);
			errorAlert.setTitle("Error");
			errorAlert.setHeaderText("No se pudo enviar el email");
			errorAlert.setContentText(e.getMessage());
			errorAlert.show();
		}

	}

	@FXML
	void onVaciarAction(ActionEvent event) {
		servidorTextField.setText("");
		puertoTextField.setText("");
		conexionCheckbox.setSelected(false);
		emailRemitenteTextField.setText("");
		contraseñaRemitenteTextField.setText("");
		emailDestinatarioTextField.setText("");
		asuntoTextField.setText("");
		mensajeTextArea.setText("");
	}

	public enviarEmailController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/enviarEmailView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		// bindings

	}

}
