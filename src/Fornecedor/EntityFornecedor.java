package Fornecedor;

import javax.validation.constraints.NotBlank;

public class EntityFornecedor implements Comparable<EntityFornecedor> {

	private int idFornecedor = 0;
	private String nomeFornecedor = "";
	private String CNPJFornecedor = "";
	private String emailFornecedor = "";
	private int telefoneFornecedor;
	private int CEPFornecedor;
	private String enderecoFornecedor = "";
	private int numeroEndereco;
	private String complementoEndereco = "";

	public EntityFornecedor() {
	}

	public EntityFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	public int getIdFornecedor() {
		return idFornecedor;
	}

	public void setIdFornecedor(int idFornecedor) {
		this.idFornecedor = idFornecedor;
	}

	@NotBlank(message = "Insira o Fornecedor ")
	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	@NotBlank(message = "Insira o CPF ")
	public String getCNPJFornecedor() {
		return CNPJFornecedor;
	}

	public void setCNPJFornecedor(String CNPJFornecedor) {
		this.CNPJFornecedor = CNPJFornecedor;
	}

	@NotBlank(message = "Insira o e-mail ")
	public String getEmailFornecedor() {
		return emailFornecedor;
	}

	public void setEmailFornecedor(String emailFornecedor) {
		this.emailFornecedor = emailFornecedor;
	}

	public int getTelefoneFornecedor() {
		return telefoneFornecedor;
	}

	public void setTelefoneFornecedor(int telefoneFornecedor) {
		this.telefoneFornecedor = telefoneFornecedor;
	}

	public int getCEPFornecedor() {
		return CEPFornecedor;
	}

	public void setCEPFornecedor(int CEPFornecedor) {
		this.CEPFornecedor = CEPFornecedor;
	}

	@NotBlank(message = "Insira o Endereço ")
	public String getEnderecoFornecedor() {
		return enderecoFornecedor;
	}

	public void setEnderecoFornecedor(String enderecoFornecedor) {
		this.enderecoFornecedor = enderecoFornecedor;
	}

	public int getNumeroEndereco() {
		return numeroEndereco;
	}

	public void setNumeroEndereco(int numeroEndereco) {
		this.numeroEndereco = numeroEndereco;
	}

	@NotBlank(message = "Insira o Complemento do endereço ")
	public String getComplementoEndereco() {
		return complementoEndereco;
	}

	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}

	@Override
	public int compareTo(EntityFornecedor fornecedor) {
		return getNomeFornecedor().compareTo(fornecedor.getNomeFornecedor());
	}

	@Override
	public String toString() {
		return nomeFornecedor;
	}
}