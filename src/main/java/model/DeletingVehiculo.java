package model;

public class DeletingVehiculo {
    private String matricula;
    private Tipo tipo;

    public DeletingVehiculo(String matricula, Tipo tipo) {
        this.matricula = matricula;
        this.tipo = tipo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
