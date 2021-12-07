package com.romanova.bd.controller;

import com.romanova.bd.dto.ObjectDto;
import com.romanova.bd.repository.ConsultantTeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/consultant_teacher")
@AllArgsConstructor
public class ConsultantTeacherController {
    private static final List<String> HEADERS =
            List.of("id_teacher", "full_name", "department", "institute");

    private static final List<String> LIKE_HEADERS =
            List.of("full_name", "department", "institute");

    private static final List<String> UPDATE_HEADERS =
            List.of("full_name", "department", "institute");

    private final ConsultantTeacherRepository repository;


    @GetMapping("/action/{action}")
    public String getAll(Model model, @PathVariable String action){
        model.addAttribute("action", action);
        model.addAttribute("object", new ObjectDto());

        switch (action){
            case "SELECT_ALL":
                model.addAttribute("objects", repository.findAll());
                return "objects/consultant_teacher/all";
            case "SELECT_BY_ID":
            case "SELECT_LIKE":
            case "SELECT_WHERE":
            case "ORDER_BY":
            case "UPDATE":
            case "DELETE":
                return "input";
            case "INSERT":
                return "objects/consultant_teacher/insert";
        }

        return null;
    }

    @PostMapping("/SELECT_BY_ID")
    public String getById(@ModelAttribute ObjectDto objectDto, Model model){
        Integer id = Integer.parseInt(objectDto.getId());
        model.addAttribute("objects",  repository.getById(id));
        return "objects/consultant_teacher/all";
    }

    @PostMapping("/SELECT_LIKE")
    public String getObjectsLike(@ModelAttribute ObjectDto objectDto, Model model){
        String like = objectDto.getLike();
        String field = objectDto.getField();

        switch (field){
            case "full_name":
                model.addAttribute("objects", repository.getObjectsWhereFullNameLike(like));
                break;
            case "department":
                model.addAttribute("objects", repository.getObjectsWhereDepartmentLike(like));
                break;
            case "institute":
                model.addAttribute("objects", repository.getObjectsWhereInstituteLike(like));
                break;
        }

        return "objects/consultant_teacher/all";
    }

    @PostMapping("/SELECT_WHERE")
    public String getObjectsWhere(@ModelAttribute ObjectDto objectDto, Model model){
        String field = objectDto.getField();
        String value = objectDto.getValue();
        switch (field){
            case "id_teacher":
                model.addAttribute("objects", repository.getObjectsWhereIdEquals(Integer.parseInt(value)));
            case "full_name":
                model.addAttribute("objects", repository.getObjectsWhereFullNameEquals(value));
                break;
            case "department":
                model.addAttribute("objects", repository.getObjectsWhereDepartmentEquals(value));
                break;
            case "institute":
                model.addAttribute("objects", repository.getObjectsWhereInstituteEquals(value));
                break;
        }
        return "objects/consultant_teacher/all";
    }

    @PostMapping("/ORDER_BY")
    public String getObjectsOrderBy(@ModelAttribute ObjectDto objectDto, Model model){
        String field = objectDto.getField();
        String order = objectDto.getOrder();
        switch (field){
            case "id_teacher":
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
            case "department":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderByDepartment());
                else
                    model.addAttribute("objects", repository.getObjectsOrderByDepartmentDesc());
                break;
            case "institute":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderByInstitute());
                else
                    model.addAttribute("objects", repository.getObjectsOrderByInstituteDesc());
                break;

        }
        return "objects/consultant_teacher/all";
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
            case "department":
                repository.updateDepartment(value, id);
                break;
            case "institute":
                repository.updateInstitute(value, id);
                break;
        }
        model.addAttribute("objects", repository.findAll());
        return "objects/consultant_teacher/all";
    }

    @PostMapping("/INSERT")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String insert(@ModelAttribute ObjectDto objectDto, Model model){
        String f1 = objectDto.getF1();
        String f2 = objectDto.getF2();
        String f3 = objectDto.getF3();
        String f4 = objectDto.getF4();

        repository.insert(f1, f2, f3, Integer.parseInt(f4));

        model.addAttribute("objects", repository.findAll());
        return "objects/consultant_teacher/all";
    }

    @PostMapping("/DELETE")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@ModelAttribute ObjectDto objectDto, Model model){
        int id = Integer.parseInt(objectDto.getId());

        repository.deleteById(id);

        model.addAttribute("objects", repository.findAll());
        return "objects/consultant_teacher/all";
    }

    @ModelAttribute
    public void modelParameters(Model model){
        model.addAttribute("headers", HEADERS);
        model.addAttribute("update_headers", UPDATE_HEADERS);
        model.addAttribute("like_headers", LIKE_HEADERS);
        model.addAttribute("table", "consultant_teacher");
    }
}
