package com.teo.movieapp.constants;

public enum Grade {

    FAILURE (1),
    POOR (2),
    UNSATISFACTORY (3),
    AVERAGE (4),
    SATISFACTORY (5),
    GOOD (6),
    VERY_GOOD (7),
    EXCELLENT (8),
    SUPERIOR (9),
    OUTSTANDING (10);

    private final int value;

    Grade(int value) {
        this.value = value;
    }

    @Override
    public String toString() {

         return ""+value;
    }
}
