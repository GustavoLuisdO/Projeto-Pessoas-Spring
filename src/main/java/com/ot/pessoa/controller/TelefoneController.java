package com.ot.pessoa.controller;

import com.ot.pessoa.dao.GlobalDao;
import com.ot.pessoa.domain.Pessoa;
import com.ot.pessoa.domain.Telefone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/updateTel/{id}")
    public ModelAndView viewUpdateTel(@PathVariable("id") Long id, ModelMap model) {
        Telefone telefone = dao.findByIdTelefone(id);
        model.addAttribute("telefone", telefone);
        model.addAttribute("pessoa", telefone.getDono());
        return new ModelAndView("telefone/edit", model);
    }

    @PostMapping("/updateTel/{id}")
    public ModelAndView updateTel(@PathVariable("id") Long id, @Valid @ModelAttribute("telefone") Telefone telefone, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("telefone/edit");
        }
        Telefone tel = dao.findByIdTelefone(id);
        Pessoa pessoa = tel.getDono();
        telefone.setDono(pessoa);
        dao.update(telefone);
        attr.addFlashAttribute("message", "Telefone alterado com sucesso!");
        return new ModelAndView("redirect:/pessoa/details/"+pessoa.getId());
    }
}