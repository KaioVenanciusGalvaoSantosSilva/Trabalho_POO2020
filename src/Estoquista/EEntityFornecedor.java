package Estoquista;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EEntityFornecedor implements Comparable<EEntityFornecedor> {

	private String nomeFornecedor = "";
	private String CNPJFornecedor = "";
	private String emailFornecedor = "";
	private int telefoneFornecedor;
	private int CEPFornecedor ;
	private String enderecoFornecedor = "";
	private int numeroEndereco ;
	private String complementoEndereco = "";

	public EEntityFornecedor() {
	}

	public EEntityFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}


	@NotBlank
	@Size(min = 1, max = 50)
	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	@NotBlank
	public String getCNPJFornecedor() {
		return CNPJFornecedor;
	}

	public void setCNPJFornecedor(String CNPJFornecedor) {
		this.CNPJFornecedor = CNPJFornecedor;
	}

	@NotBlank
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

	@NotBlank
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

	@NotBlank
	public String getComplementoEndereco() {
		return complementoEndereco;
	}

	public void setComplementoEndereco(String complementoEndereco) {
		this.complementoEndereco = complementoEndereco;
	}

	@Override
	public int compareTo(EEntityFornecedor fornecedor) {
		return getNomeFornecedor().compareTo(fornecedor.getNomeFornecedor());
	}

	@Override
	public String toString() {
		return nomeFornecedor;
	}
}
