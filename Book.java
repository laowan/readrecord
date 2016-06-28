import java.util.LinkedList;
import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Book
{
    class BookData
    {
        class ChapterData
        {
            public String index;
            public String text;
            public String startpage;
            public String state;
        }
        
        public String name;
        public String pagenum;
        public LinkedList<ChapterData> catalog;
        
        public BookData()
        {
            catalog = new LinkedList<ChapterData>();
        }
        
        public void addChapterData(String idx, String txt, String start, String stat)
        {
            ChapterData data = new ChapterData();
            data.index = idx;
            data.text = txt;
            data.startpage = start;
            data.state = stat;
            catalog.add(data);
        }
    }
    
    class LoaderHandler extends DefaultHandler
    {
        BookData bookdata;
        boolean bname = false;
        boolean bpagenum = false;
        boolean bindex = false;
        boolean btext = false;
        boolean bstartpage = false;
        boolean bstate = false;
        String index, text, startpage, state;
        
        public LoaderHandler()
        {
            bookdata = new BookData();
        }

        public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException
        {
            //System.out.println("Start Element :" + qName);

            if (qName.equals("name")) bname = true;
            if (qName.equals("pagenum")) bpagenum = true;
            if (qName.equals("index")) bindex = true;
            if (qName.equals("text")) btext = true;
            if (qName.equals("startpage")) bstartpage = true;
            if (qName.equals("state")) bstate = true;
        }

        public void endElement(String uri, String localName, String qName) throws SAXException 
        {
            //System.out.println("End Element :" + qName);
            if (qName.equals("chapter"))
            {
                bookdata.addChapterData(index, text, startpage, state);
            }
        }

        public void characters(char ch[], int start, int length) throws SAXException 
        {
            if (bname)
            {
                bookdata.name = new String(ch, start, length);
                bname = false;
            }
            if (bpagenum)
            {
                bookdata.pagenum = new String(ch, start, length);
                bpagenum = false;
            }
            if (bindex)
            {
                index = new String(ch, start, length);
                bindex = false;
            }
            if (btext)
            {
                text = new String(ch, start, length);
                btext = false;
            }
            if (bstartpage)
            {
                startpage = new String(ch, start, length);
                bstartpage = false;
            }
            if (bstate)
            {
                state = new String(ch, start, length);
                bstate = false;
            }
        }
        
        public BookData getBookData()
        {
            return bookdata;
        }
    }
    
    String name;
    int percent;

    public void load(String filepath)
    {
        File file = new File("./booklist.xml");
        if (file.exists())
        {
            try
            {
                System.out.println("book exists");
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser saxParser = factory.newSAXParser();
                
                LoaderHandler handler = new LoaderHandler();
                saxParser.parse(filepath, handler);
                
                BookData data = handler.getBookData();
                System.out.println(data.name);
                System.out.println(data.pagenum);
                for (int i = 0; i < data.catalog.size(); i++)
                {
                    System.out.println(data.catalog.get(i).index);
                    System.out.println(data.catalog.get(i).text);
                    System.out.println(data.catalog.get(i).startpage);
                    System.out.println(data.catalog.get(i).state);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            System.out.println("book no exists");
        }
    }
}