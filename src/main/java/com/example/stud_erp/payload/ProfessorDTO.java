//package com.example.stud_erp.payload;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//public class ProfessorDTO {
//    private Long id;
//    private String name;
//
//    public ProfessorDTO(Long id, String name) {
//        this.id = id;
//        this.name = name;
//    }
//}



package com.example.stud_erp.payload;

public class ProfessorDTO {

    private Long id;
    private String name;

    public ProfessorDTO() {
    }

    public ProfessorDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}