package com.romanova.bd.controller;

import com.romanova.bd.dto.ObjectDto;
import com.romanova.bd.repository.AdmissionOfficerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admission_officer")
@AllArgsConstructor
public class AdmissionOfficerController {
    private static final List<String> HEADERS =
            List.of("id_admission_officer", "busyness", "signature_admission_officer");

    private static final List<String> UPDATE_HEADERS =
            List.of("busyness", "signature_admission_officer");

    private static final List<String> LIKE_HEADERS =
            List.of("busyness");

    private final AdmissionOfficerRepository repository;

    @GetMapping("/action/{action}")
    public String getAll(Model model, @PathVariable String action){
        model.addAttribute("action", action);
        model.addAttribute("object", new ObjectDto());

        switch (action){
            case "SELECT_ALL":
                model.addAttribute("objects", repository.findAll());
                return "objects/admission_officer/all";
            case "SELECT_BY_ID":
            case "SELECT_LIKE":
            case "SELECT_WHERE":
            case "ORDER_BY":
            case "UPDATE":
            case "DELETE":
                return "input";
            case "INSERT":
                return "objects/admission_officer/insert";
        }

        return null;
    }

    @PostMapping("/SELECT_BY_ID")
    public String getById(@ModelAttribute ObjectDto objectDto, Model model){
        Integer id = Integer.parseInt(objectDto.getId());
        model.addAttribute("objects",  repository.getById(id));
        return "objects/admission_officer/all";
    }

    @PostMapping("/SELECT_LIKE")
    public String getObjectsLike(@ModelAttribute ObjectDto objectDto, Model model){
        String like = objectDto.getLike();
        String field = objectDto.getField();
        if(field.equals("busyness")){
            model.addAttribute("objects",  repository.getObjectsWhereBusynessLike(like));
        }
        else {
            model.addAttribute("objects",  List.of());
        }
        return "objects/admission_officer/all";
    }

    @PostMapping("/SELECT_WHERE")
    public String getObjectsWhere(@ModelAttribute ObjectDto objectDto, Model model){
        String field = objectDto.getField();
        String value = objectDto.getValue();
        switch (field){
            case "id_admission_officer":
                model.addAttribute("objects", repository.getObjectsWhereIdEquals(Integer.parseInt(value)));
                break;
            case "busyness":
                model.addAttribute("objects", repository.getObjectsWhereBusynessEquals(value));
                break;
            case "signature_admission_officer":
                model.addAttribute("objects", repository.getObjectsWhereSignatureEquals(Integer.parseInt(value)));
                break;

        }
        return "objects/admission_officer/all";
    }

    @PostMapping("/ORDER_BY")
    public String getObjectsOrderBy(@ModelAttribute ObjectDto objectDto, Model model){
        String field = objectDto.getField();
        String order = objectDto.getOrder();
        switch (field){
            case "id_admission_officer":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderById());
                else
                    model.addAttribute("objects", repository.getObjectsOrderByIdDesk());
                break;
            case "busyness":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderByBusyness());
                else
                    model.addAttribute("objects", repository.getObjectsOrderByBusynessDesk());
                break;
            case "signature_admission_officer":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderBySignature());
                else
                    model.addAttribute("objects", repository.getObjectsOrderBySignatureDesk());
                break;

        }
        return "objects/admission_officer/all";
    }

    @PostMapping("/UPDATE")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String update(@ModelAttribute ObjectDto objectDto, Model model){
        Integer id = Integer.parseInt(objectDto.getId());
        String field = objectDto.getField();
        String value = objectDto.getValue();
        switch (field){
            case "busyness":
                repository.updateBusyness(value, id);
                break;
            case "signature_admission_officer":
                repository.updateSignature(value, id);
                break;
        }
        model.addAttribute("objects", repository.findAll());
        return "objects/admission_officer/all";
    }

    @PostMapping("/INSERT")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String insert(@ModelAttribute ObjectDto objectDto, Model model){
        String f1 = objectDto.getF1();
        String f2 = objectDto.getF2();
        String f3 = objectDto.getF3();

        repository.insert(f1, f2, Integer.parseInt(f3));

        model.addAttribute("objects", repository.findAll());
        return "objects/admission_officer/all";
    }

    @PostMapping("/DELETE")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@ModelAttribute ObjectDto objectDto, Model model){
        int id = Integer.parseInt(objectDto.getId());

        repository.deleteById(id);

        model.addAttribute("objects", repository.findAll());
        return "objects/admission_officer/all";
    }

    @ModelAttribute
    public void modelParameters(Model model){
        model.addAttribute("headers", HEADERS);
        model.addAttribute("update_headers", UPDATE_HEADERS);
        model.addAttribute("like_headers", LIKE_HEADERS);
        model.addAttribute("table", "admission_officer");
    }
}
