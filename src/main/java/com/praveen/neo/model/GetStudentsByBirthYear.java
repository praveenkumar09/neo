package com.praveen.neo.model;

import java.util.List;

public class GetStudentsByBirthYear {
    private List<Integer> birthYear;

    public List<Integer> getBirthYear() {
        return birthYear;
    }
    public void setBirthYear(List<Integer> birthYear) {
        this.birthYear = birthYear;
    }

    @Override
    public String toString() {
        return "GetStudentsByBirthYear{" +
                "birthYear=" + birthYear +
                '}';
    }
}
