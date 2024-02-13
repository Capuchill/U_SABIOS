package com.unisabio.repository.models.models_main;

import lombok.Data;

@Data

public class People {
    private int idPerson;
    private String dniType;
    private Long dniNumber;
    private String pName;
    private String sName;
    private String pLastName;
    private String sLastName;
    private String residingCity;
    private String addressHome;
    private String phone;
    private String bornDate;
    private String sex;

    public People(
            String dniType, Long dniNumber, String pName, String sName,
            String pLastName, String sLastName
        ) {

        this.dniType = dniType;
        this.dniNumber = dniNumber;
        this.pName = pName;
        this.sName = sName;
        this.pLastName = pLastName;
        this.sLastName = sLastName;
        
    }

    public People(
            int idPerson, String dniType, Long dniNumber, String pName, 
            String sName, String pLastName, String sLastName
        ) {

        this.idPerson = idPerson;
        this.dniType = dniType;
        this.dniNumber = dniNumber;
        this.pName = pName;
        this.sName = sName;
        this.pLastName = pLastName;
        this.sLastName = sLastName;
        
    }

    public void setInfoAdicional(
            String residingCity, String addressHome,
            String phone, String bornDate, String sex
        ) {

        this.residingCity = residingCity;
        this.addressHome = addressHome;
        this.phone = phone;
        this.bornDate = bornDate;
        this.sex = sex;

    }
    
}
