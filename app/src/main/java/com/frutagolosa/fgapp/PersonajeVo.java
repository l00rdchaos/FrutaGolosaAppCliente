package com.frutagolosa.fgapp;

public class PersonajeVo {

  private String nombre;
  private String info;

  private int imagenId;

  public PersonajeVo( String nombre, String info, int imagenId) {

    this.imagenId = imagenId;
    this.info=info;
    this.nombre=nombre;

  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }


  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }


  public int getImagenId() {
    return imagenId;
  }

  public void setImagenId(int imagenId) {
    this.imagenId = imagenId;
  }
}