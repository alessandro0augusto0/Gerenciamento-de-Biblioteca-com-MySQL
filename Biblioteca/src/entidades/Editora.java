package entidades;

public class Editora {
    private String codigo;
    private String nome;
    private String contato;

    public Editora(){

        }

    public Editora(String codigo, String nome, String contato){
        this.codigo = codigo;
        this.nome = nome;
        this.contato = contato;
    }

    public String getCodigo(){
        return codigo;
    }

    public void setCodigo(String codigo){
        this.codigo = codigo;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getContato(){
        return contato;
    }

    public void setContato(String contato){
        this.contato = contato;
    }
    
}
