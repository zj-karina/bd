package com.romanova.bd.controller;

import com.romanova.bd.dto.ObjectDto;
import com.romanova.bd.repository.EnrolleeRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/enrollee")
@AllArgsConstructor
public class EnrolleeController {
    private static final List<String> HEADERS =
            List.of("id_enrollee", "full_name", "sum_exam_points", "sum_IA_points", "passport_numbers",
            "certificate_numbers", "direction");

    private static final List<String> UPDATE_HEADERS =
            List.of("full_name", "sum_exam_points", "sum_IA_points", "passport_numbers",
                    "certificate_numbers", "direction");

    private static final List<String> LIKE_HEADERS =
            List.of("full_name");


    private final EnrolleeRepository repository;

    @GetMapping("/action/{action}")
    public String getAll(Model model, @PathVariable String action){
        model.addAttribute("action", action);
        model.addAttribute("object", new ObjectDto());

        switch (action){
            case "SELECT_ALL":
                model.addAttribute("objects", repository.findAll());
                return "objects/enrollee/all";
            case "SELECT_BY_ID":
            case "SELECT_LIKE":
            case "SELECT_WHERE":
            case "ORDER_BY":
            case "UPDATE":
            case "DELETE":
                return "input";
            case "INSERT":
                return "objects/enrollee/insert";
        }

        return null;
    }

    @PostMapping("/SELECT_BY_ID")
    public String getById(@ModelAttribute ObjectDto objectDto, Model model){
        Integer id = Integer.parseInt(objectDto.getId());
        model.addAttribute("objects",  repository.getById(id));
        return "objects/enrollee/all";
    }

    @PostMapping("/SELECT_LIKE")
    public String getObjectsLike(@ModelAttribute ObjectDto objectDto, Model model){
        String like = objectDto.getLike();
        String field = objectDto.getField();
        if(field.equals("full_name")){
            model.addAttribute("objects",  repository.getObjectsFullNameLike(like));
        }
        else {
            model.addAttribute("objects",  List.of());
        }
        return "objects/enrollee/all";
    }

    @PostMapping("/SELECT_WHERE")
    public String getObjectsWhere(@ModelAttribute ObjectDto objectDto, Model model){
        String field = objectDto.getField();
        String value = objectDto.getValue();
        switch (field){
            case "id_enrollee":
                model.addAttribute("objects", repository.getObjectsWhereIdEquals(Integer.parseInt(value)));
                break;
            case "full_name":
                model.addAttribute("objects", repository.getObjectsWhereFullNameEquals(value));
                break;
            case "sum_exam_points":
                model.addAttribute("objects", repository.getObjectsWhereSumExamPointEquals(value));
                break;
            case "sum_IA_points":
                model.addAttribute("objects", repository.getObjectsWhereSumIAPointEquals(value));
                break;
            case "passport_numbers":
                model.addAttribute("objects", repository.getObjectsWherePassportNumbersEquals(value));
                break;
            case "certificate_numbers":
                model.addAttribute("objects", repository.getObjectsWhereCertificateNumbersEquals(value));
                break;
            case "direction":
                model.addAttribute("objects", repository.getObjectsWhereDirectionEquals(value));
                break;
        }
        return "objects/enrollee/all";
    }

    @PostMapping("/ORDER_BY")
    public String getObjectsOrderBy(@ModelAttribute ObjectDto objectDto, Model model){
        String field = objectDto.getField();
        String order = objectDto.getOrder();
        switch (field){
            case "id_enrollee":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderById());
                else
                    model.addAttribute("objects", repository.getObjectsOrderByIdDesk());
                break;
            case "full_name":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderByFullName());
                else
                    model.addAttribute("objects", repository.getObjectsOrderByFullNameDesc());
                break;
            case "sum_exam_points":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderBySumExamPoints());
                else
                    model.addAttribute("objects", repository.getObjectsOrderBySumExamPointsDesc());
                break;
            case "sum_IA_points":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderBySumIAPoint());
                else
                    model.addAttribute("objects", repository.getObjectsOrderBySumIAPointDesc());
                break;
            case "passport_numbers":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderByPassportNumbers());
                else
                    model.addAttribute("objects", repository.getObjectsOrderByPassportNumbersDesc());
                break;
            case "certificate_numbers":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderByCertificateNumbers());
                else
                    model.addAttribute("objects", repository.getObjectsOrderByCertificateNumbersDesc());
                break;
            case "direction":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderByDirection());
                else
                    model.addAttribute("objects", repository.getObjectsOrderByDirectionDesc());
                break;
        }
        return "objects/enrollee/all";
    }

    @PostMapping("/UPDATE")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String update(@ModelAttribute ObjectDto objectDto, Model model){
        Integer id = Integer.parseInt(objectDto.getId());
        String field = objectDto.getField();
        String value = objectDto.getValue();
        switch (field){
            case "full_name":
                repository.updateFullName(value, id);
                break;
            case "sum_exam_points":
                repository.updateSumExamPoints(value, id);
                break;
            case "sum_IA_points":
                repository.updateSumIAPoints(value, id);
                break;
            case "passport_numbers":
                repository.updatePassport(value, id);
                break;
            case "certificate_numbers":
                repository.updateCertificate(value, id);
                break;
            case "direction":
                repository.updateDirection(value, id);
                break;
        }
        model.addAttribute("objects", repository.findAll());
        return "objects/enrollee/all";
    }

    @PostMapping("/INSERT")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String insert(@ModelAttribute ObjectDto objectDto, Model model){
        String f1 = objectDto.getF1();
        String f2 = objectDto.getF2();
        String f3 = objectDto.getF3();
        String f4 = objectDto.getF4();
        String f5 = objectDto.getF5();
        String f6 = objectDto.getF6();
        String f7 = objectDto.getF7();

        repository.insert(f1, f2, f3, f4, f5, f6, f7);

        model.addAttribute("objects", repository.findAll());
        return "objects/enrollee/all";
    }

    @PostMapping("/DELETE")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@ModelAttribute ObjectDto objectDto, Model model){
        int id = Integer.parseInt(objectDto.getId());

        repository.deleteById(id);

        model.addAttribute("objects", repository.findAll());
        return "objects/enrollee/all";
    }

    @ModelAttribute
    public void modelParameters(Model model){
        model.addAttribute("headers", HEADERS);
        model.addAttribute("update_headers", UPDATE_HEADERS);
        model.addAttribute("like_headers", LIKE_HEADERS);
        model.addAttribute("table", "enrollee");
    }
}
