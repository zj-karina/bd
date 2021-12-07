package com.romanova.bd.controller;

import com.romanova.bd.dto.ObjectDto;
import com.romanova.bd.repository.TrainingBudgetaryBasisRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/budgetary_basis_of_training")
@AllArgsConstructor
public class TrainingBudgetaryBasisController {
    private static final List<String> HEADERS =
            List.of("state_budget_reciept_number", "availability_of_original_certificate");

    private static final List<String> UPDATE_HEADERS =
            List.of("availability_of_original_certificate");

    private static final List<String> LIKE_HEADERS = List.of();


    private final TrainingBudgetaryBasisRepository repository;

    @GetMapping("/action/{action}")
    public String getAll(Model model, @PathVariable String action){
        model.addAttribute("action", action);
        model.addAttribute("object", new ObjectDto());

        switch (action){
            case "SELECT_ALL":
                model.addAttribute("objects", repository.findAll());
                return "objects/budgetary_basis_of_training/all";
            case "SELECT_BY_ID":
            case "SELECT_LIKE":
            case "SELECT_WHERE":
            case "ORDER_BY":
            case "UPDATE":
            case "DELETE":
                return "input";
            case "INSERT":
                return "objects/budgetary_basis_of_training/insert";
        }

        return null;
    }

    @PostMapping("/SELECT_BY_ID")
    public String getById(@ModelAttribute ObjectDto objectDto, Model model){
        Integer id = Integer.parseInt(objectDto.getId());
        model.addAttribute("objects",  repository.getById(id));
        return "objects/budgetary_basis_of_training/all";
    }

    @PostMapping("/SELECT_LIKE")
    public String getObjectsLike(@ModelAttribute ObjectDto objectDto, Model model){
        model.addAttribute("objects",  List.of());
        return "objects/budgetary_basis_of_training/all";
    }

    @PostMapping("/SELECT_WHERE")
    public String getObjectsWhere(@ModelAttribute ObjectDto objectDto, Model model){
        String field = objectDto.getField();
        String value = objectDto.getValue();
        switch (field){
            case "state_budget_reciept_number":
                model.addAttribute("objects", repository.getObjectsWhereIdEquals(value));
                break;
            case "availability_of_original_certificate":
                model.addAttribute("objects", repository.getObjectsWhereCertificateAvailabilityEquals(value));
                break;
        }
        return "objects/budgetary_basis_of_training/all";
    }

    @PostMapping("/ORDER_BY")
    public String getObjectsOrderBy(@ModelAttribute ObjectDto objectDto, Model model){
        String field = objectDto.getField();
        String order = objectDto.getOrder();
        switch (field){
            case "state_budget_reciept_number":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderById());
                else
                    model.addAttribute("objects", repository.getObjectsOrderByIdDesk());
                break;
            case "availability_of_original_certificate":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderByCertificateAvailability());
                else
                    model.addAttribute("objects", repository.getObjectsOrderByCertificateAvailabilityDesc());
                break;
        }
        return "objects/budgetary_basis_of_training/all";
    }

    @PostMapping("/UPDATE")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String update(@ModelAttribute ObjectDto objectDto, Model model){
        Integer id = Integer.parseInt(objectDto.getId());
        String field = objectDto.getField();
        String value = objectDto.getValue();
        switch (field){
            case "availability_of_original_certificate":
                if(value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("no"))
                    repository.updateCertificateAvailability(value, id);
                break;
        }
        model.addAttribute("objects", repository.findAll());
        return "objects/budgetary_basis_of_training/all";
    }

    @PostMapping("/INSERT")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String insert(@ModelAttribute ObjectDto objectDto, Model model){
        String f1 = objectDto.getF1();
        String f2 = objectDto.getF2();

        if(!f2.equalsIgnoreCase("yes") && f2.equalsIgnoreCase("no")){
            f2 = "No";
        }

        repository.insert(f1, f2);

        model.addAttribute("objects", repository.findAll());
        return "objects/budgetary_basis_of_training/all";
    }

    @PostMapping("/DELETE")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@ModelAttribute ObjectDto objectDto, Model model){
        int id = Integer.parseInt(objectDto.getId());

        repository.deleteById(id);

        model.addAttribute("objects", repository.findAll());
        return "objects/budgetary_basis_of_training/all";
    }

    @ModelAttribute
    public void modelParameters(Model model){
        model.addAttribute("headers", HEADERS);
        model.addAttribute("update_headers", UPDATE_HEADERS);
        model.addAttribute("like_headers", LIKE_HEADERS);
        model.addAttribute("table", "budgetary_basis_of_training");
    }
}
