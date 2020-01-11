import java.util.Scanner;

public class matrix {
    private String matInp;
    public double[][] values;
    public String name;
    public int rowNum;
    public int colNum;
    matrix(){
        Scanner sc = new Scanner(System.in);

        System.out.print("Input matrix label: ");
        name = sc.nextLine();

        while(true) {
            System.out.print("Input matrix: ");
            matInp = sc.nextLine();
            if (matInp.equals("help")) {
                System.out.println("[[2,3],[4,5]] = 2_3>4_5 | Underscore separates entries, > separates rows");
            } else {
                matrixParse();
                break;
            }
        }
        rowNum = values.length;
        colNum = values[0].length;
    }

    matrix(String matInp){
        Scanner sc = new Scanner(System.in);
        System.out.print("Input matrix label: ");
        name = sc.nextLine();

        this.matInp = matInp;
        matrixParse();
        rowNum = values.length;
        colNum = values[0].length;
    }

    matrix(double[][] values){
        /*Scanner sc = new Scanner(System.in);
        System.out.print("Input matrix label: ");
        name = sc.nextLine();*/

        this.values = values;
        rowNum = values.length;
        colNum = values[0].length;
    }

    private void matrixParse(){
        String[] rows = this.matInp.split(">");
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

        this.values = mat.clone();
    }

    public void view(){ /*TODO: Even out the issue with different length strings*/
        for (double[] row : values) {
            System.out.print("|");
            for (double ent: row) {
                System.out.printf(" %f", ent);
            }
            System.out.println(" |");
        }
    }
}
