package com.taqui.api_mvc.controller;

import com.taqui.api_mvc.dto.ProdutoRequest;
import com.taqui.api_mvc.dto.ProdutoResponse;
import com.taqui.api_mvc.model.Produto;
import com.taqui.api_mvc.repository.ProdutoRepository;
import com.taqui.api_mvc.service.ErroNegocioException;
import com.taqui.api_mvc.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ProdutoViewController {


    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/listaProdutos")
    public ModelAndView listaProdutos() {
        List<Produto> listaProdutos = produtoRepository.findAll();
        List<ProdutoResponse> listaProdutosResponse = new ArrayList<>();
        for (Produto produto : listaProdutos) {
            listaProdutosResponse.add(produtoService.produtoToResponse(produto));
        }
        ModelAndView mv = new ModelAndView("produtos");
        mv.addObject("listaProdutos", listaProdutosResponse);
        return mv;
    }

    @GetMapping("/listaProdutosTemplate")
    public ModelAndView listaProdutosTemplate() {
        List<Produto> listaProdutos = produtoRepository.findAll();
        List<ProdutoResponse> listaProdutosResponse = new ArrayList<>();
        for (Produto produto : listaProdutos) {
            listaProdutosResponse.add(produtoService.produtoToResponse(produto));
        }
        ModelAndView mv = new ModelAndView("template");
        mv.addObject("page", "produtos");
        mv.addObject("content", "listaProdutos");
        mv.addObject("listaProdutos", listaProdutosResponse);
        return mv;
    }



    @GetMapping("/cadastroProduto")
    public ModelAndView cadastroLivro() {
        ModelAndView mv = new ModelAndView("template");
        mv.addObject("page", "produtoCadastro");
        mv.addObject("content", "formCadastro");
        mv.addObject("produtoRequest", new ProdutoRequest());
        return mv;
    }

    @PostMapping("cadastrarProduto")
    public ModelAndView cadastrarProduto(@Valid @ModelAttribute ProdutoRequest produtoRequest) {
        Produto produto = produtoService.requestToProduto(produtoRequest);
        produtoRepository.save(produto);
        return listaProdutosTemplate();
    }

    @GetMapping("/editaProduto/{id}")
    public ModelAndView editarProduto(@PathVariable Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isEmpty()){
            throw new ErroNegocioException("Produto não encontrado.");
        }
        ModelAndView mv = new ModelAndView("template");
        mv.addObject("page", "produtoEdicao");
        mv.addObject("content", "formEdicao");
        mv.addObject("produtoRequest", produtoService.produtoToRequest(produto.get()));
        mv.addObject("idProduto", id);
        return mv;
    }


    @PostMapping("/atualizarProduto/{id}")
    public ModelAndView atualizarProduto(@PathVariable Long id, @Valid @ModelAttribute ProdutoRequest produtoRequest ) {
        if(produtoRepository.findById(id).isEmpty()){
            throw new ErroNegocioException("Produto não encontrado.");
        }
        Produto produto = produtoService.requestToProduto(produtoRequest);
        produto.setId(id);
        produtoRepository.save(produto);
        return listaProdutosTemplate();
    }


    @GetMapping("/deletarProduto/{id}")
    public ModelAndView deletarProduto(@PathVariable Long id){
        Optional<Produto> produto = produtoRepository.findById(id);
        if(produto.isEmpty()){
            throw new ErroNegocioException("Produto não encontrado.");
        }
        produtoRepository.delete(produto.get());
        return listaProdutosTemplate();
    }


}
