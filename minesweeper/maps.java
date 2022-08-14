package minesweeper;

import java.util.Random;
import java.util.Scanner;
import java.io.IOException;

public class maps {

    private static Scanner input = new Scanner(System.in);

    private static int Difficult = 0;

    public static int Horizontal = 10;

    public static int Vertical = 30;

    public static key[][] map = new key[Horizontal][Vertical];

    private static void SetDifficult() {
        while (true) {
            System.out.println("请输入难度系数(1,5)");

            int number = input.nextInt();

            if (number > 0 && number < 6) {
                Difficult = number * 10;
                break;
            } else {
                System.out.println("输入错误,请重新输入");
            }
        }
    }

    private static void GainBomb() {
        int x, y;

        for (int i = 0; i < Difficult; i++) {
            x = new Random().nextInt(Horizontal);
            y = new Random().nextInt(Vertical);

            if (map[x][y].GainIsBomb() == false) {
                map[x][y].SetIsBomb();
                map[x][y].SetEval(1);
            } else {
                i--;
            }
        }
    }

    public static void PrintMap() throws IOException, InterruptedException {

        cls();

        for (int j = 0; j < Vertical + 4; j++) {
            if (j == 0) {
                System.out.print("*");
            } else if (j == Vertical + 3) {
                System.out.print("*");
            } else {
                System.out.print("=");
            }
        }
        System.out.print('\n');
        for (int i = 0; i < Vertical + 4; i++) {
            if (i == 0) {
                System.out.print("|");
            } else if (i == Vertical + 3) {
                System.out.print("|");
            } else if (i % 6 == 0 && i != Vertical) {
                System.out.print('.');
            } else {
                System.out.print(" ");
            }
        }
        System.out.print("\n");

        for (int i = 0; i < Horizontal; i++) {
            System.out.print("|");
            if (i % 3 == 0) {
                System.out.print(".");
            } else {
                System.out.print(" ");
            }
            for (int j = 0; j < Vertical; j++) {
                map[i][j].Print();
            }
            if (i % 3 == 0) {
                System.out.print(".");
            } else {
                System.out.print(" ");
            }
            System.out.print('|');
            System.out.print('\n');
        }

        for (int i = 0; i < Vertical + 4; i++) {
            if (i == 0) {
                System.out.print("|");
            } else if (i == Vertical + 3) {
                System.out.print("|");
            } else if (i % 6 == 0 && i != Vertical) {
                System.out.print('.');
            } else {
                System.out.print(" ");
            }
        }
        System.out.print("\n");
        for (int j = 0; j < Vertical + 4; j++) {
            if (j == 0) {
                System.out.print("*");
            } else if (j == Vertical + 3) {
                System.out.print("*");
            } else {
                System.out.print("=");
            }
        }
        System.out.print('\n');
    }

    public static boolean Input() throws IOException, InterruptedException {
        int x, y;
        while (true) {
            System.out.println("请输入所选位置的坐标(如: x y)");
            y = input.nextInt();
            x = input.nextInt();
            if ((x >= 0 && x <= Horizontal - 1) && (y >= 0 && y <= Vertical - 1)) {
                break;
            } else {
                System.out.println("输入错误,请重新输入");
                PrintMap();
            }
        }
        map[x][y].ChoosePrint();
        if (map[x][y].GainIsBomb()) {
            return false;
        }
        return true;
    }

    public static void Over() throws IOException, InterruptedException {
        PrintMap();

        for (int i = 0; i < 10; i++) {
            if (i<=5) {
                System.out.print("=");
            } else {
                System.out.print(" ");
            }
        }

        System.out.print("You Died");

        for (int i = 0; i < 12; i++) {
            if (i<=5) {
                System.out.print(" ");
            } else {
                System.out.print("=");
            }
        }

        input.close();
    }
    
    private static void Computer() {
        int process;
        for (int i = 1; i < Horizontal - 2; i++) {
            for (int j = 1; j < Vertical - 2; j++) {
                process = 0;
                process = map[i][j + 1].GainEval() + process;
                process = map[i][j - 1].GainEval() + process;
                process = map[i + 1][j].GainEval() + process;
                process = map[i - 1][j].GainEval() + process;
                map[i][j].SetEval(process);
            }
        }

        map[0][0].SetEval(map[0][1].GainEval() + map[1][0].GainEval());
        map[0][Vertical - 1].SetEval(map[0][Vertical - 2].GainEval() + map[1][Vertical - 1].GainEval());
        map[Horizontal - 1][0].SetEval(map[Horizontal - 1][1].GainEval() + map[Horizontal - 2][0].GainEval());
        map[Horizontal - 1][Vertical - 1]
                .SetEval(map[Horizontal - 1][Vertical - 2].GainEval() + map[Horizontal - 2][Vertical - 1].GainEval());

        for (int i = 1; i < Vertical - 2; i++) {
            map[0][i].SetEval(map[0][i + 1].GainEval() + map[0][i - 1].GainEval() + map[1][i].GainEval());
            map[Horizontal - 1][i].SetEval(map[Horizontal - 1][i + 1].GainEval() + map[Horizontal - 1][i - 1].GainEval()
                    + map[Horizontal - 2][i].GainEval());
        }

        for (int i = 1; i < Horizontal - 2; i++) {
            map[i][0].SetEval(map[i + 1][0].GainEval() + map[i - 1][0].GainEval() + map[i][1].GainEval());
            map[i][Vertical - 1].SetEval(map[i + 1][Vertical - 1].GainEval() + map[i - 1][Vertical - 1].GainEval()
                    + map[i][Vertical - 2].GainEval());
        }
    }

    public static boolean JudgmentWin() {
        for (int i = 0; i < Horizontal; i++) {
            for (int j = 0; j < Vertical; j++) {
                if (map[i][j].GainIsPrint()==false && map[i][j].GainIsBomb()==false) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void INITIALIZATION() {
        key.Initialization();
        SetDifficult();
        GainBomb();
        Computer();
    }

    public static void WinGame() throws IOException, InterruptedException {

        PrintMap();

        for (int i = 0; i < 10; i++) {
            if (i<=5) {
                System.out.print("=");
            } else {
                System.out.print(" ");
            }
        }

        System.out.print("You Win");

        for (int i = 0; i < 12; i++) {
            if (i<=5) {
                System.out.print(" ");
            } else {
                System.out.print("=");
            }
        }

        input.close();
    }

    public static void cls() throws IOException, InterruptedException {

        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

    }
}
