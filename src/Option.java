import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public abstract class Option {
    public abstract void call();
    public abstract String name();
    public static void back() {
        System.out.print("Press ENTER to return to menu. ");
        new Scanner(System.in).nextLine();
        System.out.println();
    }
}

class matrixChoose extends Option{
    public String name(){
        return "Change Matrix";
    }
    public void call() {
        tool.current = tool.chooser();
        back();
    }
}

class matrixNew extends Option{
    public String name(){
        return "New Matrix";
    }
    public void call() {
        System.out.println();
        tool.matrices.add(new matrix());
        back();
    }
}

class matrixMultiply extends Option{
    double[][] multA;
    double[][] multB;
    public String name(){
        return "Multiply by Matrix";
    }
    public void call() {
        ArrayList<matrix> multable = new ArrayList<>();
        for( matrix m : tool.matrices){
            if(tool.current.colNum == m.rowNum){
                multable.add(m);
            }
        }

        System.out.printf("%nMultiply Matrix %s By:%n", tool.current.label);
        //TODO: Bad Input handling
        for (int i = 0; i < multable.size(); i++){
            System.out.printf("    (%d) Matrix %s%n", i + 1, multable.get(i).label);
        }

        multA = tool.current.values;

        multB = multable.get(tool.choice(multable, "Select Matrix: ")).values;

        double[][] resVal = new double[multA.length][multB[0].length];

        for(int i = 0; i < resVal.length; i++){
            for(int j = 0; j < resVal.length; j++){
                resVal[i][j] = cij(i,j);
            }
        }

        System.out.println("Result:");

        matrix result = new matrix(resVal);
        result.view();
        result.save();

        back();
    }

    private double cij(int i, int j){
        double cumulative = 0;
        for(int k = 0; k < this.multA[0].length; k++){
            cumulative += multA[i][k] * multB[k][j];
        }
        return cumulative;
    }
}

class matrixAdd extends Option{
    public String name(){
        return "Add to Matrix";
    }
    public void call() {
        ArrayList<matrix> addable = new ArrayList<>();
        for (matrix m : tool.matrices) {
            if(m.rowNum == tool.current.rowNum && m.colNum == tool.current.colNum)
                addable.add(m);
        }

        System.out.printf("%nAdd to Matrix %s:%n", tool.current.label);
        for (int i = 0; i < addable.size(); i++){
            System.out.printf("    (%d) Matrix %s%n", i + 1, addable.get(i).label);
        }

        double[][] addMat = addable.get(tool.choice(addable, "Select Matrix: ")).values.clone();
        double[][] resVal  = new double[tool.current.rowNum][tool.current.colNum];
        for (int row = 0; row < tool.current.rowNum; row++) {
            for (int ent = 0; ent < tool.current.colNum; ent++) {
                resVal[row][ent] = tool.current.values[row][ent] + addMat[row][ent];
            }
        }

        System.out.println("Result:");

        matrix result = new matrix(resVal);
        result.view();
        result.save();

        back();
    }
}

class matrixSubtract extends Option{
    public String name(){
        return "Subtract from Matrix";
    }
    public void call() {
        ArrayList<matrix> subbable = new ArrayList<>();
        for (matrix m : tool.matrices) {
            if(m.rowNum == tool.current.rowNum && m.colNum == tool.current.colNum)
                subbable.add(m);
        }

        System.out.printf("%nSubtract from Matrix %s:%n", tool.current.label);
        //TODO: Bad Input handling
        for (int i = 0; i < subbable.size(); i++){
            System.out.printf("    (%d) Matrix %s%n", i + 1, subbable.get(i).label);
        }

        System.out.print("Selection: ");

        double[][] subMat = subbable.get(tool.choice(subbable,"Select Matrix: ")).values.clone();
        double[][] resVal  = new double[tool.current.rowNum][tool.current.colNum];
        for (int row = 0; row < tool.current.rowNum; row++) {
            for (int ent = 0; ent < tool.current.colNum; ent++) {
                resVal[row][ent] = tool.current.values[row][ent] - subMat[row][ent];
            }
        }

        System.out.println("Result:");

        matrix result = new matrix(resVal);
        result.view();
        result.save();

        back();
    }
}

class matrixScalar extends Option{
    public String name(){
        return "Multiply by Scalar";
    }
    public void call() {

        double scalar;
        while (true){
            System.out.printf("%nMultiply Matrix %s by how much: ", tool.current.label);
            try {
                scalar = new Scanner(System.in).nextDouble();
                break;
            }catch (InputMismatchException e){
                System.out.print("Please enter a number.");
            }

        }
        double[][] scalarReturn = new double[tool.current.rowNum][tool.current.colNum];

        for (int rowN = 0; rowN < tool.current.rowNum; rowN++) {
            for (int colN = 0; colN < tool.current.colNum; colN++) {
                 scalarReturn[rowN][colN] = tool.current.values[rowN][colN] * scalar;
            }
        }

        matrix result = new matrix(scalarReturn);

        System.out.println("Result:");
        result.view();
        result.save();

        back();
    }
}

class view extends Option{
    @Override
    public String name() {
        return "View Matrix";
    }

    @Override
    public void call() {
        System.out.printf("%n(Matrix %s)%n", tool.current.label);
        tool.current.view();
        back();
    }

}

class exit extends Option{
    public String name(){return "Exit";}
    public void call(){tool.run = false;}
}