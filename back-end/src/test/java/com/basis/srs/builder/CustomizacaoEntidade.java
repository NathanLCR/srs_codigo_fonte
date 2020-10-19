package com.basis.srs.builder;

<<<<<<< HEAD

public interface CustomizacaoEntidade<E> {

=======
/**
 * Interface que define um contrato para permitir a custimização de uma entidade
 * no momento de sua contrução, para utilização em testes
 *
 * @param <E> Tipo da entidade a ser customizado
 */
public interface CustomizacaoEntidade<E> {

    /**
     * Executa a customização da entidade
     *
     * @param entidade a ser customizada
     */
>>>>>>> fe8655038ed0757a48b2a5cecc43e6a54668beb1
    void executar(E entidade);
}
