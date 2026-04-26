package com.example.starsgallery.beans;

public class Star {
    private int id;
    private String name;
    private String role;
    private int imgRes;      // ← int au lieu de String
    private float star;
    private static int counter = 0;

    public Star(String name, String role, int imgRes, float star) {
        this.id = ++counter;
        this.name = name;
        this.role = role;
        this.imgRes = imgRes;
        this.star = star;
    }

    public int getId()       { return id; }
    public String getName()  { return name; }
    public String getRole()  { return role; }
    public int getImgRes()   { return imgRes; }   // ← getImgRes()
    public float getStar()   { return star; }

    public void setName(String name)   { this.name = name; }
    public void setRole(String role)   { this.role = role; }
    public void setImgRes(int imgRes)  { this.imgRes = imgRes; }
    public void setStar(float star)    { this.star = star; }
}