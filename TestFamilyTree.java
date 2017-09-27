package odev;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by eray on 4/13/17.
 */
public class TestFamilyTree {

    public static void main(String[] args) {
        FamilyTree<String> family = null;

        String readFilename = "src/odev/family.txt";

        BufferedReader br = null;

        String line = "";

        try {
            br = new BufferedReader(new FileReader(readFilename));

            line = br.readLine();

            family = new FamilyTree<>(line);

            while ((line = br.readLine()) != null) {

                String[] sArray = line.split(" *(,|=>| ) *");
                if(sArray.length == 3)
                {
                    family.add(sArray[0], sArray[1], sArray[2]);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        family.levelOrderToString();
    }
}
