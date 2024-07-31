package entidades;

public class Usuario extends Pessoa{
    private String endereco;
    private String telefone;

    public Usuario(){

    }

    public Usuario(String nome, String cpf, String endereco, String telefone){
        super(nome, cpf);
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public String getEndereco(){
        return endereco;
    }

    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    @Override
	public String toString() {
		return "Usuario:\n"
				+ "Nome: " + getNome()
				+ " CPF: " + getCpf()
				+ " Endereco: " + getEndereco()
				+ " Telefone: " + getTelefone();
	}

}
