package models;
import lombok.Data;
import java.util.ArrayList;

@Data
public class AddBookRequestBodyModel {

    public String userId;
    private ArrayList<IsbnValue> collectionOfIsbns;

    public void setIsbn(String value) {
        IsbnValue isbn = new IsbnValue();
        isbn.setIsbn(value);
        ArrayList<IsbnValue> isbnData = new ArrayList<>();
        isbnData.add(isbn);
        this.collectionOfIsbns = isbnData;
    }
}