import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class Main {

    public static String join(Collection a, String separator)
    {
        StringBuilder result = new StringBuilder();
        if (a != null && a.size() > 0)
        {
            Iterator strIterator = a.iterator();
            boolean first = true;
            while (strIterator.hasNext())
            {
                Object obj = strIterator.next();
                if (first)
                {
                    first = false;
                }
                else
                {
                    result.append(separator);
                }
                result.append(obj.toString());
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        Set<String> modsInStudy = new TreeSet<>();
        modsInStudy.add("XR");
        modsInStudy.add("CT");
        modsInStudy.add("RF");

        String modsInStudyString = join(modsInStudy, "\\");

        System.out.println(modsInStudyString);
    }
}
