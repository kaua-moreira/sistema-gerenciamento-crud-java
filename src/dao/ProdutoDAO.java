package dao;

import model.Produto;
import java.util.List;

public interface ProdutoDAO {

    void criar(Produto produto);
    Produto buscarPorId(int id);
    List<Produto> listarTodos();
    void atualizar(Produto produto);
    void deletar(int id);
}