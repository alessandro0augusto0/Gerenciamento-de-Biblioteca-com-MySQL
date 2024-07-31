package itens;

public abstract class ItemDeBiblioteca {
    
    private String codigo;
    private String titulo;
    private String status;

    public ItemDeBiblioteca(){

    }

    public ItemDeBiblioteca(String codigo, String titulo, String status){
        this.codigo = codigo;
        this.titulo = titulo;
        this.status = status;
    }

    public String getCodigo(){
        return codigo;
    }

    public void setCodigo(String codigo){
        this.codigo = codigo;
    }

    public String getTitulo(){
        return titulo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }
}
