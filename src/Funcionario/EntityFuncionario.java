package Funcionario;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

public class EntityFuncionario implements Comparable<EntityFuncionario> {

	private int idFuncionario = 0;
	private String nomeFuncionario = "";
	private String CPFFuncionario = "";
	private String emailFuncionario = "";
	private int telefoneFuncionario;
	private int CEPFuncionario;
	private String enderecoFuncionario = "";
	private int numeroEndereco;
	private String complementoEndereco = "";
	private String area = "";
	private String funcao = "";

	private LocalDate dataadmissao = LocalDate.now();
	private LocalDate datadesligamento = LocalDate.of(2100, 1, 1);

	public EntityFuncionario() {
	}

	public EntityFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	public int getIdFuncionario() {
		return idFuncionario;
	}

	public void setIdFuncionario(int idFuncionario) {
		this.idFuncionario = idFuncionario;
	}

	@NotBlank(message = "Insira o Funcionario ")
	public String getNomeFuncionario() {
		return nomeFuncionario;
	}

	public void setNomeFuncionario(String nomeFuncionario) {
		this.nomeFuncionario = nomeFuncionario;
	}

	@NotBlank(message = "Insira o CPF ")
	public String getCPFFuncionario() {
		return CPFFuncionario;
	}

	public void setCPFFuncionario(String CPFFuncionario) {
		this.CPFFuncionario = CPFFuncionario;
	}

	@NotBlank(message = "Insira o e-mail ")
	public String getEmailFuncionario() {
		return emailFuncionario;
	}

	public void setEmailFuncionario(String emailFuncionario) {
		this.emailFuncionario = emailFuncionario;
	}

	public int getTelefoneFuncionario() {
		return telefoneFuncionario;
	}

	public void setTelefoneFuncionario(int telefoneFuncionario) {
		this.telefoneFuncionario = telefoneFuncionario;
	}

	public int getCEPFuncionario() {
		return CEPFuncionario;
	}

	public void setCEPFuncionario(int CEPFuncionario) {
		this.CEPFuncionario = CEPFuncionario;
	}

	@NotBlank(message = "Insira o Endereço ")
	public String getEnderecoFuncionario() {
		return enderecoFuncionario;
	}

	public void setEnderecoFuncionario(String enderecoFuncionario) {
		this.enderecoFuncionario = enderecoFuncionario;
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

	@NotBlank(message = "Insira a Area ")
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@NotBlank(message = "Insira a Função ")
	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	@PastOrPresent(message = "esta data deve estar no passado ou presente")
	public LocalDate getDataAdmissao() {
		return dataadmissao;
	}

	public void setDataAdmissao(LocalDate dataadmissao) {
		this.dataadmissao = dataadmissao;
	}

	@FutureOrPresent(message = "esta data deve estar no presente ou futuro")
	public LocalDate getDataDesligamento() {
		return datadesligamento;
	}

	public void setDataDesligamento(LocalDate datadesligamento) {
		this.datadesligamento = datadesligamento;
	}

	@Override
	public int compareTo(EntityFuncionario funcionario) {
		return getNomeFuncionario().compareTo(funcionario.getNomeFuncionario());
	}

	@Override
	public String toString() {
		return nomeFuncionario;
	}
}