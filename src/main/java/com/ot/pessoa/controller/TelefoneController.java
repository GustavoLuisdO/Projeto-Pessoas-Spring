package com.ot.pessoa.controller;

import com.ot.pessoa.dao.PessoaDao;
import com.ot.pessoa.dao.TelefoneDao;
import com.ot.pessoa.domain.Pessoa;
import com.ot.pessoa.domain.Telefone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("telefone")
public class TelefoneController {

    @Autowired
    private TelefoneDao dao;

    @Autowired
    private PessoaDao daoPessoa;

    @GetMapping("/all")
    public ModelAndView findAll(ModelMap model) {
        model.addAttribute("telefones", dao.findAll());
        return new ModelAndView("/telefone/list", model);
    }

    @GetMapping("/create")
    public ModelAndView create(@ModelAttribute("telefone") Telefone telefone, ModelMap model) {
        model.addAttribute("pessoas", daoPessoa.findAll());
        return new ModelAndView("telefone/create", model);
    }

    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("telefone") Telefone telefone, BindingResult result,RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("/telefone/create");
        }
        dao.create(telefone);
        attr.addFlashAttribute("message", "Telefone inserido com sucesso!");
        return new ModelAndView("redirect:/telefone/all");
    }
}