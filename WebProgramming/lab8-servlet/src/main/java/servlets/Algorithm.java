package servlets;

public class Algorithm {

    private int[][] array;

    public Algorithm(int [][] array){
        this.array=array;
    }

    public boolean win(){
        int i=0;
        if(array[i][i]!=0 && array[i][i]==array[i+1][i+1] && array[i+1][i+1]==array[i+2][i+2]){
            return true;
        }

        if(array[0][2]!=0 && array[0][2]==array[1][1] && array[1][1]==array[2][0]){
            return true;
        }
        for (int j=0;j<3;j++){
            if(array[j][0] !=0 && array[j][0]==array[j][1] && array[j][1]==array[j][2])
                return true;
        }

        for (int j=0;j<3;j++){
            if(array[0][j] !=0 && array[0][j]==array[1][j] && array[1][j]==array[2][j]){
                return true;
            }

        }

        return false;

    }
}
