import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;

public class CodeGenerator {
    private String generatedCode;

    // Zbiory znaków do generowania tokenów
    private static final String DIGITS = "0123456789";
    private static final String ALPHANUMERIC = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Konstruktor
    public CodeGenerator(int length) {
        if (length == 4 || length == 6) {
            generatedCode = generateCode(length, DIGITS); // Generuj PIN
        } else if (length == 12 || 16 == length) {
            generatedCode = generateCode(length, ALPHANUMERIC); // Generuj token
        } else {
            throw new IllegalArgumentException("Nieprawidłowa długość kodu. Wartość powinna być 4, 6, 12 lub 16.");
        }
    }

    // Metoda generująca kod
    private String generateCode(int length, String charSet) {
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(charSet.length());
            code.append(charSet.charAt(index));
        }

        return code.toString();
    }

    // Metoda do wyświetlenia kodu
    public void displayCode() {
        System.out.println("Wygenerowany kod: " + generatedCode);
    }

    // Metoda zapisująca kod do pliku
    public void saveCodeToFile(String fileName) throws IOException {
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(generatedCode);
            System.out.println("Kod zapisany do pliku: " + fileName);
        }
    }

    // Main - przykładowe użycie
    public static void main(String[] args) {
        try {
            // Generowanie PIN-u 4-cyfrowego
            CodeGenerator pinGenerator = new CodeGenerator(4);
            pinGenerator.displayCode();
            pinGenerator.saveCodeToFile("pin.txt");

            // Generowanie tokenu 16-znakowego
            CodeGenerator tokenGenerator = new CodeGenerator(16);
            tokenGenerator.displayCode();
            tokenGenerator.saveCodeToFile("token.txt");
        } catch (IOException e) {
            System.err.println("Wystąpił błąd przy zapisywaniu do pliku: " + e.getMessage());
        }
    }
}
