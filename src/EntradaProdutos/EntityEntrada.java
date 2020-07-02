package EntradaProdutos;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

public class EntityEntrada implements Comparable<EntityEntrada> {

	private int idEntrada = 0;
	private String nomeProd = "";
	private String nomeFornecedor = "";
	private String lote = "";
	private double valorTotal;
	private int quantidade;
	private LocalDate dataEntrada = LocalDate.now();
	private LocalDate dataValidade = LocalDate.of(2100, 1, 1);

	public EntityEntrada() {
	}

	public EntityEntrada(String nomeProd) {
		this.nomeProd = nomeProd;
	}

	public int getIdEntrada() {
		return idEntrada;
	}

	public void setIdEntrada(int idEntrada) {
		this.idEntrada = idEntrada;
	}

	@NotBlank(message = "Insira o Produto ")
	public String getNomeProduto() {
		return nomeProd;
	}

	public void setNomeProduto(String nomeProd) {
		this.nomeProd = nomeProd;
	}

	@NotBlank(message = "Insira o Fornecedor ")
	public String getNomeFornecedor() {
		return nomeFornecedor;
	}

	public void setNomeFornecedor(String nomeFornecedor) {
		this.nomeFornecedor = nomeFornecedor;
	}

	@NotBlank(message = "Insira o Lote ")
	public String getLote() {
		return lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@PastOrPresent(message = "Esta data deve estar no passado ou presente")
	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	@FutureOrPresent(message = "esta data deve estar no presente ou futuro")
	public LocalDate getDataValidade() {
		return dataValidade;
	}

	public void setDataValidade(LocalDate dataValidade) {
		this.dataValidade = dataValidade;
	}

	@Override
	public int compareTo(EntityEntrada entrada) {
		return getNomeProduto().compareTo(entrada.getNomeProduto());
	}

	@Override
	public String toString() {
		return nomeProd;
	}
}