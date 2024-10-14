package dad.enviarEmail;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class enviarEmailApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		enviarEmailController enviarEmailController = new enviarEmailController();
		
		Stage enviarEmailStage = new Stage();
		enviarEmailStage.setTitle("Enviar email");
		enviarEmailStage.setScene(new Scene(enviarEmailController.getRoot()));
		enviarEmailStage.show();
		
	}
	
}
