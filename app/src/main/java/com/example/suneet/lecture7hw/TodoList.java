package com.example.suneet.lecture7hw;

import java.io.Serializable;

/**
 * Created by suneet on 26/6/17.
 */

public class TodoList  implements Serializable{
    String item;
    boolean status;

    public TodoList(String item, boolean status) {
        this.item = item;
        this.status = status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getItem() {
        return item;

    }

    public boolean isStatus() {
        return status;
    }
}
