package Login;

import javax.validation.constraints.NotBlank;

public class EntityLogin implements Comparable<EntityLogin> {

	private int idUsuario = 0;
	private String usuario = "";
	private String senha = "";
	private String cargo = "";

	public EntityLogin() {
	}

	public EntityLogin(String usuario) {
		this.usuario = usuario;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	@NotBlank(message = "Insira o Login ")
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@NotBlank(message = "Insira a senha ")
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@NotBlank(message = "Insira o Cargo ")
	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	@Override
	public int compareTo(EntityLogin login) {
		return getUsuario().compareTo(login.getUsuario());
	}

	@Override
	public String toString() {
		return usuario;
	}
}