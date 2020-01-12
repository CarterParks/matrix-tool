import java.util.*;

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
        ArrayList<Option> options = new ArrayList<>(Arrays.asList(
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

        options.get(choice(options, "Select Option: ")).call();

        if(run){
            menu();
        }
    }

    public static matrix chooser() {
        System.out.printf("%nMatricies:%n");
        for (int i = 0; i < matrices.size(); i++) System.out.printf("    (%d) %s%n", i + 1, matrices.get(i).label);

        matrix matCho = matrices.get(choice(matrices, "Select Matrix: "));
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
                System.out.print("Please enter a list option.");
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
