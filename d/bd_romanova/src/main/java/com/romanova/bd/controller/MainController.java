package com.romanova.bd.controller;

import com.romanova.bd.dto.ObjectDto;
import com.romanova.bd.dto.Table;
import com.romanova.bd.repository.FilterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Controller
public class MainController {

    private static final List<String> TABLES = List.of("admission_officer", "consultant_teacher", "contest",
            "package_of_documents", "enrollee", "filling_out_application", "target_learning_framework",
            "budgetary_basis_of_training", "paid_basis_of_training");

    private static final List<String> ACTIONS = List.of("SELECT_ALL", "SELECT_BY_ID", "SELECT_LIKE",
            "SELECT_WHERE", "UPDATE", "INSERT", "DELETE", "ORDER_BY", "FILTER");

    private final FilterRepository repository;

    @GetMapping("/login")
    public String getLoginPage(){
        return "registration/login";
    }

    @GetMapping("/home")
    public String getHomePage(Model model){
        model.addAttribute("tables", TABLES);
        model.addAttribute("actions", ACTIONS);
        model.addAttribute("tbl", new Table());
        return "main";
    }

    @GetMapping ("/filter/{table}")
    public String getFilterPage(@PathVariable String table, Model model){
        model.addAttribute("table", table);
        model.addAttribute("objectData", new ObjectDto());
        return "filter";
    }

    @PostMapping("/filter")
    public String getInputFilterPage(@ModelAttribute ObjectDto objectDto, Model model){
        String table = objectDto.getF1();
        String field1 = objectDto.getF2();
        String field2 = objectDto.getF4();
        String field3 = objectDto.getF6();
        String value1 =  objectDto.getF3();
        String value2 =  objectDto.getF5();
        String value3 =  objectDto.getF7();

        String comp1 = objectDto.getCompareSign1();
        String comp2 = objectDto.getCompareSign2();
        String comp3 = objectDto.getCompareSign3();

        String ls1 = objectDto.getLogical12();
        String ls2 = objectDto.getLogical23();

        List<Map<String, Object>> result = null;
        if(field2.isEmpty() && field3.isEmpty()){
            result = repository.getFilteredObjectsOneFilter(table, field1, comp1, value1);
        }
        else if (field3.isEmpty()){
            result = repository.getFilteredObjectsTwoFilters(table, field1, comp1, value1, field2, comp2, value2, ls1);
        }
        else {
            result = repository.getFilteredObjectsThreeFilters(
                    table, field1, comp1, value1, field2, comp2, value2, field3, comp3, value3, ls1, ls2);
        }

        List<String> headers = getHeaders(table);
        model.addAttribute("headers", headers);
        model.addAttribute("objects", result);
        return "objects";
    }

    @PostMapping("/table")
    public String getActionPage(@ModelAttribute Table table, Model model){
        if(table.getAction().equals("FILTER")){
            return "redirect:/filter/" + table.getName();
        }


        switch (table.getName()){
            case "admission_officer":
                return "redirect:/admission_officer/action/" + table.getAction();
            case "consultant_teacher":
                return "redirect:/consultant_teacher/action/" + table.getAction();
            case "contest":
                return "redirect:/contest/action/" + table.getAction();
            case "package_of_documents":
                return "redirect:/package_of_documents/action/" + table.getAction();
            case "enrollee":
                return "redirect:/enrollee/action/" + table.getAction();
            case "filling_out_application":
                return "redirect:/filling_out_application/action/" + table.getAction();
            case "target_learning_framework":
                return "redirect:/target_learning_framework/action/" + table.getAction();
            case "budgetary_basis_of_training":
                return "redirect:/budgetary_basis_of_training/action/" + table.getAction();
            case "paid_basis_of_training":
                return "redirect:/paid_basis_of_training/action/" + table.getAction();
        }
        return null;
    }


    private List<String> getHeaders(String table){
        List<Map<String, Object>> result = repository.getAllObjects(table);
        return result.stream()
                .flatMap(x -> x.entrySet().stream())
                .map(Map.Entry::getKey)
                .distinct()
                .collect(Collectors.toList());
    }
}
