import java.util.*;

public class Tool {
    public static Matrix current;
    public static ArrayList<Matrix> matrices = new ArrayList<>();
    public static boolean run = true;

    public static void main(String [] args){
        new Tool();
    }

    Tool(){
        System.out.printf("=====[ matrix-tool v1.2 ]=====%n%n");
        matrices.add(new Matrix());
        System.out.println();
        current = matrices.get(0);
        menu();
    }

    static void menu(){
        // Default Options
        ArrayList<Option> options = new ArrayList<>(Arrays.asList(
                new MatrixNew(),
                new View(),
                new MatrixScalar(),
                new Transpose(),
                new Exit()
        ));

        // Multiplying Matrices
        boolean mul = false;
        for (Matrix m : matrices) {
            if (current.colNum == m.rowNum) {
                mul = true;
                break;
            }
        }
        if(mul) options.add(3, new MatrixMultiply());

        // Vector Cross Product
        boolean _3x1 = current.rowNum == 3 && current.colNum == 1;
        boolean _1x3 = current.rowNum == 1 && current.colNum == 3;
        if(_3x1 || _1x3){
            options.add(3, new CrossProduct());
        }

        //Square Matrix
        if(current.rowNum == current.colNum){
            options.add(4, new Determinant());
            options.add(4, new UpperTri());
        }

        // Multiple Matrices
        if(matrices.size()>1){
            options.add(2, new MatrixSubtract());
            options.add(2, new MatrixAdd());
            options.add(2, new MatrixChoose());
        }

        System.out.printf("Options (Matrix %s):%n", current.label);
        for (int i = 0;i< options.size(); i++) {
            System.out.printf("    (%d) %s %n", i + 1, options.get(i).name());
        }

        options.get(choice(options, "Select Option: ")).call();

        if(run){
            menu();
        }
    }

    public static Matrix chooser() {
        System.out.printf("%nMatricies:%n");
        for (int i = 0; i < matrices.size(); i++) System.out.printf("    (%d) %s%n", i + 1, matrices.get(i).label);

        Matrix matCho = matrices.get(choice(matrices, "Select Matrix: "));
        System.out.printf("Matrix %s Selected!%n", matCho.label);
        return matCho;
    }

    public static <E> int choice(ArrayList<E> ls, String prompt){
        int choice;
        while(true){
            System.out.print(prompt);
            try {
                choice = new Scanner(System.in).nextInt() - 1;
            }catch (InputMismatchException e){
                System.out.println("Please enter a list option.");
                continue;
            }
            if(0 <= choice & choice < ls.size()){
                break;
            }
            System.out.println("Please enter a list option.");
        }
        return choice;
    }
}
