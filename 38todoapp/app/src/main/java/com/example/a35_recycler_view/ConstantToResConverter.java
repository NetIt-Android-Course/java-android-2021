package com.example.a35_recycler_view;

import com.example.a35_recycler_view.data.Todo;

public class ConstantToResConverter {

    public static int getDifficultyColor(Todo task) {
        int difficultyColor = 0;
        switch (task.getDifficulty()) {
            case Todo.Difficulty.LOW:
                difficultyColor = R.color.easy_green;
                break;
            case Todo.Difficulty.MEDIUM:
                difficultyColor = R.color.medium_yellow;
                break;
            case Todo.Difficulty.HIGH:
                difficultyColor = R.color.hard_red;
                break;
        }
        return difficultyColor;
    }
}
