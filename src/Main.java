import java.io.File;
import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {

    final static String FILE_NAME = "accounts.js";
    final static String FILE_CONFIG_NAME = "config.json";

    public static void main(String[] args) {
        String path = null;
        File currentDirectory = new File(new File("").getAbsolutePath());
        try {
            path = currentDirectory.getCanonicalPath() + "\\";

        }catch (IOException ioe){
            ioe.getStackTrace();
        }


        ListParser listParser =  new ListParser();
        List l = listParser.readFileInList(path + FILE_NAME);
        l = listParser.clear(l);

        /*
        Iterator<User> itr = l.iterator();
        while (itr.hasNext()){
            User user = itr.next();
            System.out.println("\n" + "user:" + user.user + " password:" + user.password + "\n");
        }*/

        if(!l.isEmpty()){
            ObjectMapper mapper = new ObjectMapper();
            try {
                mapper.writeValue(new File(path + FILE_CONFIG_NAME), l);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
