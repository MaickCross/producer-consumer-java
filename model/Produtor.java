/*******************************************************************************************************
* Autor: Maick Vieira Alves
* Matricula: 202310196
* Inicio: 25/05/2025
* Ultima alteracao: 17/06/2025 
* Nome: Produtor
* Descricao: Essa classe gerencia o objeto consumidor e seu acesso ao Buffer
********************************************************************************************************/
package model;

import controller.TelaPrincipalController;
import javafx.scene.control.Slider;

public class Produtor extends Thread{

    //controller
    private TelaPrincipalController controller = new TelaPrincipalController();

    //slider de velocidade do Produtor
    private Slider sliderProdutor;

    //variavel para conferir se a thread esta pausada ou nao
    private boolean pausado = false;

    //variavel para resetar a thread
    public boolean reset = false;

    /**************************************************************
   * Metodo: Construtor da Classe Produtor
   * Funcao: inicializar o objeto Produtor
   * Parametros: controller, Slider
   * Retorno: void
   ***************************************************************/
    public Produtor(TelaPrincipalController controller, Slider sliderProdutor){

        this.controller = controller;
        this.sliderProdutor = sliderProdutor;
    }

    /**************************************************************
   * Metodo: run
   * Funcao: metodo run que inicia a thread
   * Parametros: nulo
   * Retorno: void
   ***************************************************************/
    @Override
    public void run() {
        while (true) { 
    
          try {

            // pausa a thread se necessario
          while (getPausado()) {
            Thread.sleep(100);
            if (getReset())  break;
          }
            //confere se ha espaço no Buffer para produzir
            TelaPrincipalController.vazio.acquire();

            //confere se eh possivel acessar a regiao critica
            TelaPrincipalController.mutex.acquire();

            //produz item
            controller.ProduzirItem();
            TelaPrincipalController.mutex.release();

            TelaPrincipalController.cheio.release();

            sleep(1000); // responsavel pela animacao
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
      
      
    //metodos

    /**************************************************************
   * Metodo: sleepThread
   * Funcao: Responsavel pelo controle de velocidade, coloca a thread para dormir por x tempo
   * Parametros: double velocidade(slider)
   * Retorno: void
   ***************************************************************/
  public void sleepThread(double velocidade) {

    try {
      Thread.sleep((long) (velocidade * 10));
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  //getters e setters

  /****************************************************************
  * Metodo: getPausado
  * Funcao: retorna o estado de pausa da thread
  * Parametros: nulo
  * Retorno: boolean 
  *************************************************************** */
  public boolean getPausado() {
    return pausado;
  }  

  /****************************************************************
  * Metodo: setPausado
  * Funcao: define o estado de pausa da thread
  * Parametros: boolean 
  * Retorno: void
  *************************************************************** */
  public void setPausado(boolean pausado) {
    this.pausado = pausado;
  }

  /****************************************************************
  * Metodo: getReset
  * Funcao: retorna o flag de reset da thread
  * Parametros: nulo
  * Retorno: boolean
  *************************************************************** */
  public boolean getReset() {
    return reset;
  }

  /****************************************************************
  * Metodo: setReset
  * Funcao: define o flag de reset da thread
  * Parametros: boolean 
  * Retorno: void
  *************************************************************** */
  public void setReset(boolean reset) {
    this.reset = reset;
  }

    
}
