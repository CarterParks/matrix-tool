import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/*REMEMBER: double[][] is ONLY for in-method use!*/

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
        return String.format("Multiply Matrix %s by Matrix", Tool.current.label);
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
            for(int j = 0; j < resVal[0].length; j++){
                resVal[i][j] = cij(i,j, multA, multB);
            }
        }

        /*Outer Product method*/

        System.out.println("Result:");

        Matrix result = new Matrix(resVal);
        result.view();
        result.save();

        back();
    }

    private static double cij(int i, int j, double[][] multA, double[][] multB){
        double cumulative = 0;
        for(int k = 0; k < multA[0].length; k++){
            cumulative += multA[i][k] * multB[k][j];
        }
        return cumulative;
    }

    public static Matrix quiet(Matrix A, Matrix B){
        double[][] multA = A.values;

        double[][] multB = B.values;

        double[][] resVal = new double[multA.length][multB[0].length];

        for(int i = 0; i < resVal.length; i++){
            for(int j = 0; j < resVal[0].length; j++){
                resVal[i][j] = cij(i, j, multA, multB);
            }
        }

        return new Matrix(resVal);
    }
}

class MatrixAdd extends Option{
    public String name(){
        return "Add to Matrix";
    }
    public void call() {
        ArrayList<Matrix> addable = new ArrayList<>();
        for (Matrix m : Tool.matrices) {
            if(m.rowNum == Tool.current.rowNum && m.colNum == Tool.current.colNum && m != Tool.current)
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
        return String.format("Subtract from Matrix %s", Tool.current.label);
    }
    public void call() {
        ArrayList<Matrix> subbable = new ArrayList<>();
        for (Matrix m : Tool.matrices) {
            if(m.rowNum == Tool.current.rowNum && m.colNum == Tool.current.colNum && m != Tool.current)
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
        return "Multiply Matrix by Scalar";
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
        return String.format("View Matrix %s", Tool.current.label);
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

        System.out.printf("%nVector to Cross Multiply Vector %s with :%n", Tool.current.label);
        for (int i = 0; i < crossable.size(); i++){
            System.out.printf("    (%d) Vector %s%n", i + 1, crossable.get(i).label);
        }

        double[][] mA = Tool.current.values;
        double[][] mB = crossable.get(Tool.choice(crossable, "Selection: ")).values;

        double[] vA = vectorize(mA);
        double[] vB = vectorize(mB);

        double[][] resValue = new double[][] {
            {(vA[1]*vB[2])-(vA[2]*vB[1])},
            {(vA[2]*vB[0])-(vA[0]*vB[2])},
            {(vA[0]*vB[1])-(vA[1]*vB[0])}
        };

        System.out.println("Result:");
        Matrix result = new Matrix(resValue);
        result.view();
        result.save();
        back();
    }

    private static double[] vectorize(double[][] m){
        double[] n = new double[3];

        if(m.length == 1 && m[0].length == 3){
            n = m[0].clone();
        }else if(m.length == 3 && m[0].length == 1){
            for (int i = 0; i < 3; i++) {
                n[i] = m[i][0];
            }
        }

        return n;
    }
}

class Transpose extends Option{
    @Override
    public String name() {
        return String.format("Transpose Matrix %s", Tool.current.label);
    }

    @Override
    public void call() {
        System.out.println();

        double[][] resValue = new double[Tool.current.colNum][Tool.current.rowNum];

        for (int i = 0; i < Tool.current.rowNum; i++) {
            for (int j = 0; j < Tool.current.colNum; j++) {
                resValue[j][i] = Tool.current.values[i][j];
            }
        }

        System.out.printf("Matrix %s Transposed:%n", Tool.current.label);
        Matrix result = new Matrix(resValue);
        result.view();
        result.save();

        back();
    }

    public static Matrix quiet(Matrix m){
        double[][] resValue = new double[Tool.current.colNum][Tool.current.rowNum];

        for (int i = 0; i < Tool.current.rowNum; i++) {
            for (int j = 0; j < Tool.current.colNum; j++) {
                resValue[j][i] = Tool.current.values[i][j];
            }
        }

        return new Matrix(resValue);
    }
}

class UpperTri extends Option{
    @Override
    public String name() {
        return "Calculate Upper Triangular";
    }

    @Override
    public void call() {
        Matrix result = new Matrix(Tool.current.values);
        for (int piv = 0; piv < result.rowNum; piv++) {
            for (int subRow = piv + 1; subRow < result.colNum; subRow++) {
                rowSub(result, piv, subRow);
            }
        }

        System.out.printf("%nUpper Triangular for Matrix %s:%n", Tool.current.label);

        result.view();
        result.save();

        back();
    }

    public static void rowSub(Matrix m, int pivCol, int subRow){
        if(m.values[pivCol][pivCol] == 0)
            System.out.println("Warning: Pivot is 0.");
        double b = m.values[subRow][pivCol];
        double t = m.values[pivCol][pivCol];
        double fact = (b/t);
        for (int i = 0; i < m.values[pivCol].length; i++) {
            m.values[subRow][i] = m.values[subRow][i] - (m.values[pivCol][i] * fact);
        }
    }

    public static Matrix quiet() {
        Matrix result = new Matrix(Tool.current.values);
        for (int piv = 0; piv < result.rowNum; piv++) {
            for (int subRow = piv + 1; subRow < result.colNum; subRow++) {
                rowSub(result, piv, subRow);
            }
        }
        return result;
    }
}

class Determinant extends Option{
    @Override
    public String name() {
        return "Calculate Determinant";
    }

    @Override
    public void call() {
        Matrix u = UpperTri.quiet();

        double d = 1;
        for (int piv = 0; piv < u.rowNum; piv++) {
            d = d * u.values[piv][piv];
        }

        System.out.printf("%nDeterminant of Matrix %s: %2f%n", Tool.current.label, d);
        back();
    }
}

class Exit extends Option{
    public String name(){return "Exit";}
    public void call(){
        Tool.run = false;}
}