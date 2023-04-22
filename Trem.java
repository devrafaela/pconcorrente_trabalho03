
import javafx.scene.control.Slider;/* ***************************************************************
* Autor............: Rafaela Pereira Santos
* Matricula........: 202110838
* Inicio...........: 13/04/2023
* Ultima alteracao.: 21/04/2023
* Nome.............: Trem
* Funcao...........: Executa o Trem para percorrer os Trilhos
*************************************************************** */
public abstract class Trem extends Thread {

  /* ********************************************************************************
  * - 'processID' :  int que identifica o processo associado a instancia da classe
  * - 'nomeDoTrem' :  String que contem o nome do trem
  * - 'rota' :  objeto 'LinhaDoTrem' que contem a rota associada a essa instancia
  * - 'SLDR' :  objeto 'Slider' que representa o controle de velocidade na GUI
  * - 'DIRECAO' :  booleano que representa o sentido de um trem (True: ->, False: <-)
  * - 'countTrilho' :  int que representa o numero de trilhos percorridos pelo trem
  * - 'porcentagem' :  double, entre 0 e 1, que representa a porcentagem da rota que
  * foi percorrida pelo trem
  ******************************************************************************** */
  private int processID;          
  private String nomeDoTrem;     
  protected LinhaDoTrem rota;     
  private final Slider SLDR;      
  private final boolean DIRECAO;    
  protected int countTrilho;       
  protected double porcentagem;


  /* ********************************************************************************************
  * Nome do Constutor: Trem
  * Funcao: Cria um objeto do tipo 'Trem'
  * Parametros: 'pID' (int, eh o ID do processo do trem), 'slider' (do tipo 'Slider', sera usa-
  * do para controlar a velocidade do trem na rota), 'direcao' (indica o sentido do trem na rota),
  * 'rota' (objeto 'LinhaDotrem', representa a rota que o trem ira percorrer)
  * Retorno: Nao possui retorno
  * Explicacao: Apenas inicializa os atributos de 'Trem' com os valores correspondentes
  ******************************************************************************************** */
  public Trem (int pID, String nome, Slider slider, boolean direcao, LinhaDoTrem rota) {
    this.nomeDoTrem = nome;
    this.SLDR = slider;
    this.rota = rota;
    this.DIRECAO = direcao;
    this.processID = pID;
  } // fim do Trem

  /* ******************************************************************************
  * - Metodo: movimento
  * - Funcao: Contem um metodo chamado 'moveTrem' que eh utilizado para movimentar
  * um trem ao longo de uma rota denifida
  * - Parametros: Nao possui parametros
  * - Retorno: Nao possui retorno
  ****************************************************************************** */
  protected abstract void movimento (boolean direcao);
    /* ****************************
    *     porcentagem (dados)     *
    * 0.00 a 0.19 : trilho comum  *
    * 0.20 a 0.29 : tunel 1       *
    * 0.30 a 0.69 : trilho comum  *
    * 0.70 a 0.79 : tunel 2       *
    * 0.80 a 1.00 : trilho comum  *
    * *************************** */

  // -- double, define a contagem maxima de trilhos na GUI -- //
  double countTrilhoMax = 0;

