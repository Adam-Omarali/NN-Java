import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Data {
    public static ArrayList<Point> trainingData = new ArrayList<Point>();
    public Data() throws Exception{
        Scanner sc = new Scanner(new File("src/train.txt"));  
        while (sc.hasNextLine()){
            String[] line = sc.nextLine().split(",");
            double x = Double.parseDouble(line[0]);
            double y = Double.parseDouble(line[1]);
            int label = Integer.parseInt(line[2]);
            Point point = new Point(x, y, label);
            trainingData.add(point);
        }
    }
}
