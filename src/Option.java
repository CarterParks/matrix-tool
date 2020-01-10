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
        double[][] work = tool.current.values;
        for (double[] row : work) {
            for (double entry : row)
                System.out.print(" " + entry);
            System.out.println();
        }
        clear();
    }

}

class exit extends Option{
    public String name(){return "Exit";}
    public void call(){tool.run = false;}
}