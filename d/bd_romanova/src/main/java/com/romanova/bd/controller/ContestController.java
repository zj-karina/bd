package com.romanova.bd.controller;

import com.romanova.bd.dto.ObjectDto;
import com.romanova.bd.repository.ContestRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/contest")
@AllArgsConstructor
public class ContestController {
    private static final List<String> HEADERS =
            List.of("number_of_contest", "passing_score", "year_of_contest");

    private static final List<String> UPDATE_HEADERS =
            List.of("passing_score", "year_of_contest");

    private static final List<String> LIKE_HEADERS =
            List.of();

    private final ContestRepository repository;

    @GetMapping("/action/{action}")
    public String getAll(Model model, @PathVariable String action){
        model.addAttribute("action", action);
        model.addAttribute("object", new ObjectDto());

        switch (action){
            case "SELECT_ALL":
                model.addAttribute("objects", repository.findAll());
                return "objects/contest/all";
            case "SELECT_BY_ID":
            case "SELECT_LIKE":
            case "SELECT_WHERE":
            case "ORDER_BY":
            case "UPDATE":
            case "DELETE":
                return "input";
            case "INSERT":
                return "objects/contest/insert";
        }

        return null;
    }

    @PostMapping("/SELECT_BY_ID")
    public String getById(@ModelAttribute ObjectDto objectDto, Model model){
        Integer id = Integer.parseInt(objectDto.getId());
        model.addAttribute("objects",  repository.getById(id));
        return "objects/contest/all";
    }

    @PostMapping("/SELECT_LIKE")
    public String getObjectsLike(@ModelAttribute ObjectDto objectDto, Model model){
        model.addAttribute("objects",  List.of());
        return "objects/contest/all";
    }

    @PostMapping("/SELECT_WHERE")
    public String getObjectsWhere(@ModelAttribute ObjectDto objectDto, Model model){
        String field = objectDto.getField();
        Integer value = Integer.parseInt(objectDto.getValue());
        switch (field){
            case "number_of_contest":
                model.addAttribute("objects", repository.getObjectsWhereIdEquals(value));
                break;
            case "passing_score":
                model.addAttribute("objects", repository.getObjectsWherePassingScoreEquals(value));
                break;
            case "year_of_contest":
                model.addAttribute("objects", repository.getObjectsWhereYearEquals(value));
                break;
        }
        return "objects/contest/all";
    }

    @PostMapping("/ORDER_BY")
    public String getObjectsOrderBy(@ModelAttribute ObjectDto objectDto, Model model){
        String field = objectDto.getField();
        String order = objectDto.getOrder();
        switch (field){
            case "number_of_contest":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderById());
                else
                    model.addAttribute("objects", repository.getObjectsOrderByIdDesk());
                break;
            case "passing_score":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderByScore());
                else
                    model.addAttribute("objects", repository.getObjectsOrderByScoreDesk());
                break;
            case "year_of_contest":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderByYear());
                else
                    model.addAttribute("objects", repository.getObjectsOrderByYearDesc());
                break;
        }
        return "objects/contest/all";
    }

    @PostMapping("/UPDATE")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String update(@ModelAttribute ObjectDto objectDto, Model model){
        Integer id = Integer.parseInt(objectDto.getId());
        String field = objectDto.getField();
        String value = objectDto.getValue();
        switch (field){
            case "passing_score":
                repository.updateScore(value, id);
                break;
            case "year_of_contest":
                repository.updateYear(value, id);
                break;
        }
        model.addAttribute("objects", repository.findAll());
        return "objects/contest/all";
    }

    @PostMapping("/INSERT")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String insert(@ModelAttribute ObjectDto objectDto, Model model){
        String f1 = objectDto.getF1();
        String f2 = objectDto.getF2();
        String f3 = objectDto.getF3();
        String f4 = objectDto.getF4();
        String f5 = objectDto.getF5();


        repository.insert(f1, f2, f3, f4, f5);

        model.addAttribute("objects", repository.findAll());
        return "objects/contest/all";
    }

    @PostMapping("/DELETE")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@ModelAttribute ObjectDto objectDto, Model model){
        int id = Integer.parseInt(objectDto.getId());

        repository.deleteById(id);

        model.addAttribute("objects", repository.findAll());
        return "objects/contest/all";
    }

    @ModelAttribute
    public void modelParameters(Model model){
        model.addAttribute("headers", HEADERS);
        model.addAttribute("update_headers", UPDATE_HEADERS);
        model.addAttribute("like_headers", LIKE_HEADERS);
        model.addAttribute("table", "contest");
    }
}
