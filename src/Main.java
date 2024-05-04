import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Token> tokens = new ArrayList<>();

        tokens.add(new Token(TipoToken.PALAVRA_RESERVADA, "int")); 
        tokens.add(new Token(TipoToken.IDENTIFICADOR, "x")); 
        tokens.add(new Token(TipoToken.PONTO_VIRGULA, ";")); 

        AnalisadorSintatico analisador = new AnalisadorSintatico(tokens);

        analisador.analisar();
    }
}