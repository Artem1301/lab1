import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShopBook {
    private List<String[]> data;

    public ShopBook() {
        data = new ArrayList<>();
    }

    public void addData(String[] entry) {
        data.add(entry);
    }

    public List<String[]> getData() {
        return data;
    }

    public void removeRow(int rowIndex) {
        if (rowIndex >= 0 && rowIndex < data.size()) {
            data.remove(rowIndex);
            System.out.println("Row at index " + rowIndex + " removed from the database.");
        } else {
            System.out.println("Invalid row index.");
        }
    }

    public void addRow(String[] newRow) {
        data.add(newRow);
        System.out.println("New row added to the database.");
    }

    public void editRow(int rowIndex, String[] newData) {
        if (rowIndex >= 0 && rowIndex < data.size()) {
            data.set(rowIndex, newData);
            System.out.println("Row at index " + rowIndex + " edited.");
        } else {
            System.out.println("Invalid row index.");
        }
    }

    public List<String[]> searchByTitle(String title) {
        List<String[]> result = new ArrayList<>();
        for (String[] row : data) {
            if (row[1].equalsIgnoreCase(title)) {
                result.add(row);
            }
        }
        return result;
    }

    public List<String[]> searchByAuthor(String author) {
        List<String[]> result = new ArrayList<>();
        for (String[] row : data) {
            if (row[0].equalsIgnoreCase(author)) {
                result.add(row);
            }
        }
        return result;
    }
}


class Menu extends ShopBook{
    public static void main(String[] args) {

        ShopBook database = new ShopBook();
        try (BufferedReader reader = new BufferedReader(new FileReader("books.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(",");
                database.addData(columns);
            }
            System.out.println("Data added to the database.");
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        System.out.printf("Commands for DataBase:\n" +
                "Edit - Remove the row\n" +
                "D - Delete the row\n" +
                "SA - Search by an Author\n" +
                "ST - Search by a Title\n" +
                "SH - Show the DataBase\n" +
                "A - Add the row\n");


        Scanner scanner = new Scanner(System.in);
        String choose = scanner.nextLine();
        switch (choose) {
            case "Edit":
                int rowIndexToEdit = 0;
                String[] newData = {"Edited Author", "Edited Title", "2022", "200", "$15.00"};
                database.editRow(rowIndexToEdit, newData);
                break;
            case "D":
                int rowIndex = 0;
                database.removeRow(rowIndex);
                break;
            case "SA":
                // Search books by author
                String searchAuthor = "Petro Franko";
                System.out.println("Books by author '" + searchAuthor.trim() + "':");
                List<String[]> booksByAuthor = database.searchByAuthor(searchAuthor.trim());
                for (String[] columns : booksByAuthor) {
                    for (String column : columns) {
                        System.out.print(String.format("%-16s", column));  
                        System.out.print("|"); 
                    }
                    System.out.println();  
                }
                break;
            case "ST":
                // Search books by title
                String searchTitle = "Good boy";
                System.out.println("Books with title '" + searchTitle.trim() + "':");
                List<String[]> booksByTitle = database.searchByTitle(searchTitle.trim());
                for (String[] columns : booksByTitle) {
                    for (String column : columns) {
                        System.out.print(String.format("%-16s", column));  // Форматирование с отступами
                        System.out.print("|");  // Разделитель между столбцами
                    }
                    System.out.println();  // Новая строка для следующей записи
                }
                break;
            case "SH":
                // Display the data in the database
                List<String[]> retrievedData = database.getData();
                System.out.println("_____________________________________________________________________________________");
                for (String[] columns : retrievedData) {
                    for (String column : columns) {
                        System.out.print(String.format("%-16s", column));  // Форматирование с отступами
                        System.out.print("|");  // Разделитель между столбцами
                    }
                    System.out.println();  // Новая строка для следующей записи
                }
                System.out.println("_____________________________________________________________________________________");
                break;
            case "A":
                // Add a new row
                String[] newRow = {"New Author", "New Title", "2023", "300", "$20.00"};
                database.addRow(newRow);
                break;
            default:
                System.out.println("Choose a correct option!!!");
                break;
        }
    }
}
