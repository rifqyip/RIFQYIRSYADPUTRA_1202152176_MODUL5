package com.example.android.rifqyirsyadputra_1202152176_modul5;

/**
 * Created by Rifqy on 25/03/2018.
 */

public class Todoitem {
    //Deklarasi Variable
    String task, desk, prior;

    public Todoitem(String task, String desk, String prior) {
        this.task = task;
        this.desk = desk;
        this.prior = prior;
    }

    //Setter & Getter

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDesk() {
        return desk;
    }

    public void setDesk(String desk) {
        this.desk = desk;
    }

    public String getPrior() {
        return prior;
    }

    public void setPrior(String prior) {
        this.prior = prior;
    }
}
