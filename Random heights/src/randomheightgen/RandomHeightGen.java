/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package randomheightgen;

import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 * @author Matt
 */
public class RandomHeightGen
{
    public static int randomInx()
    {
        double [] items = { 0.01779975,  0.14165316,  0.01029262,  0.168136, 0.03061161,  0.09046587, 0.19987289,  0.13398581,  0.03119906,  0.17598322};
        int idx = 0;
        for (double r = Math.random() * 1; idx < items.length - 1; ++idx) {
            r -= items[idx];
            if (r <= 0.0) break;
        }
        return idx;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException 
    {
        
        int [] nums = { 5, 7, 10, 14, 16, 2, 9, 12, 8, 3};
        
        Path path = Paths.get("C:\\Users\\matth\\OneDrive\\Desktop\\Project207\\Visualization\\src\\main\\resources\\com\\example\\visualization\\Tree_Inventory.csv");
        ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(path, StandardCharsets.UTF_8);
        int lineNumber = 1;
//        for(int i = 1; i < lines.size(); i++) {
//            String[] newLineCategories = lines.get(i).split(",");
//            if(newLineCategories.length == 46)
//            {
//                if(newLineCategories[34].equals(""))
//                {
//                    newLineCategories[34] = String.valueOf(nums[randomInx()]);
//                }
//                lines.set(lineNumber, Arrays.toString(newLineCategories).substring(2).replaceAll("\\s+",""));
//            }
//            lineNumber++;
//        }

        String[] newLineCategories = lines.get(45450).split(",");
        lines.set(45450, Arrays.toString(newLineCategories).substring(2).replaceAll("\\s+","").replaceAll("Whiteash", "WhiteAsh"));
        Files.write(path, lines, StandardCharsets.UTF_8);
    }
}
