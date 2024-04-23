package library;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import storage.SortedLinkedList;


public class BookReader{

    private Scanner fileIn;
    private SortedLinkedList<Book> books;

    public BookReader(String filename){
        books = new SortedLinkedList<>();
        try
        {
            setFileIn(new Scanner(new File(filename)));
            readLines();
            fileIn.close();
        }
        catch (FileNotFoundException FNF)
        {
            System.err.println("Could not open the input file." + filename);
        }
    }

    public void setFileIn(Scanner fileIn){
        this.fileIn = fileIn;
    }

    public void readLines()
    {
        while (fileIn.hasNextLine())
        {
            String str = fileIn.nextLine();
            String[] split = str.split(",");

            String author = split[0].trim();
            String title = split[1].trim();
            int numPages = Integer.parseInt(split[2].trim());

            Book newBook = new Book(author, title, numPages);
            books.add(newBook);

        }
    }

    public SortedLinkedList<Book> getBooks(){
        return books;
    }

    public void printMoreThan300(){
                for(Book book: books){
                    if (book.getNumPages() > 300){
                        System.out.println(book.toString());
                    }
                }
    }

    public double averagePages(){
        int totalPages = 0;
        if(books.isEmpty()){
            return 0;
        }
        for (Book book : books){
            totalPages += book.getNumPages();
        }
        return (double) totalPages / books.getLength();
    }

    public void removeLessThan200(){
        
    }
}

