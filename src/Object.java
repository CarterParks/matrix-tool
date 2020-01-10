import java.util.Scanner;
import java.io.IOException;



abstract class Option {
    public abstract void call() throws IOException, InterruptedException;
    public abstract String name();
    public static void clear() {
        System.out.print("Press Any key to return to Menu: ");
        new Scanner(System.in).nextLine();
        System.out.println();
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

class matrixChange extends Option{
    public String name(){
        return "Change";
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
        clear();
    }

}

class exit extends Option{
    public String name(){return "Exit";}
    public void call(){tool.run = false;}
}