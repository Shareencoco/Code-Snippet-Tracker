
import java.util.*;

class CodeSnippet {
    int id;
    String title;
    String language;
    String code;

    public CodeSnippet(int id, String title, String language, String code) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.code = code;
    }

    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Language: " + language);
        System.out.println("Code:\n" + code);
        System.out.println("-------------------------");
    }
}

public class SnippetTracker {
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<CodeSnippet> snippets = new ArrayList<>();
    private static int currentId = 1;

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nCode Snippet Tracker");
            System.out.println("1. Add Snippet");
            System.out.println("2. View All Snippets");
            System.out.println("3. Search Snippet by Language");
            System.out.println("4. Delete Snippet by ID");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addSnippet();
                    break;
                case 2:
                    viewAllSnippets();
                    break;
                case 3:
                    searchByLanguage();
                    break;
                case 4:
                    deleteById();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addSnippet() {
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter language: ");
        String language = scanner.nextLine();
        System.out.print("Enter code snippet (end with ';' on a new line): ");
        StringBuilder codeBuilder = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).equals(";")) {
            codeBuilder.append(line).append("\n");
        }
        CodeSnippet snippet = new CodeSnippet(currentId++, title, language, codeBuilder.toString());
        snippets.add(snippet);
        System.out.println("Snippet added!");
    }

    private static void viewAllSnippets() {
        if (snippets.isEmpty()) {
            System.out.println("No snippets found.");
        } else {
            for (CodeSnippet snippet : snippets) {
                snippet.display();
            }
        }
    }

    private static void searchByLanguage() {
        System.out.print("Enter language to search: ");
        String lang = scanner.nextLine();
        boolean found = false;
        for (CodeSnippet snippet : snippets) {
            if (snippet.language.equalsIgnoreCase(lang)) {
                snippet.display();
                found = true;
            }
        }
        if (!found) System.out.println("No snippets found for " + lang);
    }

    private static void deleteById() {
        System.out.print("Enter ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Iterator<CodeSnippet> iterator = snippets.iterator();
        while (iterator.hasNext()) {
            CodeSnippet snippet = iterator.next();
            if (snippet.id == id) {
                iterator.remove();
                System.out.println("Snippet deleted.");
                return;
            }
        }
        System.out.println("Snippet with ID " + id + " not found.");
    }
}
