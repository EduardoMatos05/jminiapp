package com.jminiapp.examples.tictactoe;

import com.jminiapp.core.adapters.JSONAdapter;

/**
 * JSON adapter for TicTacToeState objects.
 *
 * <p>This adapter enables the TicTacToe app to import and export game state
 * to/from JSON files. It leverages the framework's JSONAdapter interface which
 * provides default implementations for serialization using Gson.</p>
 */
public class TicTacToeJSONAdapter implements JSONAdapter<TicTacToeState> {

    @Override
    public Class<TicTacToeState> getstateClass() {
        return TicTacToeState.class;
    }
}
