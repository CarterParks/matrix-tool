import java.util.Scanner;

public class matrix {
    private String AInp;
    public double[][] matrix;
    matrix(String name){
        System.out.println("[[2,3],[4,5]] = 2_3>4_5 | Underscore separates entries, > separates rows");// Replace with 'help'
        System.out.print("Input matrix: ");
        Scanner sc = new Scanner(System.in);
        AInp = sc.nextLine();
        matrixParse();
    }
    private void matrixParse(){
        String[] rows = this.AInp.split(">");
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

        this.matrix = mat;
    }
}
