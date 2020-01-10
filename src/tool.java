import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

public class tool {
    public static double[][] A;
    public static double[][] B;

    public static matrix current;
    public static List<matrix> matrices;
    /*Make Matrix Object so you can add unlimited matricies*/
    /*Add matrix storage + selector*/
    /*Get methods working*/
    /*Version Control stuff*/
    public static boolean run = true;

    public static void main(String [] args) throws IOException, InterruptedException {
        tool whe = new tool();
        whe.menu();
    }
    tool(){

        Scanner sc = new Scanner(System.in);

        System.out.println("[[2,3],[4,5]] = 2_3>4_5 | Underscore separates entries, > separates rows");

        System.out.print("Input A: ");
        String AInp = sc.nextLine();
        /*A = matrixParse(AInp);*/

        System.out.print("Input B: ");
        String BInp = sc.nextLine();
        /*B = matrixParse(BInp);*/

    }

    /*public static double[][] matrixParse(String feed){
        String[] rows = feed.split(">");
        int cols = 1 + (int) rows[0].codePoints().filter(ch -> ch == '_').count();

        double[][] mat = new double[rows.length][cols];

        for(int rowN = 0; rowN < rows.length; rowN++){
            int l = rows[rowN].split("_").length;

            double[] matRow = new double[l];

            for(int i = 0; i < l; i++){
                matRow[i] = Double.parseDouble(rows[rowN].split("_")[i]);
            }

            mat[rowN] = matRow;
        }

        return mat;
    }*/

    public void menu() throws IOException, InterruptedException {
        List<Option> options = new ArrayList<>(Arrays.asList(
                new matrixAdd(),
                new matrixSubtract(),
                new matrixScalar(),
                new matrixChange(),
                new view(),
                new exit()
        ));

        if(A.length == B[0].length || B.length == A[0].length){
            options.add(0, new matrixMultiply());
        }

        System.out.println("Options:");
        for (int i =0;i< options.size(); i++) {
            System.out.printf("(%d) %s %n", i + 1, options.get(i).name());
        }

        Scanner sc = new Scanner(System.in);

        options.get(sc.nextInt() - 1).call();

        if(run){
            menu();
        }
    }

}
