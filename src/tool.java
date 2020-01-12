import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;
import java.util.Arrays;

public class tool {
    public static matrix current;
    public static ArrayList<matrix> matrices = new ArrayList<>();
    public static boolean run = true;

    public static void main(String [] args){
        new tool();
    }

    tool(){
        System.out.println("=====[ matrix-tool v1 ]=====");
        matrices.add(new matrix());
        System.out.println();
        current = matrices.get(0);
        menu();
    }

    static void menu(){
        List<Option> options = new ArrayList<>(Arrays.asList(
                new matrixNew(),
                new view(),
                new matrixScalar(),
                new exit()
        ));
        boolean mul = false;
        for (matrix m : matrices) {
            if (current.colNum == m.rowNum) {
                mul = true;
                break;
            }
        }
        if(mul) options.add(3, new matrixMultiply());
        if(matrices.size()>1){
            options.add(2, new matrixChoose());
            options.add(3, new matrixAdd());
            options.add(4, new matrixSubtract());
        }

        System.out.printf("Options (Matrix %s):%n", current.label);
        for (int i = 0;i< options.size(); i++) {
            System.out.printf("    (%d) %s %n", i + 1, options.get(i).name());
        }

        Scanner sc = new Scanner(System.in);

        int choice;
        while (true) {
            System.out.print("Selection: ");
            choice = sc.nextInt();
            if(1 < choice & choice - 1 < options.size()){
                break;
            }
            System.out.println("Please enter a list option.");
        }

        options.get(choice - 1).call();

        if(run){
            menu();
        }
    }

    public static matrix chooser() {
        Scanner sc = new Scanner(System.in);

        System.out.printf("%nMatricies:%n");
        for (int i = 0; i < matrices.size(); i++) System.out.printf("    (%d) %s%n", i + 1, matrices.get(i).label);

        int choice;
        while(true){
            System.out.print("Select a matrix: ");
            choice = sc.nextInt() - 1;
            if(1 < choice & choice - 1 < matrices.size()){
                break;
            }
            System.out.println("Please enter a list option.");
        }

        matrix matCho = matrices.get(choice);
        System.out.printf("Matrix %s Selected!%n", matCho.label);
        return matCho;
    }
}
