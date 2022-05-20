package model;

public enum Tipo {
    COCHE("COCHE"),MOTO("MOTO"),BICICLETA("BICICLETA"),PATINETE("PATINETE");
    private String str;
    Tipo(String str){
        this.str=str;
    }

    public String getStr() {
        return str;
    }
}
