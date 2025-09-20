document.addEventListener('DOMContentLoaded', () => {

    const produtos = [
        { id: 1, nome: "Camiseta Preta", preco: 59.90, imagem: "https://via.placeholder.com/300x200?text=Camiseta" },
        { id: 2, nome: "Calça Jeans", preco: 129.90, imagem: "https://via.placeholder.com/300x200?text=Calça" },
        { id: 3, nome: "Tênis Esportivo", preco: 249.90, imagem: "https://via.placeholder.com/300x200?text=Tênis" },
        { id: 4, nome: "Mochila Moderna", preco: 89.90, imagem: "https://via.placeholder.com/300x200?text=Mochila" },
        { id: 5, nome: "Boné Estiloso", preco: 39.90, imagem: "https://via.placeholder.com/300x200?text=Boné" },
        { id: 6, nome: "Jaqueta de Couro", preco: 350.00, imagem: "https://via.placeholder.com/300x200?text=Jaqueta" },
    ];

    let carrinho = [];

    const secaoProdutos = document.getElementById('secao-produtos');
    const modalCarrinho = document.getElementById('modal-carrinho');
    const carrinhoBtn = document.getElementById('carrinho-btn');
    const fecharCarrinhoBtn = document.getElementById('fechar-carrinho-btn');
    const itensCarrinhoContainer = document.getElementById('itens-carrinho');
    const contadorCarrinho = document.getElementById('contador-carrinho');
    const totalCarrinhoSpan = document.getElementById('total-carrinho');

    // Função para renderizar os produtos na página
    function renderizarProdutos() {
        secaoProdutos.innerHTML = '';
        produtos.forEach(produto => {
            const produtoCard = document.createElement('div');
            produtoCard.classList.add('bg-white', 'rounded-lg', 'shadow-lg', 'overflow-hidden', 'transform', 'transition', 'hover:scale-105');
            produtoCard.innerHTML = `
                <img src="${produto.imagem}" alt="${produto.nome}" class="w-full h-48 object-cover">
                <div class="p-4">
                    <h3 class="text-lg font-bold text-gray-800">${produto.nome}</h3>
                    <p class="text-gray-600 mt-1">R$ ${produto.preco.toFixed(2).replace('.', ',')}</p>
                    <button class="adicionar-carrinho mt-4 w-full bg-blue-500 text-white font-bold py-2 px-4 rounded-full hover:bg-blue-600 transition" data-id="${produto.id}">
                        Adicionar ao Carrinho
                    </button>
                </div>
            `;
            secaoProdutos.appendChild(produtoCard);
        });
    }

    // Função para renderizar os itens do carrinho no modal
    function renderizarCarrinho() {
        itensCarrinhoContainer.innerHTML = '';
        let total = 0;
        carrinho.forEach(item => {
            total += item.preco * item.quantidade;
            const itemDiv = document.createElement('div');
            itemDiv.classList.add('flex', 'justify-between', 'items-center', 'border-b', 'pb-2');
            itemDiv.innerHTML = `
                <div class="flex-1">
                    <h4 class="text-gray-800 font-semibold">${item.nome}</h4>
                    <p class="text-sm text-gray-500">R$ ${item.preco.toFixed(2).replace('.', ',')} x ${item.quantidade}</p>
                </div>
                <div class="flex items-center space-x-2">
                    <button class="remover-item text-red-500 hover:text-red-700 transition" data-id="${item.id}">&times;</button>
                </div>
            `;
            itensCarrinhoContainer.appendChild(itemDiv);
        });

        totalCarrinhoSpan.textContent = `R$ ${total.toFixed(2).replace('.', ',')}`;
        contadorCarrinho.textContent = carrinho.length;
        contadorCarrinho.style.display = carrinho.length > 0 ? 'flex' : 'none';
    }

    // Função para adicionar um produto ao carrinho
    function adicionarAoCarrinho(produtoId) {
        const produto = produtos.find(p => p.id == produtoId);
        if (produto) {
            const itemExistente = carrinho.find(item => item.id == produtoId);
            if (itemExistente) {
                itemExistente.quantidade++;
            } else {
                carrinho.push({ ...produto, quantidade: 1 });
            }
            renderizarCarrinho();
        }
    }

    // Função para remover um item do carrinho
    function removerDoCarrinho(produtoId) {
        carrinho = carrinho.filter(item => item.id != produtoId);
        renderizarCarrinho();
    }

    // Eventos de clique
    secaoProdutos.addEventListener('click', (e) => {
        if (e.target.classList.contains('adicionar-carrinho')) {
            const produtoId = e.target.dataset.id;
            adicionarAoCarrinho(produtoId);
        }
    });

    itensCarrinhoContainer.addEventListener('click', (e) => {
        if (e.target.classList.contains('remover-item')) {
            const produtoId = e.target.dataset.id;
            removerDoCarrinho(produtoId);
        }
    });

    // Abrir e fechar o modal do carrinho
    carrinhoBtn.addEventListener('click', () => {
        modalCarrinho.style.display = 'flex';
    });

    fecharCarrinhoBtn.addEventListener('click', () => {
        modalCarrinho.style.display = 'none';
    });

    // Fechar o modal ao clicar fora dele
    window.addEventListener('click', (e) => {
        if (e.target === modalCarrinho) {
            modalCarrinho.style.display = 'none';
        }
    });

    // Inicialização
    renderizarProdutos();
    renderizarCarrinho(); // Garante que o contador inicial está correto
});