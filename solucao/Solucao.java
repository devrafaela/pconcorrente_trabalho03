package solucao;
/* ***************************************************************
* Autor............: Rafaela Pereira Santos
* Matricula........: 202110838
* Inicio...........: 13/04/2023
* Ultima alteracao.: 15/04/2023
* Nome.............: Solucao
* Funcao...........: Serve de heranca para as solucoes de geren-
* ciamento de Regioes Criticas
*************************************************************** */
public abstract class Solucao {

  /* ***************************************************************
  * - Metodo: entraNoTunel
  * - Funcao: Responsavel por indicar que um Processo esta dentro de
  * uma Regiao Critica
  * - Parametros: 'pID', int que representa o ID do Processo
  * - Retorno: Nao possui retorno
  *************************************************************** */
  public abstract void entraNoTunel(int pID);


  /* ***************************************************************
  * - Metodo: saiDoTunel
  * - Funcao: Responsavel por indicar que um Processo esta saindo de
  * uma Regiao Critica
  * - Parametros: 'pID', int que representa o ID do Processo
  * - Retorno: Nao possui retorno
  *************************************************************** */
  public abstract void saiDoTunel(int pID);

} // fim da classe
