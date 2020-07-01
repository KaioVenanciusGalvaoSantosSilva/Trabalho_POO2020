package Produto;

import javax.validation.constraints.NotBlank;

public class EntityProduto implements Comparable<EntityProduto> {

	private int idProduto = 0;
	private String nomeProduto = "";
	private double valorUnitario;
	private String tipoProduto = "";
	private String principioAtivo = "";

	public EntityProduto() {
	}

	public EntityProduto(String nomeProduto) {
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

	public double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	@NotBlank(message = "Insira o tipo de Produto ")
	public String getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(String tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	@NotBlank(message = "Insira o Princio Ativo ")
	public String getPrincipioAtivo() {
		return principioAtivo;
	}

	public void setPrincipioAtivo(String principioAtivo) {
		this.principioAtivo = principioAtivo;
	}

	@Override
	public int compareTo(EntityProduto produto) {
		return getNomeProduto().compareTo(produto.getNomeProduto());
	}

	@Override
	public String toString() {
		return nomeProduto;
	}
}