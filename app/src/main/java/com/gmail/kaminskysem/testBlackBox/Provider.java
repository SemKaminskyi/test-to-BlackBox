package com.gmail.kaminskysem.testBlackBox;

import com.gmail.kaminskysem.testBlackBox.Data.Model;
import com.gmail.kaminskysem.testBlackBox.Data.UserDAO;

import java.util.ArrayList;
import java.util.List;

public class Provider implements UserDAO {
    private final List <Model> list = new ArrayList<>();


    @Override
    public Model getGameId(int gameId) {
        return list.get(gameId);
    }

    @Override
    public Model getID(int id) {
        return list.get(id);
    }

    @Override
    public List<Model> getTaskList() {
        return list;
    }

    @Override
    public void insert(Model model) {
        list.add(model);
    }

}
