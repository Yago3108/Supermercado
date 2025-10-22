
package model;

import java.util.HashMap;
import java.util.Map;

public class Carrinho {
    
    private final Map<Produto, Integer> itensComQuantidade = new HashMap<>();

    public void adicionarProduto(Produto produto) {
        // Se já existe, pega a quantidade atual e adiciona 1
        itensComQuantidade.put(
            produto, 
            itensComQuantidade.getOrDefault(produto, 0) + 1
        );
    }

    // Remove 1 unidade do produto
    public void removerProduto(Produto produto) {
        if (itensComQuantidade.containsKey(produto)) {
            int novaQuantidade = itensComQuantidade.get(produto) - 1;
            if (novaQuantidade <= 0) {
                itensComQuantidade.remove(produto); 
            } else {
                itensComQuantidade.put(produto, novaQuantidade);
            }
        }
    }
    
    public double calcularTotal() {
        double total = 0;
        for (Map.Entry<Produto, Integer> entry : itensComQuantidade.entrySet()) {
            total += entry.getKey().getPreco() * entry.getValue();
        }
        return total;
    }
    
    public void limpar() {
        itensComQuantidade.clear();
    }
    public Map<Produto, Integer> getItensComQuantidade() {
        return itensComQuantidade;
    }
}