import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Token> tokens = new ArrayList<>();

        tokens.add(new Token(TipoToken.PALAVRA_RESERVADA, "int")); 
        tokens.add(new Token(TipoToken.IDENTIFICADOR, "x")); 
        tokens.add(new Token(TipoToken.PONTO_VIRGULA, ";")); 

        AnalisadorSintatico analisador = new AnalisadorSintatico(tokens);

        // Adicionei um try-catch para capturar exceções durante a análise
        try {
            analisador.analisar();
            System.out.println("Análise sintática concluída sem erros.");
        } catch (RuntimeException e) {
            System.out.println("Erro durante a análise sintática: " + e.getMessage());
        }
    }
}
