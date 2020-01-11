import java.util.Scanner;
import java.io.IOException;



public abstract class Option {
    public abstract void call() throws IOException, InterruptedException;
    public abstract String name();
    public static void clear() {
        System.out.print("Press any key to return to menu: ");
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
        clear();
    }
}

class matrixNew extends Option{
    public String name(){
        return "New Matrix";
    }
    public void call() {
        tool.matrices.add(new matrix());
        clear();
    }
}

class matrixMultiply extends Option{
    public String name(){
        return "Multiply";
    }
    public void call() {
        clear();
    }
}

class matrixAdd extends Option{
    public String name(){
        return "Add";
    }
    public void call() {
        clear();
    }
}

class matrixSubtract extends Option{
    public String name(){
        return "Subtract";
    }
    public void call() {
        clear();
    }
}

class matrixScalar extends Option{
    public String name(){
        return "Multiply by Scalar";
    }
    public void call() {
        System.out.printf("    Multiply %s by how much: ", tool.current.name);
        double scalar = new Scanner(System.in).nextDouble();
        double[][] scalarReturn = new double[tool.current.rowNum][tool.current.colNum];

        for (int rowN = 0; rowN < tool.current.rowNum; rowN++) {
            for (int colN = 0; colN < tool.current.colNum; colN++) {
                 scalarReturn[rowN][colN] = tool.current.values[rowN][colN] * scalar;
            }
        }

        matrix result = new matrix(scalarReturn);
        System.out.println("    Result:");
        result.view();

        System.out.print("        Save? (Y/N) ");
        String choice = new Scanner(System.in).nextLine();
        if(choice.equals("Y") || choice.equals("y")){
            System.out.print("    Input matrix label: ");
            result.name = new Scanner(System.in).nextLine();
            tool.matrices.add(result);
            System.out.println("    Saved!");
        }

        clear();
    }
}

class view extends Option{
    @Override
    public String name() {
        return "View Matrix";
    }

    @Override
    public void call() {
        tool.current.view();
        clear();
    }

}

class exit extends Option{
    public String name(){return "Exit";}
    public void call(){tool.run = false;}
}