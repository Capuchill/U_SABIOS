package com.proyectito.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class DataInfo {
    public static final Map<String,Integer> meses;
    public static final Map<Integer, String> sexs = new LinkedHashMap<>();
    public static final Map<Integer, String> docType = new LinkedHashMap<>();

    static {
        meses = new LinkedHashMap<>();
        meses.put("Enero", 1);
        meses.put("Febrero", 2);
        meses.put("Marzo", 3);
        meses.put("Abril", 4);
        meses.put("Mayo", 5);
        meses.put("Junio", 6);
        meses.put("Julio", 7);
        meses.put("Agosto", 8);
        meses.put("Septiembre", 9);
        meses.put("Octubre", 10);
        meses.put("Noviembre", 11);
        meses.put("Diciembre", 12);
        sexs.put(1, "Male");
        sexs.put(2, "Female");
        docType.put(1, "DNI");
        docType.put(2, "FOREIGN_DNI");
        docType.put(3, "PASSPORT");
        docType.put(4, "TI");
    }

    private DataInfo(){}
}
