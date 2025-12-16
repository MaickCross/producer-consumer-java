/*******************************************************************************************************
* Autor: Maick Vieira Alves
* Matricula: 202310196
* Inicio: 25/05/2025
* Ultima alteracao: 17/06/2025 
* Nome: Buffer
* Descricao: responsavel por gerenciar o Buffer de itens e a fila de buffers
********************************************************************************************************/
package Utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Buffer {

  // buffer a ser manipulado pelo produtor/consumidor
  private int[] Buffer;

  // tamanho do Buffer
  private int tamanhoBuffer;

  // Fila que armazena a ordem de buffers que o consumidor deve acessar em sequencia(sincronizado com a geracao de itens)
  private Queue<Integer> filaBuffer;


  /**********************************************************************
   * Metodo: construtor da classe Buffer
   * Funcao: Inicializa os Arrays e Fila da classe buffer
   * Parametros: nulo
   * Retorno: void
   **********************************************************************/
  public Buffer(int N) {

    // inicia o array Buffer
    Buffer = new int[N];
    this.tamanhoBuffer = N;
    filaBuffer = new LinkedList<>();

  }

  // metodos

  /**********************************************************************
   * Metodo: inicializarVazioBuffer
   * Funcao: define todos os indices do Buffer para vazio(0)
   * Parametros: nulo
   * Retorno: void
   **********************************************************************/
  public void inicializarVazioBuffer() {

    for (int i = 0; i < this.tamanhoBuffer; i++) {

      Buffer[i] = 0;
    }
  }

  /**********************************************************************
   * Metodo: resetarFilaBuffer
   * Funcao: reseta a fila do Buffer, remove todos os indices
   * Parametros: nulo
   * Retorno: void
   **********************************************************************/
  public void resetarFilaBuffer() {
    filaBuffer.clear();
  }

  /**********************************************************************
   * Metodo: ArmazenarItemIDBuffer
   * Funcao: armazena o ID do item que foi inserido no buffer
   * Parametros: int posicao do buffer, int ID do item
   * Retorno: void
   **********************************************************************/
  public void ArmazenarItemIDBuffer(int posicao, int itemID) {

    Buffer[posicao] = itemID;
  }

  /**********************************************************************
   * Metodo: RetirarItemIDBuffer
   * Funcao: recebe e remove o ID do item retirado do buffer
   * Parametros: int 
   * Retorno: void
   **********************************************************************/
  public int RetirarItemIDBuffer(int posicao) {

    int ID_Item = Buffer[posicao];

    Buffer[posicao] = 0;

    return ID_Item;
  }

  /**********************************************************************
   * Metodo: InserirBufferAleatorio
   * Funcao: verifica quais as posicoes vazias do buffer e escolhe aleatoriamente para poder inserir 
   * Parametros: nulo
   * Retorno: void
   **********************************************************************/
  public int InserirBufferAleatorio() {

    ArrayList<Integer> posicoesLivres = new ArrayList<>();

    for (int i = 0; i < Buffer.length; i++) {

      if (Buffer[i] == 0) {

        posicoesLivres.add(i);
      }
    }

    Random rand = new Random();
    int indice_sorteado = rand.nextInt(posicoesLivres.size());
    int buffer_sorteado = posicoesLivres.get(indice_sorteado);

    return buffer_sorteado;
  }

  // metodos relacionados a fila do consumidor

    /**********************************************************************
   * Metodo: adicionarFilaBuffer
   * Funcao: armazena a ordem de buffers a serem acessados, segue a ordem de geracao do produtor
   * Parametros: int
   * Retorno: void
   **********************************************************************/
  public void adicionarFilaBuffer(int buffer) {

    this.filaBuffer.offer(buffer);
  }

  /**********************************************************************
   * Metodo: removerFilaBuffer
   * Funcao: recebe qual Buffer deve ser acessado em seguida
   * Parametros: nulo
   * Retorno: void
   **********************************************************************/
  public Integer removerFilaBuffer() {
    return this.filaBuffer.poll();
  }

}
