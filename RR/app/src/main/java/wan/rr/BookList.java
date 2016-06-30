package wan.rr;

import java.util.LinkedList;
import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class BookList
{
    class LoaderHandler extends DefaultHandler
    {
        LinkedList<String> books;
        boolean bname = false;
        
        public LoaderHandler()
        {
            books = new LinkedList<String>();
        }

        public void startElement(String uri, String localName,String qName, Attributes attributes) throws SAXException
        {
            //System.out.println("Start Element :" + qName);

            if (qName.equals("book")) 
            {
                bname = true;
            }
        }

        public void endElement(String uri, String localName, String qName) throws SAXException 
        {
            //System.out.println("End Element :" + qName);
        }

        public void characters(char ch[], int start, int length) throws SAXException 
        {
            if (bname)
            {
                books.add(new String(ch, start, length));
                bname = false;
            }
        }
        
        public LinkedList<String> getBookNames()
        {
            return books;
        }
    }
    
    LinkedList<Book> booklist;
    
    public BookList()
    {
        booklist = new LinkedList<Book>();
    }
    
    // load the booklist.xml and the book described in it.
    // if booklist.xml not exist, load all books under 'books' dir 
    // and then generate the booklist.xml file.
    public void load()
    {
        File file = new File("./booklist.xml");
        if (file.exists())
        {
            try
            {
                System.out.println("booklist exists");
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser saxParser = factory.newSAXParser();
                
                LoaderHandler handler = new LoaderHandler();
                saxParser.parse("./booklist.xml", handler);
                
                LinkedList<String> books = handler.getBookNames();
                for (int i = 0; i < books.size(); i++)
                {
                    String filepath = "./books/" + books.get(i) + ".xml";
                    File bookfile = new File(filepath);
                    if (bookfile.exists())
                    {
                        Book book = new Book();
                        book.load(filepath);
                        booklist.add(book);
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
            System.out.println("booklist no exists");
    }
      
    // return the count of books
    public int count()
    {
        return booklist.size();
    }
    
    public Book getBook(int idx)
    {
        return booklist.get(idx);
    }
}