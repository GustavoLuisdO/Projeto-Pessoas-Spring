package com.ot.pessoa.controller;

import com.ot.pessoa.dao.GlobalDao;
import com.ot.pessoa.domain.Pessoa;
import com.ot.pessoa.domain.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private GlobalDao dao;

    @GetMapping("/update/{id}")
    public ModelAndView viewUpdate(@PathVariable("id") Long id, ModelMap model) {
        Produto produto = dao.findByIdProduto(id);
        model.addAttribute("produto", produto);
        model.addAttribute("pessoa", produto.getCliente());
        return new ModelAndView("produto/edit", model);
    }

    @PostMapping("/update/{id}")
    public ModelAndView update(@PathVariable("id") Long id, @Valid @ModelAttribute("produto") Produto produto, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("produto/edit");
        }

        Produto prod = dao.findByIdProduto(id);
        Pessoa cliente = prod.getCliente();
        produto.setCliente(cliente);
        if (dao.validationProduto(produto)) {
            dao.update(produto);
            attr.addFlashAttribute("messageSuccess", "Produto alterado com sucesso!");
        }
        else {
            attr.addFlashAttribute("messageError", "Erro ao alterar produto. Preencha todos os campos corretamente!");
        }
        return new ModelAndView("redirect:/pessoa/details/"+cliente.getId());
    }

}