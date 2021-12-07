package com.romanova.bd.controller;

import com.romanova.bd.dto.ObjectDto;
import com.romanova.bd.repository.TargetLearningFrameworkRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/target_learning_framework")
@AllArgsConstructor
public class TargetLearningFrameworkController {
    private static final List<String> HEADERS =
            List.of("number_of_contract_with_company", "receipt_of_payment", "availability_of_original_certificate");

    private static final List<String> UPDATE_HEADERS =
            List.of("receipt_of_payment", "availability_of_original_certificate");

    private static final List<String> LIKE_HEADERS =
            List.of();

    private final TargetLearningFrameworkRepository repository;

    @GetMapping("/action/{action}")
    public String getAll(Model model, @PathVariable String action){
        model.addAttribute("action", action);
        model.addAttribute("object", new ObjectDto());

        switch (action){
            case "SELECT_ALL":
                model.addAttribute("objects", repository.findAll());
                return "objects/target_learning_framework/all";
            case "SELECT_BY_ID":
            case "SELECT_LIKE":
            case "SELECT_WHERE":
            case "ORDER_BY":
            case "UPDATE":
            case "DELETE":
                return "input";
            case "INSERT":
                return "objects/target_learning_framework/insert";
        }

        return null;
    }

    @PostMapping("/SELECT_BY_ID")
    public String getById(@ModelAttribute ObjectDto objectDto, Model model){
        Integer id = Integer.parseInt(objectDto.getId());
        model.addAttribute("objects",  repository.getById(id));
        return "objects/target_learning_framework/all";
    }

    @PostMapping("/SELECT_LIKE")
    public String getObjectsLike(@ModelAttribute ObjectDto objectDto, Model model){
        model.addAttribute("objects",  List.of());

        return "objects/target_learning_framework/all";
    }

    @PostMapping("/SELECT_WHERE")
    public String getObjectsWhere(@ModelAttribute ObjectDto objectDto, Model model){
        String field = objectDto.getField();
        String value = objectDto.getValue();
        switch (field){
            case "number_of_contract_with_company":
                model.addAttribute("objects", repository.getObjectsWhereIdEquals(value));
                break;
            case "receipt_of_payment":
                model.addAttribute("objects", repository.getObjectsWhereReceiptEquals(value));
                break;
            case "availability_of_original_certificate":
                model.addAttribute("objects", repository.getObjectsWhereCertificateAvailabilityEquals(value));
                break;
        }
        return "objects/target_learning_framework/all";
    }

    @PostMapping("/ORDER_BY")
    public String getObjectsOrderBy(@ModelAttribute ObjectDto objectDto, Model model){
        String field = objectDto.getField();
        String order = objectDto.getOrder();
        switch (field){
            case "number_of_contract_with_company":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderById());
                else
                    model.addAttribute("objects", repository.getObjectsOrderByIdDesk());
                break;
            case "receipt_of_payment":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderByReceipt());
                else
                    model.addAttribute("objects", repository.getObjectsOrderByReceiptDesk());
                break;
            case "availability_of_original_certificate":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderByCertificateAvailability());
                else
                    model.addAttribute("objects", repository.getObjectsOrderByCertificateAvailabilityDesk());
                break;
        }
        return "objects/target_learning_framework/all";
    }

    @PostMapping("/UPDATE")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String update(@ModelAttribute ObjectDto objectDto, Model model){
        Integer id = Integer.parseInt(objectDto.getId());
        String field = objectDto.getField();
        String value = objectDto.getValue();
        switch (field){
            case "receipt_of_payment":
                if(value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("no"))
                    repository.updateReceipt(value, id);
                break;
            case "availability_of_original_certificate":
                if(value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("no"))
                    repository.updateCertificateAvailability(value, id);
                break;
        }
        model.addAttribute("objects", repository.findAll());
        return "objects/target_learning_framework/all";
    }

    @PostMapping("/INSERT")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String insert(@ModelAttribute ObjectDto objectDto, Model model){
        String f1 = objectDto.getF1();
        String f2 = objectDto.getF2();
        String f3 = objectDto.getF3();

        if(!f2.equalsIgnoreCase("yes") && f2.equalsIgnoreCase("no")){
            f2 = "No";
        }

        if(!f3.equalsIgnoreCase("yes") && f3.equalsIgnoreCase("no")){
            f3 = "No";
        }

        repository.insert(f1, f2, f3);

        model.addAttribute("objects", repository.findAll());
        return "objects/target_learning_framework/all";
    }

    @PostMapping("/DELETE")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@ModelAttribute ObjectDto objectDto, Model model){
        int id = Integer.parseInt(objectDto.getId());

        repository.deleteById(id);

        model.addAttribute("objects", repository.findAll());
        return "objects/target_learning_framework/all";
    }

    @ModelAttribute
    public void modelParameters(Model model){
        model.addAttribute("headers", HEADERS);
        model.addAttribute("update_headers", UPDATE_HEADERS);
        model.addAttribute("like_headers", LIKE_HEADERS);
        model.addAttribute("table", "target_learning_framework");
    }
}
