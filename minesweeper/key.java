package minesweeper;

public class key {
    private boolean isBomb;
    private int eval;
    private boolean isPrint;

    public static void Initialization() {
        for (int i = 0; i < maps.Horizontal; i++) {
            for (int j = 0; j < maps.Vertical; j++) {
                maps.map[i][j] = new key();
                maps.map[i][j].isBomb = false;
                maps.map[i][j].isPrint = false;
                maps.map[i][j].eval = 0;
            }
        }
    }

    public void Print() {
        if (this.isPrint) {
            if (this.isBomb) {
                System.out.print('●');
            } else {
                if (this.eval == 0) {
                    System.out.print('□');
                } else {
                    System.out.print(this.eval);
                }
            }
        } else {
            System.out.print("■");
        }
    }

    public void ChoosePrint() {
        this.isPrint = true;
    }

    public boolean GainIsBomb() {
        return this.isBomb;
    }

    public boolean GainIsPrint() {
        return this.isPrint; 
    }

    public void SetIsBomb() {
        this.isBomb = true;
    }

    public int GainEval() {
        if (this.isBomb) {
            return 1;
        }
        return 0;
    }

    public void SetEval(int lock) {
        if (lock>=0 && lock<=4) {
            this.eval=lock;
        }else{
            System.out.println("计算错误");
        }
    }

}
