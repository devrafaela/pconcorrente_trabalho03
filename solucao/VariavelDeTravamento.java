package solucao;
/* ***************************************************************
* Autor............: Rafaela Pereira Santos
* Matricula........: 202110838
* Inicio...........: 15/04/2023
* Ultima alteracao.: 17/04/2023 
* Nome.............: VariavelDeTravamento
* Funcao...........: Eh uma implementacao da Solucao de Variavel
* de Travamento, visto em aula, que garante que apenas um processo
* (Trem) possa acessar a Regiao Critica (Tunel) de cada vez
*************************************************************** */
public class VariavelDeTravamento extends Solucao {

  /* ***************************************************************
  * - 'bloequeio' : int, servira como um mecanismo para garantir que
  * apenas um Trem (processo) possa acessar um Tunel (regiao critica)
  * de cada vez, evitando condicoes de corrida
  *************************************************************** */
  private int bloqueio;


  /* ***************************************************************
  * - Nome do Constutor: VariavelDeTravamento
  * - Funcao: Inicializa 'bloqueio' com o valor 0
  * - Parametros: Sem parametros
  * - Retorno: Nao possui retorno
  * - Explicacao: Eh responsavel por inicializar a Variavel de Trava-
  * mento 'bloqueio' com um valor padrao de 0
  *************************************************************** */
  public VariavelDeTravamento() {
    bloqueio = 0;
  }


  /* ***************************************************************
  * - Metodo: entraNoTunel
  * - Funcao: Verifica se a Regiao Critica esta sendo acessada por
  * outro processo e aguarda ate que ela esteja disponivel
  * - Parametros: 'processID' (int, que representa o ID do processo)
  * - Retorno: Nao possui retorno
  *************************************************************** */
  @Override
  public void entraNoTunel(int processID) {
    // -- Enquanto 'bloqueio' for diferente de 0, ou seja, enquanto a Regicao Critica estiver sendo acessada por outro processo: -- //
    while (bloqueio != 0) {
      // -- Instrucao utilizada apenas para evitar que o loop while execute um loop infinito sem minimizar o uso de recursos -- //
      System.out.printf("");
    } // fim do while
    // -- Define 'bloqueio' como 1, ou seja, isso significa que o Processo que executou esse metodo entrou na Regiao Critica e outstro processos que desejam entrar na mesma serao bloqueados, ate que seja liberado - -//
    bloqueio = 1;
  } // fim do entraNoTunel


  /* ***************************************************************
  * - Metodo: saiNoTunel
  * - Funcao: Libera a Regiao Critica para outros Processos, ou seja,
  * quando um processo nao precisa mais acessar a Regiao Critica, ele
  * chama esse metodo para indicar que esta saindo da Regiao Critica,
  * e liberar o bloqueio
  * - Parametros: 'processID', int, eh a indentificacao do processo
  * - Retorno: Nao possui retorno
  *************************************************************** */
  @Override
  public void saiDoTunel(int processID) {
    // -- Eh uma instrucao que define a variavel 'bloqueio' como 0, isso significa que o processo que executou esse metodo esta saindo da Regiao Critica -- //
    bloqueio = 0;
  } // fim de saiDoTunel
  
} // fim da classe
