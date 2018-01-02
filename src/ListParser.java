import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ListParser {

    Pattern p = Pattern.compile("^'(.*)':\\p{Blank}'(.*)',$");
    Matcher m;

    public List<String> readFileInList(String fileName)
    {

        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (NoSuchFileException nsfe){
            System.out.print("No such file in directory:" + fileName);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return lines;
    }

    public List<User> clear(List<String> lines){
        List<User> list = new ArrayList<>();
        Iterator<String> itr = lines.iterator();
        while (itr.hasNext()){
            String line = itr.next();

            if(check(line))
                list.add(getAsClass(m));
        }

        return list;
    }

    private boolean check(String userNameString){
        m = p.matcher(userNameString);
        return m.matches();
    }
    private User getAsClass(Matcher m){
        return new User(m.group(1), m.group(2));
    }

}