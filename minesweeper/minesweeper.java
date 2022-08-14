package minesweeper;

import java.io.IOException;

public class minesweeper {

    public static void main(String[] args) throws IOException, InterruptedException {
        maps.INITIALIZATION();

        while (true) {

            maps.PrintMap();

            if (maps.Input()) {

                if (maps.JudgmentWin()) {
                    maps.WinGame();
                    break;
                }

            } else {
                maps.Over();
                break;
            }
        }
    }
}
