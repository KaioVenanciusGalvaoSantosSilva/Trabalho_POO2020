package Estoque;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EstoqueControl {
	private EstoqueDAOImpl EntradaDAO = new EstoqueDAOImpl();
	private ObservableList<EntityEstoque> lista = FXCollections.observableArrayList();

	public EntityEstoque pesquisarPorNome(String nomeProduto) {
		lista.clear();
		List<EntityEstoque> estoques = EntradaDAO.pesquisarPorNome(nomeProduto);
		lista.addAll(estoques);
		return lista.get(0);
	}

	public ObservableList<EntityEstoque> getLista() {
		return lista;
	}
}