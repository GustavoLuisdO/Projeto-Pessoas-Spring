package com.ot.pessoa.controller;

import com.ot.pessoa.dao.GlobalDao;
import com.ot.pessoa.domain.Genero;
import com.ot.pessoa.domain.Pessoa;
import com.ot.pessoa.domain.Produto;
import com.ot.pessoa.domain.Telefone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("pessoa")
public class PessoaController {

    @Autowired
    private GlobalDao dao;

    @GetMapping("/all")
    public ModelAndView findAll(ModelMap model) {
        model.addAttribute("pessoas", dao.findAllPessoas());
        return new ModelAndView("pessoa/list", model);
    }

    @GetMapping("/create")
    public ModelAndView create(@ModelAttribute("pessoa") Pessoa pessoa, ModelMap model) {
        model.addAttribute("generos", Genero.values());
        return new ModelAndView("pessoa/create", model);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("pessoa") Pessoa pessoa, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("pessoa/create");
        }
        if (dao.validationPessoa(pessoa)) {
            dao.create(pessoa);
            attr.addFlashAttribute("messageSuccess", "Registro inserido com sucesso!");
        }
        else {
            attr.addFlashAttribute("messageError", "Erro ao inserir registro. Preencha todos os campos corretamente!");
        }
        return new ModelAndView("redirect:/pessoa/all");
    }

    @GetMapping("/update/{id}")
    public ModelAndView viewUpdate(@PathVariable("id") Long id, ModelMap model) {
        Pessoa pessoa = dao.findByIdPessoa(id);
        model.addAttribute("pessoa", pessoa);
        return new ModelAndView("pessoa/edit", model);
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("pessoa") Pessoa pessoa, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("pessoa/edit");
        }

        if (dao.validationPessoa(pessoa)) {
            dao.update(pessoa);
            attr.addFlashAttribute("messageSuccess", "Registro alterado com sucesso!");
        }
        else {
            attr.addFlashAttribute("messageError", "Erro ao alterar registro. Preencha todos os campos corretamente!");
        }
        return new ModelAndView("redirect:/pessoa/all");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id, RedirectAttributes attr) {
        if (dao.deletePessoa(id)) {
            dao.deletePessoa(id);
            attr.addFlashAttribute("messageSuccess", "Registro excluido com sucesso!");
        }
        else {
            attr.addFlashAttribute("messageError", "Não é possível excluir este registro!");
        }
        return new ModelAndView("redirect:/pessoa/all");
    }

    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable("id") Long id, @ModelAttribute("telefone") Telefone telefone, @ModelAttribute("produto") Produto produto,
                                ModelMap model) {

        Pessoa pessoa = dao.findByIdPessoa(id);
        List<Telefone> telefones = dao.findTelefoneByPessoa(id, pessoa);
        List<Produto> produtos = dao.findProdutoByPessoa(id, pessoa);

        model.addAttribute("pessoa", pessoa);
        model.addAttribute("telefones", telefones);
        model.addAttribute("produtos", produtos);

        return new ModelAndView("pessoa/details", model);
    }

    @PostMapping("/details/{id}")
    public ModelAndView detailsPost(@PathVariable("id") Long id, @Valid @ModelAttribute("telefone") Telefone telefone, @ModelAttribute("produto") Produto produto,
                                    RedirectAttributes attr) {
        Pessoa pessoa = dao.findByIdPessoa(id);
        telefone.setId(null);
        telefone.setDono(pessoa);

        if (dao.validationTelefone(telefone)) {
            dao.create(telefone);
            attr.addFlashAttribute("messageSuccess", "Telefone criado com sucesso!");
        }
        else {
            attr.addFlashAttribute("messageError", "Erro ao inserir telefone. Preencha todos os campos corretamente!");
        }

        produto.setId(null);
        produto.setCliente(pessoa);
        dao.create(produto);
        attr.addFlashAttribute("messageSuccess", "Produto criado com sucesso!");

        return new ModelAndView("redirect:/pessoa/details/{id}");
    }
}