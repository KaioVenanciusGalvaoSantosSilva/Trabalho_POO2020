package Estoque;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;

public class EntityEstoque implements Comparable<EntityEstoque> {

	private int idProduto = 0;
	private String nomeProduto = "";
	private String principioAtivo = "";
	private double valorUnitario;
	private int quantidadeAtual;
	private String nomeFornecedor = "";

	private LocalDate dataValidade = LocalDate.of(2100, 1, 1);

	public EntityEstoque() {
	}

	public EntityEstoque(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public int getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}

	@NotBlank(message = "Insira o Produto ")
	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	@NotBlank(message = "Insira o Fornecedor ")
	public String getPrincipioAtivo() {
		return principioAtivo;
	}

	public void setPrincipioAtivo(String principioAtivo) {
		this.principioAtivo = principioAtivo;
	}
	
	@NotBlank(message = "Insira o e-mail ")
	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	
	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public int getQuantidadeAtual() {
		return quantidadeAtual;
	}

	public void setQuantidadeAtual(int quantidadeAtual) {
		this.quantidadeAtual = quantidadeAtual;
	}

	
	@FutureOrPresent(message = "esta data deve estar no presente ou futuro")
	public LocalDate getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}

	@Override
	public int compareTo(EntityEstoque entrada) {
		return getNomeProduto().compareTo(entrada.getNomeProduto());
	}

	@Override
	public String toString() {
		return nomeProduto;
	}
}