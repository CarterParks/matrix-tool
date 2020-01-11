import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;
/* TODO: Get matrix multiplication working
*  TODO: Setup bad input handler*/
public class tool {
    public static matrix current;
    public static ArrayList<matrix> matrices = new ArrayList<>();
    public static boolean run = true;

    public static void main(String [] args){
        new tool();
    }

    tool(){
        matrices.add(new matrix());
        current = matrices.get(0);
        System.out.println();
        menu();
    }

    static void menu(){
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

        System.out.printf("Options (Matrix %s):%n", current.label);
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

        System.out.printf("%nMatricies:%n");
        for(int i = 0; i < matrices.size(); i++) System.out.printf("    (%d) %s%n",i + 1, matrices.get(i).label);

        System.out.print("Select a matrix: ");
        matrix choice = matrices.get(sc.nextInt() - 1);
        System.out.printf("Matrix %s Selected!%n", choice.label);
        return choice;
    }
}
