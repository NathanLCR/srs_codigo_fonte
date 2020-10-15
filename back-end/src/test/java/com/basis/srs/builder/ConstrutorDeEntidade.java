package com.basis.srs.builder;

import java.text.ParseException;
import java.util.Collection;

<<<<<<< HEAD

=======
>>>>>>> fe8655038ed0757a48b2a5cecc43e6a54668beb1
public abstract class ConstrutorDeEntidade<E> {

    private CustomizacaoEntidade<E> customizacao;

    public E construir() throws ParseException {
        final E entidade = construirEntidade();
        if (isCustomizado()) {
            customizacao.executar(entidade);
        }
        return persistir(entidade);
    }

<<<<<<< HEAD
    public ConstrutorDeEntidade<E> customizar(CustomizacaoEntidade<E> customizacao) {
=======
    public com.basis.srs.builder.ConstrutorDeEntidade<E> customizar(CustomizacaoEntidade<E> customizacao) {
>>>>>>> fe8655038ed0757a48b2a5cecc43e6a54668beb1
        this.customizacao = customizacao;
        return this;
    }

<<<<<<< HEAD

    protected abstract E construirEntidade() throws ParseException;

    protected abstract E persistir(E entidade);

    protected abstract Collection<E> obterTodos();

    protected abstract E obterPorId(Integer id);
=======
    protected abstract E construirEntidade() throws ParseException;

    public abstract E persistir(E entidade);

    protected abstract Collection<E> obterTodos();

    protected abstract E obterPorId(Long id);
>>>>>>> fe8655038ed0757a48b2a5cecc43e6a54668beb1

    public boolean isCustomizado() {
        return this.customizacao != null;
    }

    public void setCustomizacao(CustomizacaoEntidade<E> customizacao) {
        this.customizacao = customizacao;
    }
<<<<<<< HEAD


=======
>>>>>>> fe8655038ed0757a48b2a5cecc43e6a54668beb1
}
