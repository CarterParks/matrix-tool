import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

public class tool {
    public static matrix current;
    public static ArrayList<matrix> matrices;
    /*Make Matrix Object so you can add unlimited matricies*/
    /*Add matrix storage + selector*/
    /*Get methods working*/
    public static boolean run = true;

    public static void main(String [] args) throws IOException, InterruptedException {
        new tool();
        menu();
    }

    tool(){
        matrices = new ArrayList<>();
        matrices.add(new matrix());
        current = matrices.get(0);
    }

    static void menu() throws IOException, InterruptedException {
        List<Option> options = new ArrayList<>(Arrays.asList(
                new matrixNew(),
                new matrixScalar(),
                new view(),
                new exit()
        ));
        if(matrices.size()>1){
            options.add(0, new matrixChoose());
            options.add(1, new matrixAdd());
            options.add(2, new matrixSubtract());
        }

        System.out.println("Options:");
        for (int i =0;i< options.size(); i++) {
            System.out.printf("    (%d) %s %n", i + 1, options.get(i).name());
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Selection: ");
        options.get(sc.nextInt() - 1).call();

        if(run){
            menu();
        }
    }

    public static matrix chooser(){
        Scanner sc = new Scanner(System.in);

        System.out.println("    Matricies:");
        for(int i = 0; i < matrices.size(); i++) System.out.printf("        (%d) %s%n",i + 1, matrices.get(i).name);

        System.out.print("    Select a matrix: ");
        return matrices.get(sc.nextInt());
    }
}
