package com.romanova.bd.controller;

import com.romanova.bd.dto.ObjectDto;
import com.romanova.bd.repository.FillingOutApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/filling_out_application")
@AllArgsConstructor
public class FillingOutApplicationController {
    private static final List<String> HEADERS =
            List.of("application_number", "text_of_application", "signature_enrollee");

    private static final List<String> UPDATE_HEADERS =
            List.of("text_of_application", "signature_enrollee");

    private static final List<String> LIKE_HEADERS =
            List.of("text_of_application");

    private final FillingOutApplicationRepository repository;

    @GetMapping("/action/{action}")
    public String getAll(Model model, @PathVariable String action){
        model.addAttribute("action", action);
        model.addAttribute("object", new ObjectDto());

        switch (action){
            case "SELECT_ALL":
                model.addAttribute("objects", repository.findAll());
                return "objects/filling_out_application/all";
            case "SELECT_BY_ID":
            case "SELECT_LIKE":
            case "SELECT_WHERE":
            case "ORDER_BY":
            case "UPDATE":
            case "DELETE":
                return "input";
            case "INSERT":
                return "objects/filling_out_application/insert";
        }

        return null;
    }

    @PostMapping("/SELECT_BY_ID")
    public String getById(@ModelAttribute ObjectDto objectDto, Model model){
        Integer id = Integer.parseInt(objectDto.getId());
        model.addAttribute("objects",  repository.getById(id));
        return "objects/filling_out_application/all";
    }

    @PostMapping("/SELECT_LIKE")
    public String getObjectsLike(@ModelAttribute ObjectDto objectDto, Model model){
        String like = objectDto.getLike();
        String field = objectDto.getField();
        if(field.equals("text_of_application")){
            model.addAttribute("objects",  repository.getObjectsWhereAppTextLike(like));
        }
        else {
            model.addAttribute("objects",  List.of());
        }
        return "objects/filling_out_application/all";
    }

    @PostMapping("/SELECT_WHERE")
    public String getObjectsWhere(@ModelAttribute ObjectDto objectDto, Model model){
        String field = objectDto.getField();
        String value = objectDto.getValue();
        switch (field){
            case "application_number":
                model.addAttribute("objects", repository.getObjectsWhereIdEquals(Integer.parseInt(value)));
                break;
            case "text_of_application":
                model.addAttribute("objects", repository.getObjectsWhereAppTextEquals(value));
                break;
            case "signature_enrollee":
                model.addAttribute("objects", repository.getObjectsWhereSignatureEnrolleeEquals(Integer.parseInt(value)));
                break;
        }
        return "objects/filling_out_application/all";
    }

    @PostMapping("/ORDER_BY")
    public String getObjectsOrderBy(@ModelAttribute ObjectDto objectDto, Model model){
        String field = objectDto.getField();
        String order = objectDto.getOrder();
        switch (field){
            case "application_number":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderById());
                else
                    model.addAttribute("objects", repository.getObjectsOrderByIdDesk());
                break;
            case "text_of_application":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderByAppText());
                else
                    model.addAttribute("objects", repository.getObjectsOrderByAppTextDesk());
                break;
            case "signature_enrollee":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderBySignatureEnrollee());
                else
                    model.addAttribute("objects", repository.getObjectsOrderBySignatureEnrolleeDesk());
                break;
        }
        return "objects/filling_out_application/all";
    }

    @PostMapping("/UPDATE")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String update(@ModelAttribute ObjectDto objectDto, Model model){
        Integer id = Integer.parseInt(objectDto.getId());
        String field = objectDto.getField();
        String value = objectDto.getValue();
        switch (field){
            case "text_of_application":
                repository.updateText(value, id);
                break;
            case "signature_enrollee":
                repository.updateSignature(value, id);
                break;
        }
        model.addAttribute("objects", repository.findAll());
        return "objects/filling_out_application/all";
    }

    @PostMapping("/INSERT")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String insert(@ModelAttribute ObjectDto objectDto, Model model){
        String f1 = objectDto.getF1();
        String f2 = objectDto.getF2();
        String f3 = objectDto.getF3();

        repository.insert(f1, f2, f3);

        model.addAttribute("objects", repository.findAll());
        return "objects/filling_out_application/all";
    }

    @PostMapping("/DELETE")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@ModelAttribute ObjectDto objectDto, Model model){
        int id = Integer.parseInt(objectDto.getId());

        repository.deleteById(id);

        model.addAttribute("objects", repository.findAll());
        return "objects/filling_out_application/all";
    }

    @ModelAttribute
    public void modelParameters(Model model){
        model.addAttribute("headers", HEADERS);
        model.addAttribute("update_headers", UPDATE_HEADERS);
        model.addAttribute("like_headers", LIKE_HEADERS);
        model.addAttribute("table", "filling_out_application");
    }
}
