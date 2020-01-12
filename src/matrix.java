import java.util.InputMismatchException;
import java.util.Scanner;

public class matrix {
    public double[][] values;
    public String label;
    public int rowNum;
    public int colNum;
    matrix(){
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.print("Input matrix label: ");
            label = sc.nextLine();
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
            while(true){
                if(r == 0) System.out.print("Input row 1, separated by spaces: ");
                else System.out.printf("Input row %d: ", r + 1);
                inputs = new Scanner(System.in).nextLine().split(" ");
                try {
                    for(int ite = 0; ite < colNum; ite++){
                        outs[ite] = Double.parseDouble(inputs[ite]);
                    }
                }catch (NumberFormatException e){
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

    matrix(double[][] values){
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
        System.out.print("Save? (Y/N) ");
        String choice = new Scanner(System.in).nextLine();
        if(choice.equals("Y") || choice.equals("y")){
            System.out.print("Input matrix label: ");
            this.label = new Scanner(System.in).nextLine();
            tool.matrices.add(this);
            System.out.printf("    Matrix %s Saved!%n", this.label);
        }
    }
}
