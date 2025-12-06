package com.jminiapp.examples.tictactoe;

import com.jminiapp.core.engine.JMiniAppRunner;

public class TicTacToeAppRunner {

    public static void main(String[] args) {
        JMiniAppRunner
                .forApp(TicTacToeApp.class)
                .withState(TicTacToeState.class)
                .withAdapters(new TicTacToeJSONAdapter())
                .named("TicTacToe")
                .run(args);
    }
}
