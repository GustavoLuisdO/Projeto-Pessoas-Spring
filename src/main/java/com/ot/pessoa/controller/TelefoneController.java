package com.ot.pessoa.controller;

import com.ot.pessoa.dao.GlobalDao;
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
@RequestMapping("telefone")
public class TelefoneController {

    @Autowired
    private GlobalDao dao;

    @GetMapping("/update/{id}")
    public ModelAndView viewUpdate(@PathVariable("id") Long id, ModelMap model) {
        Telefone telefone = dao.findByIdTelefone(id);
        model.addAttribute("telefone", telefone);
        model.addAttribute("pessoa", telefone.getDono());
        return new ModelAndView("telefone/edit", model);
    }

    @PostMapping("/update/{id}")
    public ModelAndView update(@PathVariable("id") Long id, @Valid @ModelAttribute("telefone") Telefone telefone, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("telefone/edit");
        }

        Telefone tel = dao.findByIdTelefone(id);
        Pessoa pessoa = tel.getDono();
        telefone.setDono(pessoa);
        if (dao.validationTelefone(telefone)) {
            dao.update(telefone);
            attr.addFlashAttribute("messageSuccess", "Telefone alterado com sucesso!");
        }
        else {
            attr.addFlashAttribute("messageError", "Erro ao alterar telefone. Preencha todos os campos corretamente!");
        }
        return new ModelAndView("redirect:/pessoa/details/"+pessoa.getId());
    }
}