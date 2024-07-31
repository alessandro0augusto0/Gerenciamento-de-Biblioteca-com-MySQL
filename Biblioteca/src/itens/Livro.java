package itens;

import interfaces.Emprestavel;

public class Livro extends ItemDeBiblioteca implements Emprestavel {

    private String edicao;
    private String genero;
    private int anoDePublicacao;
    private int codigoEditora;

    public Livro() {

    }

    public Livro(String codigo, String titulo, String status, String edicao, String genero, int anoDePublicacao,
            int codigoEditora) {
        super(codigo, titulo, status);
        this.edicao = edicao;
        this.genero = genero;
        this.anoDePublicacao = anoDePublicacao;
        this.codigoEditora = codigoEditora;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnoDePublicacao() {
        return anoDePublicacao;
    }

    public void setAnoDePublicacao(int anoDePublicacao) {
        this.anoDePublicacao = anoDePublicacao;
    }

    public int getCodigoEditora() {
        return codigoEditora;
    }

    public void setCodigoEditora(int codigoEditora) {
        this.codigoEditora = codigoEditora;
    }

    @Override
    public void emprestar() {
        setStatus("emprestado");
    }

    @Override
    public void devolver() {
        setStatus("disponivel");
    }

    @Override
    public String toString() {
        return "Livro:\n"
                + "Codigo: " + getCodigo()
                + " Titulo: " + getTitulo()
                + " Status: " + getStatus()
                + " Edicao: " + getEdicao()
                + " Genero: " + getGenero()
                + " Ano de publicacao: " + getAnoDePublicacao()
                + " Codigo da editora: " + getCodigoEditora();
    }

}
