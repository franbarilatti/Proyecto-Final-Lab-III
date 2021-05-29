package repositories;

import com.google.gson.Gson;
import users.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends RepositoryController {

    private User user;
    private ArrayList<User> userList;

    public UserRepository(User user, ArrayList<User> userList) {
        this.user = user;
        this.userList = userList;
    }

    public User getUser() {
        return user;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }


    @Override
    public void add(Object o) {

    }

    @Override
    public void remove(Object o) {

    }

    @Override
    public void show(Object o) {

    }

    @Override
    public void modify(Object o) {

    }

    @Override
    public void findById(Object o) {

    }
}
