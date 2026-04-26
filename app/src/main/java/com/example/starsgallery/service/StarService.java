package com.example.starsgallery.service;

import com.example.starsgallery.R;
import com.example.starsgallery.beans.Star;
import com.example.starsgallery.dao.IDao;
import java.util.ArrayList;
import java.util.List;

public class StarService implements IDao<Star> {

    private List<Star> stars;
    private static StarService instance;

    private StarService() {
        stars = new ArrayList<>();
        seed();
    }

    public static StarService getInstance() {
        if (instance == null) instance = new StarService();
        return instance;
    }

    private void seed() {
        stars.add(new Star("Damson Idris",         "Franklin Saint",            R.mipmap.franklin,   4.9f));
        stars.add(new Star("Carter Hudson",          "Teddy McDonald", R.mipmap.teddy,   4.8f));
        stars.add(new Star("Alon Abutbul",         "Avi Drexler",   R.mipmap.avi,     4.7f));
        stars.add(new Star("Angela Lewis",            "Aunt Louie",   R.mipmap.louie,     4.5f));
        stars.add(new Star("Isaiah John",        "Leon Simmons",         R.mipmap.leon,       4.6f));
        stars.add(new Star("Emily Rios", "Lucia Villanueva",    R.mipmap.lucia,      4.4f));
        stars.add(new Star("Marcus Henderson",          "Andre Wright",        R.mipmap.andre,      4.3f));
        stars.add(new Star("Amin Joseph",        "Jerome Saint",         R.mipmap.jerome, 4.2f));
    }

    @Override
    public boolean create(Star o) { return stars.add(o); }

    @Override
    public boolean update(Star o) {
        for (Star s : stars) {
            if (s.getId() == o.getId()) {
                s.setName(o.getName());
                s.setRole(o.getRole());
                s.setImgRes(o.getImgRes());
                s.setStar(o.getStar());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(Star o) { return stars.remove(o); }

    @Override
    public Star findById(int id) {
        for (Star s : stars) if (s.getId() == id) return s;
        return null;
    }

    @Override
    public List<Star> findAll() { return stars; }
}