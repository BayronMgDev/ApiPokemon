package com.proyectofinal.pokemonapi.Model;

public class NextEvolution {
    private String name;
    private String num;

    public NextEvolution() {
    }

    public NextEvolution(String name, String num) {
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
