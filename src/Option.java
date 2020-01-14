import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.ToLongBiFunction;


public abstract class Option {
    public abstract void call();
    public abstract String name();
    public static void back() {
        System.out.print("Press ENTER to return to menu. ");
        new Scanner(System.in).nextLine();
        System.out.println();
    }
}

class MatrixChoose extends Option{
    public String name(){
        return "Change Matrix";
    }
    public void call() {
        Tool.current = Tool.chooser();
        back();
    }
}

class MatrixNew extends Option{
    public String name(){
        return "New Matrix";
    }
    public void call() {
        System.out.println();
        Tool.matrices.add(new Matrix());
        back();
    }
}

class MatrixMultiply extends Option{
    double[][] multA;
    double[][] multB;
    public String name(){
        return "Multiply by Matrix";
    }
    public void call() {
        ArrayList<Matrix> multable = new ArrayList<>();
        for( Matrix m : Tool.matrices){
            if(Tool.current.colNum == m.rowNum){
                multable.add(m);
            }
        }

        System.out.printf("%nMultiply Matrix %s By:%n", Tool.current.label);
        for (int i = 0; i < multable.size(); i++){
            System.out.printf("    (%d) Matrix %s%n", i + 1, multable.get(i).label);
        }

        multA = Tool.current.values.clone();

        multB = multable.get(Tool.choice(multable, "Select Matrix: ")).values.clone();

        double[][] resVal = new double[multA.length][multB[0].length];

        for(int i = 0; i < resVal.length; i++){
            for(int j = 0; j < resVal.length; j++){
                resVal[i][j] = cij(i,j);
            }
        }

        System.out.println("Result:");

        Matrix result = new Matrix(resVal);
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

class MatrixAdd extends Option{
    public String name(){
        return "Add to Matrix";
    }
    public void call() {
        ArrayList<Matrix> addable = new ArrayList<>();
        for (Matrix m : Tool.matrices) {
            if(m.rowNum == Tool.current.rowNum && m.colNum == Tool.current.colNum)
                addable.add(m);
        }

        System.out.printf("%nAdd to Matrix %s:%n", Tool.current.label);
        for (int i = 0; i < addable.size(); i++){
            System.out.printf("    (%d) Matrix %s%n", i + 1, addable.get(i).label);
        }

        double[][] addMat = addable.get(Tool.choice(addable, "Select Matrix: ")).values.clone();
        double[][] resVal  = new double[Tool.current.rowNum][Tool.current.colNum];
        for (int row = 0; row < Tool.current.rowNum; row++) {
            for (int ent = 0; ent < Tool.current.colNum; ent++) {
                resVal[row][ent] = Tool.current.values[row][ent] + addMat[row][ent];
            }
        }

        System.out.println("Result:");

        Matrix result = new Matrix(resVal);
        result.view();
        result.save();

        back();
    }
}

class MatrixSubtract extends Option{
    public String name(){
        return "Subtract from Matrix";
    }
    public void call() {
        ArrayList<Matrix> subbable = new ArrayList<>();
        for (Matrix m : Tool.matrices) {
            if(m.rowNum == Tool.current.rowNum && m.colNum == Tool.current.colNum)
                subbable.add(m);
        }

        System.out.printf("%nSubtract from Matrix %s:%n", Tool.current.label);
        //TODO: Bad Input handling
        for (int i = 0; i < subbable.size(); i++){
            System.out.printf("    (%d) Matrix %s%n", i + 1, subbable.get(i).label);
        }

        double[][] subMat = subbable.get(Tool.choice(subbable,"Select Matrix: ")).values.clone();
        double[][] resVal  = new double[Tool.current.rowNum][Tool.current.colNum];
        for (int row = 0; row < Tool.current.rowNum; row++) {
            for (int ent = 0; ent < Tool.current.colNum; ent++) {
                resVal[row][ent] = Tool.current.values[row][ent] - subMat[row][ent];
            }
        }

        System.out.println("Result:");

        Matrix result = new Matrix(resVal);
        result.view();
        result.save();

        back();
    }
}

class MatrixScalar extends Option{
    public String name(){
        return "Multiply by Scalar";
    }
    public void call() {

        double scalar;
        while (true){
            System.out.printf("%nMultiply Matrix %s by how much: ", Tool.current.label);
            try {
                scalar = new Scanner(System.in).nextDouble();
                break;
            }catch (InputMismatchException e){
                System.out.print("Please enter a number.");
            }

        }
        double[][] scalarReturn = new double[Tool.current.rowNum][Tool.current.colNum];

        for (int rowN = 0; rowN < Tool.current.rowNum; rowN++) {
            for (int colN = 0; colN < Tool.current.colNum; colN++) {
                 scalarReturn[rowN][colN] = Tool.current.values[rowN][colN] * scalar;
            }
        }

        Matrix result = new Matrix(scalarReturn);

        System.out.println("Result:");
        result.view();
        result.save();

        back();
    }
}

class View extends Option{
    @Override
    public String name() {
        return "View Matrix";
    }

    @Override
    public void call() {
        System.out.printf("%n(Matrix %s)%n", Tool.current.label);
        Tool.current.view();
        back();
    }
}

class CrossProduct extends Option{
    @Override
    public String name() {
        return "Cross Product";
    }

    @Override
    public void call() {
        ArrayList<Matrix> crossable = new ArrayList<>();
        for (Matrix m: Tool.matrices) {
            boolean _3x1 = m.rowNum == 3 && m.colNum == 1;
            boolean _1x3 = m.rowNum == 1 && m.colNum == 3;
            if(_3x1 || _1x3){
                crossable.add(m);
            }
        }

        System.out.println("Vector to Cross Multiply: ");
        for (int i = 0; i < crossable.size(); i++){
            System.out.printf("    (%d) Vector %s%n", i + 1, crossable.get(i).label);
        }

        double[][] vA = Tool.current.values;

        double[][] vB = crossable.get(Tool.choice(crossable, "Selection: ")).values;

        vA = rowToColumn(vA);
        vB = rowToColumn(vB);

        double A = vA[0][0]; double B =vA[1][0]; double C = vA[2][0];
        double D = vB[0][0]; double E =vB[1][0]; double F = vB[2][0];

        double[][] resValue = new double[][] {{B*F-E*C},{-(A*F-D*C)},{A*E-D*B}};
        Matrix result = new Matrix(resValue);
        result.view();
        result.save();

        back();
    }

    private static double[][] rowToColumn (double[][] m){
        double[][] ret = new double[3][1];
        if(m.length == 1){
            for(int i = 0; i < 3; i++){
                ret[i][0] = m[0][i];
            }
        }
        return ret;
    }

}

class Transpose extends Option{
    @Override
    public String name() {
        return "Transpose Matrix";
    }

    @Override
    public void call() {
        double[][] resValue = new double[Tool.current.colNum][Tool.current.rowNum];

        for (int i = 0; i < Tool.current.rowNum; i++) {
            for (int j = 0; j < Tool.current.colNum; j++) {
                resValue[j][i] = Tool.current.values[i][j];
            }
        }

        Matrix result = new Matrix(resValue);
        result.view();
        result.save();

        back();
    }

    /*public double[][] transpose(double[][] m){
        double[][] resValue = new double[m[0].length][m.length];

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                resValue[j][i] = m[i][j];
            }
        }
        return resValue;
    }*/
}

class exit extends Option{
    public String name(){return "Exit";}
    public void call(){
        Tool.run = false;}
}