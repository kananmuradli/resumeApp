package main;

import dao.UserDaoInter;
import dao.impl.UserDaoImpl;


public class Main {

    public static void main(String[] args) {
        UserDaoInter userDao= new UserDaoImpl();
    }
}
