package controleEstoque;

public class Produto {

    private String codigo;
    private String nome;
    private double precoUnitario;
    private int quantidadeDisponivel;
    
    
    public Produto(String codigo, String nome, double precoUnitario, int quantidadeDisponivel) {
        this.codigo = codigo;
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }


    public String getCodigo() {
        return codigo;
    }


    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public double getPrecoUnitario() {
        return precoUnitario;
    }


    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }


    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }


    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }


    public void apresentar() {
        System.out.println(
            "Código: " + codigo + 
            " | Nome: " + nome + 
            " | Preço: R$" + String.format("%.2f", precoUnitario) + 
            " | Estoque: " + quantidadeDisponivel
        );
    }
}