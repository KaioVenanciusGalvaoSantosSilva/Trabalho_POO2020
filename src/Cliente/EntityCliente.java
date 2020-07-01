package Cliente;

import javax.validation.constraints.NotBlank;

public class EntityCliente implements Comparable<EntityCliente> {

	private int idCliente = 0;
	private String nomeCliente = "";
	private String CPFCliente = "";
	private String emailCliente = "";
	private int telefoneCliente;
	private int CEPCliente;
	private String enderecoCliente = "";
	private int numeroEndereco;
	private String complementoEndereco = "";

	public EntityCliente() {
	}

	public EntityCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	@NotBlank(message = "Insira o Cliente ")
	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	@NotBlank(message = "Insira o CPF ")
	public String getCPFCliente() {
		return CPFCliente;
	}

	public void setCPFCliente(String CPFCliente) {
		this.CPFCliente = CPFCliente;
	}

	@NotBlank(message = "Insira o e-mail ")
	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public int getTelefoneCliente() {
		return telefoneCliente;
	}

	public void setTelefoneCliente(int telefoneCliente) {
		this.telefoneCliente = telefoneCliente;
	}

	public int getCEPCliente() {
		return CEPCliente;
	}

	public void setCEPCliente(int CEPCliente) {
		this.CEPCliente = CEPCliente;
	}

	@NotBlank(message = "Insira o Endereço ")
	public String getEnderecoCliente() {
		return enderecoCliente;
	}

	public void setEnderecoCliente(String enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
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
	public int compareTo(EntityCliente cliente) {
		return getNomeCliente().compareTo(cliente.getNomeCliente());
	}

	@Override
	public String toString() {
		return nomeCliente;
	}
}