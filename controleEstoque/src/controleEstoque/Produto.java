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

    public String getNome() {
        return nome;
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
    
    public double calcularPrecoComDesconto(int quantidade) {
        if (quantidade >= 10) {
            return precoUnitario * 0.9;
        }
        return precoUnitario;
    }
    
    public void apresentar() {
        System.out.printf(
            "Código: %s | Nome: %s | Preço: R$%.2f | Estoque: %d%n",
            codigo, nome, precoUnitario, quantidadeDisponivel
        );
    }
}