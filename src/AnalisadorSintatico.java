import java.util.List;

public class AnalisadorSintatico {
    private List<Token> tokens;
    private int posicaoAtual;

    public AnalisadorSintatico(List<Token> tokens) {
        this.tokens = tokens;
        this.posicaoAtual = 0;
    }

    private void tipo() {
        if (verificarTipo(TipoToken.PALAVRA_RESERVADA) && (tokenAtual().getValor().equals("int") ||
                tokenAtual().getValor().equals("float") ||
                tokenAtual().getValor().equals("double") ||
                tokenAtual().getValor().equals("char") ||
                tokenAtual().getValor().equals("boolean"))) {
            proximoToken();
        } else {
            erroSintatico("Tipo inválido ou não reconhecido.");
        }
    }

    private void proximoToken() {
        posicaoAtual++;
    }

    private boolean temMaisTokens() {
        return posicaoAtual < tokens.size();
    }

    private Token tokenAtual() {
        if (temMaisTokens()) {
            return tokens.get(posicaoAtual);
        } else {
            throw new RuntimeException("Erro: Nenhum token disponível.");
        }
    }

    private boolean verificarTipo(TipoToken tipo) {
        return tokenAtual().getTipo() == tipo;
    }

    private void expressao() {
        // Implemente a análise de expressões aqui
    }

    private void erroSintatico(String mensagem) {
        throw new RuntimeException("Erro sintático: " + mensagem);
    }

    public void analisar() {
        programa();
    }

    private void programa() {
        while (temMaisTokens()) {
            declaracao();
        }
    }

    private void declaracao() {
        if (verificarTipo(TipoToken.PALAVRA_RESERVADA)) {
            String palavraReservada = tokenAtual().getValor();
            if (palavraReservada.equals("int") || palavraReservada.equals("float") || palavraReservada.equals("double")
                    || palavraReservada.equals("char") || palavraReservada.equals("boolean")) {
                declaracaoVariavel();
            } else if (palavraReservada.equals("struct")) {
                declaracaoEstrutura();
            } else {
                erroSintatico("Declaração não reconhecida.");
            }
        } else if (verificarTipo(TipoToken.COMENTARIO)) {
            proximoToken();
        } else {
            erroSintatico("Declaração esperada.");
        }
    }

    private void declaracaoVariavel() {
        tipo();
        if (verificarTipo(TipoToken.IDENTIFICADOR)) {
            proximoToken();
            if (verificarTipo(TipoToken.PONTO_VIRGULA)) {
                proximoToken();
            } else if (verificarTipo(TipoToken.OPERADOR) && tokenAtual().getValor().equals("=")) {
                proximoToken();
                expressao();
                if (verificarTipo(TipoToken.PONTO_VIRGULA)) {
                    proximoToken();
                } else {
                    erroSintatico("';' esperado após a expressão.");
                }
            } else {
                erroSintatico("';' ou '=' esperado após o identificador.");
            }
        } else {
            erroSintatico("Identificador esperado após o tipo.");
        }
    }

    private void declaracaoEstrutura() {
        if (verificarTipo(TipoToken.PALAVRA_RESERVADA) && tokenAtual().getValor().equals("struct")) {
            proximoToken();
            if (verificarTipo(TipoToken.IDENTIFICADOR)) {
                proximoToken();
                if (verificarTipo(TipoToken.SIMBOLO_ESPECIAL) && tokenAtual().getValor().equals("{")) {
                    proximoToken();
                    while (!verificarTipo(TipoToken.SIMBOLO_ESPECIAL) || !tokenAtual().getValor().equals("}")) {
                        // Implemente o processamento das declarações dentro da estrutura aqui
                    }
                    if (verificarTipo(TipoToken.SIMBOLO_ESPECIAL) && tokenAtual().getValor().equals("}")) {
                        proximoToken();
                    } else {
                        erroSintatico("'}' esperado para fechar a declaração de estrutura.");
                    }
                } else {
                    erroSintatico("'{' esperado após o nome da estrutura.");
                }
            } else {
                erroSintatico("Identificador esperado após 'struct'.");
            }
        } else {
            erroSintatico("Palavra reservada 'struct' esperada.");
        }
    }
   }