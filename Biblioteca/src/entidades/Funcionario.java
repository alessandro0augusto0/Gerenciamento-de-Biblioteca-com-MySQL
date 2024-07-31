package entidades;

public class Funcionario extends Pessoa {

    private String funcao;
    private double salario;

    public Funcionario(){

    }

    public Funcionario(String nome, String cpf, String funcao, double salario){
        super(nome, cpf);
        this.funcao = funcao;
        this.salario = salario;
    }

    public String getFuncao(){
        return funcao;
    }

    public void setFuncao(String funcao){
        this.funcao = funcao;
    }

    public double getSalario(){
        return salario;
    }

    public void setSalario(double salario){
        this.salario = salario;
    }

    @Override
	public String toString() {
		return "Funcionario:\n"
				+ "Nome: " + getNome()
				+ " CPF: " + getCpf()
				+ " Funcao: " + getFuncao()
				+ " Salario: " + getSalario();
	}
    
}
