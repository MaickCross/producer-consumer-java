
/*******************************************************************************************************
* Autor: Maick Vieira Alves
* Matricula: 202310196
* Inicio: 25/05/2025
* Ultima alteracao: 17/06/2025 
* Nome: Principal
* Descricao: Este projeto eh uma implementacao da solucao para o Problema classico Produtor Consumidor. O projeto foi feito 
* em java 1.8 utilizando JavaFX e com a utilizacao de Semaforos para resolver a concorrencia
********************************************************************************************************/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


import controller.TelaPrincipalController;
import controller.TelaInicialController;


public class Principal extends Application {

  /**********************************************************************
   * Metodo: start
   * Funcao: Inicializa a aplicacao JavaFX
   * Parametros: Stage
   * Retorno: void
   **********************************************************************/
  @Override
  public void start(Stage janela) throws Exception {

    try {
      Parent raiz = FXMLLoader.load(getClass().getResource("view/telaInicial.fxml"));// Carrega o arquivo FXML da tela
                                                                                     // inicial
      Scene cena = new Scene(raiz);
      janela.setTitle("Dark ritual"); // Define o nome do programa
      janela.setResizable(false); // deixa o tamanho da tela fixo

      janela.setScene(cena);
      janela.show();
      janela.setOnCloseRequest(e -> {
        System.exit(0);
      }); // encerra o programa e as threads
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**************************************************************
   * Metodo: main
   * Funcao: lancar o programa
   * Parametros: args
   * Retorno: void
   ***************************************************************/
  public static void main(String[] args) {
    launch(args); // Inicia a aplicacao JavaFX
  }
}