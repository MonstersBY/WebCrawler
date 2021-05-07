import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.*;

public class WebCrawler {
    private static final int MAX_DEPTH=8;
    private static final int MAX_PAGE=1000;
    private final HashSet<String> links;

    public WebCrawler() {
        links = new HashSet<String>();
    }

    public void getPageLinks(String URL, int depth){

        if ((!links.contains(URL) && (depth <= MAX_DEPTH)) && (links.size()<MAX_PAGE)){
            try{

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            System.out.println(" >> Depth: " + depth + " [" + URL + "]");
            try {
                links.add(URL);
                Document document = Jsoup.connect(URL).get();
                Elements linksOnPage =document.select("a[href]");
                depth++;
                for (Element page: linksOnPage) {
                    getPageLinks(page.attr("abs:href"), depth);
                }

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args){
        new WebCrawler().getPageLinks("https://en.wikipedia.org/wiki/Elon_Musk", 0);

    }
}
