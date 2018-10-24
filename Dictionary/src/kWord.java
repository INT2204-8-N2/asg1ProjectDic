import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

public class kWord {
    private HashMap<String, String> data;
    private ArrayList<String> keys;
    private String path;
    private int wordsNum = 0;

    public void setWordsNum(int wordsNum) {
        this.wordsNum = wordsNum;
    }

    public void setPath(String name){
        path = name;
    }
    public int getWordsNum(){
        return wordsNum;
    }
    public ArrayList<String> getKeys() {
        return keys;
    }
    public HashMap<String, String> getData(){
        return data;
    }

    public void setData(HashMap<String, String> data) {
    }

    public kWord(){
        data = new HashMap<>();
        keys = new ArrayList<>();
    }
    //TODO: phuong thuc read data

    public void readData(){
        File file;
        FileReader fileReader;
        BufferedReader reader;

        try {
            file = new File(path);
            fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);
            String line, word, def;
            while ((line = reader.readLine()) != null){
                int index = line.indexOf("<html>");
                int index2 = line.indexOf("<ul>");
                if (index2 != -1 && index > index2){
                    index = index2;
                }

                if (index != -1){
                    word = line.substring(0, index);
                    word = word.trim();
                    keys.add(word);

                    def = line.substring(index);
                    data.put(word, def);
                    wordsNum++;
                }
            }
            reader.close();
            System.out.println(path + ": " + wordsNum +" words");
        }catch (FileNotFoundException e){
            e.printStackTrace();;
        }catch (IOException e){
            e.printStackTrace();
        }
        Collections.sort(keys);
    }
    public int BinarySearch(ArrayList<String> k, String w){
//        if (str.compareTo(arr.get(0)) <= 0) return 0;
//        int d = 0, c = arr.size();
//        while (d < c - 1){
//            int g = (d + c)/2;
//            if (str.compareTo(arr.get(g)) < 0){
//                c = g;
//            }else {
//                d = g;
//            }
//        }
//        return d;
        if (k.get(0).compareTo(w) >=0 ) return 0;
        int d = 0, c = k.size();
        while (d < c - 1){
            int g = (d + c)/2;
            if (k.get(g).compareTo(w) < 0) d = g; else c = g;
        }
        return c;

    }
    public void addWord(String word, String definition){
        word = word.toLowerCase();
        data.put(word, definition);
        int n = BinarySearch(keys, word);
        keys.add(n, word);
    }
    public void removeWord(String word){
        int n = keys.lastIndexOf(word);
        if (n >= 0 && n<=keys.size() - 1){
            keys.remove(n);
            data.remove(word);
        wordsNum--;
        }
    }
    public void modifiWord(String key, String newDef){
        newDef = newDef.toLowerCase();
        data.replace(key, newDef);
    }
}
