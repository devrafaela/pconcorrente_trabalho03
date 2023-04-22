import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/* ***************************************************************
* Autor............: Rafaela Pereira Santos
* Matricula........: 202110838
* Inicio...........: 13/04/2023
* Ultima alteracao.: 21/04/2023
* Nome.............: Principal
* Funcao...........: Inicializa o Program
*************************************************************** */
public class Principal extends Application{

  @Override
  public void start(Stage palco) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("/Tela.fxml"));
    palco.setTitle("Estacao de Trem");
    palco.getIcons().add(new Image("view/icon.png"));
    palco.setScene(new Scene(root));
    palco.setResizable(false);
    palco.centerOnScreen();
    palco.setFullScreenExitHint("");
    palco.setOnCloseRequest(t -> {
      Platform.exit();
      System.exit(0);
    });
    palco.show();
    TelaController telacontroller = new TelaController();
  } // fim de start

  public static void main(String[] args) {
    launch(args);
  } // fim de main

} // fim da classe
