import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * main method for testing purposes
 * @author Seckin Savasci - 2008400078
 * @author Bogazici University CmpE - Cmpe250
 */
public class Main {
	static Random rand = new Random();
	static int min = 1;
	static int max = 234567788;
	static double loadFactor = 0.95;
	static int n = 10007;
	static int m = (int) (loadFactor*n);
	static ArrayList<Integer> inserted = new ArrayList<>();
  
    public static void main(String[] args)
    {
        HashTable hashTable = new HashTable(n);
        for(int i = 0; i < m; i++){
        	int value = rand.nextInt((max - min) + 1) + min;
        	if(inserted.contains(value)){
        		i--;
        		continue;
        	}
        	hashTable.insert(value);
        	inserted.add(value);
        } 
		hashTable.deleteAll();
		inserted = new ArrayList<>();
		for(int i = 0; i < m; i++){
        	int value = rand.nextInt((max - min) + 1) + min;
        	if(inserted.contains(value)){
        		i--;
        		continue;
        	}
        	hashTable.insert(value);
        	inserted.add(value);
        }
        try {
			File file = new File("C:\\Users\\Manuel\\Desktop\\95-10k.txt");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			Node[] table = hashTable.getTable();
			for(Node n : table){
				if(n == null) continue;
				bw.write(""+n.getProbeCount());
				bw.newLine();
			}
			bw.close();
			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
