package columns;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class ColumnsTitlesFileProcessor {

    public List<String> importTitles(){
        List<String> colTitle = new LinkedList<String>();
        try {
            FileInputStream fis = new FileInputStream(getClass().getClassLoader().getResource("colTitles.con").getFile());
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String buf;
            while ((buf = br.readLine()) != null){
                colTitle.add(buf);
            }
            br.close();
            isr.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return colTitle;
    }

    public void exportTitles(List<String> list){
        try(OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(getClass().getClassLoader().getResource("colTitles.con").getFile()), "UTF-8")) {
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                str.append(list.get(i)).append("\n");
            }
            output.write(String.valueOf(str));
            output.flush();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
