package com.example.api_crud_mock;

public class User {
    private String id;
    private String FIRSTNAME;
    private String LASTNAME;
    private String GENDER;
    private String SALARY;

    public User(String id, String FIRSTNAME, String LASTNAME, String GENDER, String SALARY) {
        this.id = id;
        this.FIRSTNAME = FIRSTNAME;
        this.LASTNAME = LASTNAME;
        this.GENDER = GENDER;
        this.SALARY = SALARY;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFIRSTNAME() {
        return FIRSTNAME;
    }

    public void setFIRSTNAME(String FIRSTNAME) {
        this.FIRSTNAME = FIRSTNAME;
    }

    public String getLASTNAME() {
        return LASTNAME;
    }

    public void setLASTNAME(String LASTNAME) {
        this.LASTNAME = LASTNAME;
    }

    public String getGENDER() {
        return GENDER;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }

    public String getSALARY() {
        return SALARY;
    }

    public void setSALARY(String SALARY) {
        this.SALARY = SALARY;
    }
}