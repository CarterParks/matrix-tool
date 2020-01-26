import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.ToLongBiFunction;

public class Matrix {
    public double[][] values;
    public String label;
    public int rowNum;
    public int colNum;
    Matrix(){
        Scanner sc = new Scanner(System.in);

        handle:
        while(true){
            System.out.print("Input matrix label: ");
            label = sc.nextLine();

            for(Matrix M : Tool.matrices){
                if(label.equals(M.label)){
                    System.out.printf("Do you want to overwrite Matrix %s? (Y|N) ", M.label);
                    String opt = sc.nextLine();
                    if (opt.equals("y") || opt.equals("Y")){
                        Tool.matrices.remove(M);
                        Tool.current = this;
                        break;
                    }else continue handle;
                }
            }
            if(label.length() > 0) break;

            System.out.println("Please input a label.");
        }

        while(true) {
            System.out.print("Number of rows: ");
            try {
                rowNum = new Scanner(System.in).nextInt();
                break;
            }catch (InputMismatchException e){
                System.out.println("Please enter an integer.");
            }
        }

        while(true) {
            System.out.print("Number of columns: ");
            try {
                colNum = new Scanner(System.in).nextInt();
                break;
            }catch (InputMismatchException e){
                System.out.println("Please enter an integer.");
            }
        }

        values = new double[rowNum][colNum];

        for(int r = 0; r < rowNum; r++){
            String[] inputs;
            double[] outs = new double[colNum];
			System.out.println("Input Matrix: ");	
            while(true){
                inputs = new Scanner(System.in).nextLine().split(" ");
                try {
                    for(int i = 0; i < colNum; i++){
                        outs[i] = Double.parseDouble(inputs[i]);
                    }
                }catch (NumberFormatException | IndexOutOfBoundsException e){
                    System.out.printf("Please enter %d numbers separated by spaces.%n", colNum);
                    continue;
                }
                if(inputs.length == colNum) break;
                System.out.printf("Please enter %d numbers separated by spaces.%n", colNum);
            }

            values[r] = outs;
        }

        System.out.printf("   Matrix %s created!%n", this.label);
    }

    Matrix(double[][] values){
        this.values = values;
        rowNum = values.length;
        colNum = values[0].length;
    }

    public void view(){ /*TODO: Even out the issue with different length strings*/
        for (double[] row : values) {
            System.out.print("|");
            for (double ent: row) {
                System.out.printf(" %.2f", ent);
            }
            System.out.println(" |");
        }
    }

    public void save(){
        System.out.print("Save new Matrix? (Y|N) ");
        String choice = new Scanner(System.in).nextLine();
        if(choice.equals("Y") || choice.equals("y")){
            System.out.print("Input matrix label: ");
            this.label = new Scanner(System.in).nextLine();
            Tool.matrices.add(this);
            System.out.printf("    Matrix %s Saved!%n", this.label);
        }
    }

//    public double[] rowGrab(int idx){
//        return values[idx];
//    }
//
//    public double[] columnGrab(int idx){
//        double[] col = new double[values.length];
//        for (int i = 0; i < values.length; i++) {
//            col[i] = values[i][idx];
//        }
//        return col;
//    }


    public static Matrix identity(int s){
        double[][] vals = new double[s][s];
        for (double[] row: vals) Arrays.fill(row, 0 );
        for (int i = 0; i < s; i++) vals[i][i] = 1;
        return new Matrix(vals);
    }

    public Matrix copy(){
        return new Matrix(this.values);
    }
}
