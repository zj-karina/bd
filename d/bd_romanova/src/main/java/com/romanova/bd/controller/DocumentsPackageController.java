package com.romanova.bd.controller;

import com.romanova.bd.dto.ObjectDto;
import com.romanova.bd.repository.DocumentsPackageRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/package_of_documents")
@AllArgsConstructor
public class DocumentsPackageController {
    private static final List<String> HEADERS =
            List.of("id_enrollee", "photo", "medical_certificate", "IA_documents", "way_of_filling");

    private static final List<String> UPDATE_HEADERS =
            List.of("photo", "medical_certificate", "IA_documents", "way_of_filling");

    private static final List<String> LIKE_HEADERS =
            List.of("way_of_filling");


    private final DocumentsPackageRepository repository;

    @GetMapping("/action/{action}")
    public String getAll(Model model, @PathVariable String action){
        model.addAttribute("action", action);
        model.addAttribute("object", new ObjectDto());

        switch (action){
            case "SELECT_ALL":
                model.addAttribute("objects", repository.findAll());
                return "objects/package_of_documents/all";
            case "SELECT_BY_ID":
            case "SELECT_LIKE":
            case "SELECT_WHERE":
            case "ORDER_BY":
            case "UPDATE":
            case "DELETE":
                return "input";
            case "INSERT":
                return "objects/package_of_documents/insert";
        }

        return null;
    }

    @PostMapping("/SELECT_BY_ID")
    public String getById(@ModelAttribute ObjectDto objectDto, Model model){
        Integer id = Integer.parseInt(objectDto.getId());
        model.addAttribute("objects",  repository.getById(id));
        return "objects/package_of_documents/all";
    }

    @PostMapping("/SELECT_LIKE")
    public String getObjectsLike(@ModelAttribute ObjectDto objectDto, Model model){
        String like = objectDto.getLike();
        String field = objectDto.getField();
        if(field.equals("way_of_filling")){
            model.addAttribute("objects",  repository.getObjectsFillingWayLike(like));
        }
        else {
            model.addAttribute("objects",  List.of());
        }
        return "objects/package_of_documents/all";
    }

    @PostMapping("/SELECT_WHERE")
    public String getObjectsWhere(@ModelAttribute ObjectDto objectDto, Model model){
        String field = objectDto.getField();
        String value = objectDto.getValue();
        switch (field){
            case "id_enrollee":
                model.addAttribute("objects", repository.getObjectsWhereIdEquals(Integer.parseInt(value)));
                break;
            case "photo":
                model.addAttribute("objects", repository.getObjectsWherePhotoEquals(value));
                break;
            case "medical_certificate":
                model.addAttribute("objects", repository.getObjectsWhereMedicalCertificateEquals(value));
                break;
            case "IA_documents":
                model.addAttribute("objects", repository.getObjectsWhereIADocumentsEquals(value));
                break;
            case "way_of_filling":
                model.addAttribute("objects", repository.getObjectsWhereWayOfFillingEquals(value));
                break;
        }
        return "objects/package_of_documents/all";
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
            case "photo":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderByPhoto());
                else
                    model.addAttribute("objects", repository.getObjectsOrderByPhotoDesc());
                break;
            case "medical_certificate":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderByMedicalCertificate());
                else
                    model.addAttribute("objects", repository.getObjectsOrderByMedicalCertificateDesk());
                break;
            case "IA_documents":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderByIADocuments());
                else
                    model.addAttribute("objects", repository.getObjectsOrderByIADocumentsDesk());
                break;
            case "way_of_filling":
                if(order.equals("ASC"))
                    model.addAttribute("objects", repository.getObjectsOrderByFillingWay());
                else
                    model.addAttribute("objects", repository.getObjectsOrderByFillingWayDesk());
                break;
        }
        return "objects/package_of_documents/all";
    }

    @PostMapping("/UPDATE")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String update(@ModelAttribute ObjectDto objectDto, Model model){
        Integer id = Integer.parseInt(objectDto.getId());
        String field = objectDto.getField();
        String value = objectDto.getValue();
        switch (field){
            case "photo":
                if(value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("no"))
                    repository.updatePhoto(value, id);
                break;
            case "medical_certificate":
                if(value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("no"))
                    repository.updateCertificate(value, id);
                break;
            case "IA_documents":
                if(value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("no"))
                    repository.updateIADocuments(value, id);
                break;
            case "way_of_filling":
                repository.updateWay(value, id);
                break;
        }
        model.addAttribute("objects", repository.findAll());
        return "objects/package_of_documents/all";
    }

    @PostMapping("/INSERT")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String insert(@ModelAttribute ObjectDto objectDto, Model model){
        Integer id = Integer.parseInt(objectDto.getId());
        String f1 = objectDto.getF1();
        String f2 = objectDto.getF2();
        String f3 = objectDto.getF3();
        String f4 = objectDto.getF4();
        String f5 = objectDto.getF5();

        if(!f2.equalsIgnoreCase("yes") && f2.equalsIgnoreCase("no")){
            f2 = "No";
        }

        if(!f3.equalsIgnoreCase("yes") && f3.equalsIgnoreCase("no")){
            f3 = "No";
        }

        if(!f4.equalsIgnoreCase("yes") && f4.equalsIgnoreCase("no")){
            f4 = "No";
        }

        repository.insert(id, f1, f2, f3, f4, Integer.parseInt(f5));

        model.addAttribute("objects", repository.findAll());
        return "objects/package_of_documents/all";
    }

    @PostMapping("/DELETE")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String delete(@ModelAttribute ObjectDto objectDto, Model model){
        int id = Integer.parseInt(objectDto.getId());

        repository.deleteById(id);

        model.addAttribute("objects", repository.findAll());
        return "objects/package_of_documents/all";
    }

    @ModelAttribute
    public void modelParameters(Model model){
        model.addAttribute("headers", HEADERS);
        model.addAttribute("update_headers", UPDATE_HEADERS);
        model.addAttribute("like_headers", LIKE_HEADERS);
        model.addAttribute("table", "package_of_documents");
    }
}
