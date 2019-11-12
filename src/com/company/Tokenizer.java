package com.company;

import java.io.IOException;

public class Tokenizer implements ITokenizer {
    private Scanner scanner = null;
    private Lexeme currentLexeme = null;
    private Lexeme nextLexeme = null;

    @Override
    public void open(String fileName) throws IOException, TokenizerException {
        scanner = new Scanner();
        scanner.open(fileName);
        scanner.moveNext();

        nextLexeme = extractLexeme();
        currentLexeme = nextLexeme;

    }

    @Override
    public Lexeme current() {
        return currentLexeme;
    }

    public Lexeme getNextLexeme(){
        return nextLexeme;
    }

    @Override
    public void moveNext() throws IOException, TokenizerException {
        if(scanner == null){
            throw new IOException("No open file");
        }
        currentLexeme = nextLexeme;
        if(nextLexeme.token() != Token.EOF){
            nextLexeme = extractLexeme();
        }

    }

    @Override
    public void close() throws IOException {

    }

    private void consumeWhiteSpaces() throws IOException {
        while (Character.isWhitespace(scanner.current())) {
            scanner.moveNext();
        }
    }

    private Lexeme extractLexeme() throws IOException, TokenizerException {
        // TODO: implement the lexeme extraction algorithm
        consumeWhiteSpaces();
        char currentChar = scanner.current();
        scanner.moveNext();

        switch (currentChar){
            case '+':
                return new Lexeme(currentChar, Token.ADD_OP);
            case '-':
                return new Lexeme(currentChar, Token.SUB_OP);
            case '*':
                return new Lexeme(currentChar, Token.MULT_OP);
            case '/':
                return new Lexeme(currentChar, Token.DIV_OP);
            case '{':
                return new Lexeme(currentChar, Token.LEFT_CURLY);
            case '}':
                return new Lexeme(currentChar, Token.RIGHT_CURLY);
            case '(':
                return new Lexeme(currentChar, Token.LEFT_PAREN);
            case ')':
                return new Lexeme(currentChar, Token.RIGHT_PAREN);
            case '=':
                return new Lexeme(currentChar, Token.ASSIGN_OP);
            case ';':
                return new Lexeme(currentChar,Token.SEMICOLON);
            case Scanner.EOF:
                return new Lexeme(currentChar, Token.EOF);
            case Scanner.NULL:
                return new Lexeme(currentChar, Token.NULL);

                default:
                    if(currentChar >= 'a' && currentChar <= 'z'){
                        return new Lexeme(currentChar, Token.IDENT);
                    }else if(currentChar >= '0' && currentChar <= '9'){
                        return new Lexeme(currentChar, Token.INT_LIT);
                    }
                    throw new TokenizerException("Illegal token found in file!");
        }

    }
}
