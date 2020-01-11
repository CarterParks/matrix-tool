import java.util.ArrayList;
import java.util.Scanner;


public abstract class Option {
    public abstract void call()/*throws IOException, InterruptedException*/;
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
    public String name(){
        return "Multiply";
    }
    public void call() {
        back();
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
        //TODO: Bad Input handling
        for (int i = 0; i < addable.size(); i++){
            System.out.printf("    (%d) Matrix %s%n", i + 1, addable.get(i).label);
        }

        System.out.print("Selection: ");

        double[][] addMat = addable.get(new Scanner(System.in).nextInt() - 1).values.clone();
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
        ArrayList<matrix> addable = new ArrayList<>();
        for (matrix m : tool.matrices) {
            if(m.rowNum == tool.current.rowNum && m.colNum == tool.current.colNum)
                addable.add(m);
        }

        System.out.printf("%nSubtract from Matrix %s:%n", tool.current.label);
        //TODO: Bad Input handling
        for (int i = 0; i < addable.size(); i++){
            System.out.printf("    (%d) Matrix %s%n", i + 1, addable.get(i).label);
        }

        System.out.print("Selection: ");

        double[][] addMat = addable.get(new Scanner(System.in).nextInt() - 1).values.clone();
        double[][] resVal  = new double[tool.current.rowNum][tool.current.colNum];
        for (int row = 0; row < tool.current.rowNum; row++) {
            for (int ent = 0; ent < tool.current.colNum; ent++) {
                resVal[row][ent] = tool.current.values[row][ent] - addMat[row][ent];
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
        System.out.printf("%nMultiply Matrix %s by how much: ", tool.current.label);
        double scalar = new Scanner(System.in).nextDouble();
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