  /* ***************************************************************
  * - Metodo: moveTrem
  * - Funcao: 
  * - Parametros: Nao possui parametros
  * - Retorno: 
  *************************************************************** */
  public boolean moveTrem() {

    // -- Verifica se a 'rota' do Trem é nula, caso seja, o metodo imprime no terminal uma mensagem que o trem ja chegou na estacao e retorna false -- //
    if (rota == null) {
      System.out.println("- " + nomeDoTrem + " chegou na Estacao!");
      // System.out.println(countTrilhoMax);
      return false; 
      // -- Caso nao tenha chegado no final da rota: -- // 
    } else { 
      // -- Eh verificado se o Trem ja percorreu todo o comprimento da rota atual -- //
      if (countTrilho < rota.getComprimento()) {
        // -- Enquanto 'countTrilho' for menor que 'rota.getComprimento()', 'countTrilho' e 'countTrilhoMax' sao incrementados em 1 -- //
        countTrilho++;
        countTrilhoMax++;
        // -- Em seguida, o percentual do percurso eh calculado abaixo -- //
        porcentagem = countTrilhoMax / 315;
        // -- Finalmente, a funcao 'movimento()' eh chamada com 'DIRECAO' atual do Trem para atualizar a posicao do Trem na tela -- //
        movimento(DIRECAO);

      // -- Caso nao: -- //
      } else { 
        // -- Eh obtido a proxima linha na rota do trem -- //
        LinhaDoTrem linha = rota.proximaLinha(DIRECAO);       
        // -- TRECHO DO   T U N E L   ABAIXO: -- //
        // -- Eh verificado se a proxima linha eh um Tunel, verificando se eh uma instancia de Tunel -- //
        if (linha instanceof Tunel) {
          // -- Se a proxima linha for um Tunel, o Trem entra no tunel chamando o metodo 'entraNoTunel(processID)' da classe Tunel -- //
          // Ocorre um casting, ou seja, uma conversao do objeto referenciado por 'linha' para o tipo 'Tunel' -- //
          Tunel tunel = (Tunel) linha;   
          tunel.getSolucao().entraNoTunel(processID);
          System.out.println("[Entrou no Tunel]");

        // -- Caso nao: -- ///
        } else { 
          // -- Se a linha atual for um Tunel, o Trem saira do Tunel, chamando o metodo 'saiDoTunel(processID)' da classe Tunel -- //
          if (rota instanceof Tunel) {
            // -- Eh feito um downcast do objeto 'rota' para a classe 'Tunel' -- //
            ((Tunel) rota).getSolucao().saiDoTunel(processID);
          System.out.println("[Saiu do Tunel]");
          } // fim do if
        } 
        // -- TRECHO DO   T U N E L   ACIMA: -- //   
        // -- Depois disso, abaixo eh atualizado a linha atual do Trem para a proxima linha, e redefine a contade do trilho para 0 -- //
        rota = linha;
        countTrilho = 0;        
      }
    } 
    // -- Eh retornado true, inidicando que o trem pode continuar se movendo -- //
    return true;
  } // fim do moveTrem
  

  /* *********************************************************************
  * - Metodo: run
  * - Funcao: Executa em loop para mover o objeto 'Trem' em um ambiente. O
  * loop e repetido enquanto a funcao 'moveTrem()' retornar true
  * - Parametros: Nao possui parametros
  * - Retorno: Nao possui valor de retorno
  ********************************************************************* */
  public void run () {
    // -- Enquanto o metodo anterior, 'moveTrem()' for True: -- //
    while (moveTrem()) {
      // -- try usado para capturar qualquer execao que possa ser lancada no codigo dentro do bloco -- //
      try {
        // -- O metodo 'sleep()' eh usado para pausar a execucao do Thread por um determinado tempo antes de continuar o loop novamente -- //
        // -- A duracao da pausa eh determinada pelo valor do 'Slider' 'SLDR.getValue()', que permite ao usuario controlar a velocidade do trem -- //
        // -- Nesse caso o 'Slider' eh convertido em int e usado para calcular a duracao da pausa em milisseguntos -- //
        sleep(1000/(int) SLDR.getValue()); // -- Pontanto: Quanto MAIOR o valor do 'Slider', MAIS RAPIDO o Trem se movera, e MENOS TEMPO o Thread aguardara antes de continuar o loop -- //
      // -- catch eh usado para capturar excecoes lancadas dentro do try -- //
      // -- Nesse caso, se ocorrer a excecao 'InterruptedException', sera tratada dentro do bloco -- //
      } catch (InterruptedException ie) {
        // -- A Pilha de Chamadas sera impressa -- //
        ie.printStackTrace();
      } // fim do try-catch
    } // fim do while
  } // fim de run

  public boolean getDirecao () {
    return DIRECAO;
  } // fim de getDirecao

} // fim da classe
