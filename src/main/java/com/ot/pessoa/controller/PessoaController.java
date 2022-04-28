package com.ot.pessoa.controller;

import com.ot.pessoa.dao.PessoaDao;
import com.ot.pessoa.dao.TelefoneDao;
import com.ot.pessoa.domain.Pessoa;
import com.ot.pessoa.domain.Telefone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("pessoa")
public class PessoaController {

    @Autowired
    private PessoaDao dao;

    @Autowired
    private TelefoneDao daoTel;

    @GetMapping("/all")
    public ModelAndView findAll(ModelMap model) {
        model.addAttribute("pessoas", dao.findAll());
        return new ModelAndView("/pessoa/list", model);
    }

    @GetMapping("/create")
    public ModelAndView create(@ModelAttribute("pessoa") Pessoa pessoa) {
        return new ModelAndView("pessoa/create");
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("pessoa") Pessoa pessoa, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("/pessoa/create");
        }
        dao.create(pessoa);
        attr.addFlashAttribute("message", "Registro inserido com sucesso!");
        return new ModelAndView("redirect:/pessoa/all");
    }

    @GetMapping("/update/{id}")
    public ModelAndView viewUpdate(@PathVariable("id") Long id, ModelMap model) {
        Pessoa pessoa = dao.findById(id);
        model.addAttribute("pessoa", pessoa);
        return new ModelAndView("/pessoa/edit", model);
    }

    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("pessoa") Pessoa pessoa, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("/pessoa/edit");
        }
        dao.update(pessoa);
        attr.addFlashAttribute("message", "Registro alterado com sucesso!");
        return new ModelAndView("redirect:/pessoa/all");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id, RedirectAttributes attr) {
        dao.delete(id);
        attr.addFlashAttribute("message", "Registro excluido com sucesso!");
        return new ModelAndView("redirect:/pessoa/all");
    }

    @GetMapping("/details/{id}")
    public ModelAndView details(@PathVariable("id") Long id, @ModelAttribute("telefone")Telefone telefone, ModelMap model) {
        model.addAttribute("pessoa", dao.findById(id));
        return new ModelAndView("/pessoa/details", model);
    }

    @PostMapping("/details/{id}")
    public String details(@PathVariable("id") Long id, @ModelAttribute("telefone")Telefone telefone, ModelMap model, RedirectAttributes attr) {
        Pessoa pessoa = dao.findById(id);
        telefone.setDono(pessoa);
        daoTel.create(telefone);
        return "redirect:/details/{id}";
    }
}