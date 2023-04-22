package solucao;
/* ***************************************************************
* Autor............: Rafaela Pereira Santos
* Matricula........: 202110838
* Inicio...........: 13/04/2023
* Ultima alteracao.: 15/04/2023
* Nome.............: EstritaAlternancia
* Funcao...........: Utiliza a solucao de Estrita Alternancia para
* resolver o problema da Regiao Critica
*************************************************************** */
public class EstritaAlternancia extends Solucao {

  /* ***************************************************************
  * - 'vez' : int, usado para controlar o acesso do processo
  *************************************************************** */
  private int vez;

  /* ***************************************************************
  * - Nome do Constutor: EstritaAlternacia
  * - Funcao: Iniciliza 'vez' com o valor 0
  * - Parametros: Nao possui parametros
  * - Retorno:  Nao possui retorno
  * - Explicacao: Quando um objeto eh criado, ele ja possuir zero 
  * atribuido a vez
  *************************************************************** */
  public EstritaAlternancia () {
    this.vez = 0;
  }


  /* ***************************************************************
  * - Metodo: entraNoTunel
  * - Funcao: Controla o acesso de um processo a Regiao Critica por
  * meio do mecanismo de Estrita Alternancia
  * - Parametros: 'processID', int, que representa o ID do Processo
  * - Retorno: Nao possui retorno
  *************************************************************** */
  @Override
  public void entraNoTunel(int processID) {
    // -- Enquanto 'vez' for diferente do 'processID' que esta tentando entrar, o loop continuara sendo executado -- //
    while (vez != processID) {
      // -- Instrucao utilizada apenas para evitar que o loop while execute um loop infinito sem minimizar o uso de recursos -- //
      System.out.println("");
    } // fim do while
    // -- Quando a condicao do while for satisfeita, ou seja, quando 'vez' eh igual ao 'processID' que esta tentando entrar -- //
    // -- o processo pode finalmente entrar na Regiao Critica e executar as tarefas que deseja realizar -- //
  } // fim de entraNoTunel


  /* ***************************************************************
  * - Metodo: saiDoTunel
  * - Funcao: Eh chamado quando um Processo deseja sair da Regiao Critica
  * - Parametros: 'processID', int, que representa o ID do Processo
  * - Retorno: Nao possui retorno
  *************************************************************** */
  @Override
  public void saiDoTunel(int processID) {
    // -- Atualiza a variavel 'vez' para permitir que o proximo Processo na fila entre na Regiao Critica -- //
    vez = processID + 1;
  } // fim de saiDoTunel

} // fim da classe
