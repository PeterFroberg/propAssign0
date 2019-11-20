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
    }

    @Override
    public Lexeme current() {
        return currentLexeme;
    }

    @Override
    public void moveNext() throws IOException, TokenizerException {
        if (scanner == null) {
            throw new IOException("No open file");
        }

        currentLexeme = nextLexeme;

        if (nextLexeme.token() != Token.EOF) {
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
        //char currentChar = scanner.current();
        //scanner.moveNext();

        Lexeme tempLexeme;
        switch (scanner.current()) {
            case '+':
                tempLexeme = new Lexeme(scanner.current(), Token.ADD_OP);
                scanner.moveNext();
                return tempLexeme;
            case '-':
                tempLexeme = new Lexeme(scanner.current(), Token.SUB_OP);
                scanner.moveNext();
                return tempLexeme;
            case '*':
                tempLexeme = new Lexeme(scanner.current(), Token.MULT_OP);
                scanner.moveNext();
                return tempLexeme;
            case '/':
                tempLexeme = new Lexeme(scanner.current(), Token.DIV_OP);
                scanner.moveNext();
                return tempLexeme;
            case '{':
                tempLexeme = new Lexeme(scanner.current(), Token.LEFT_CURLY);
                scanner.moveNext();
                return tempLexeme;
            case '}':
                tempLexeme = new Lexeme(scanner.current(), Token.RIGHT_CURLY);
                scanner.moveNext();
                return tempLexeme;
            case '(':
                tempLexeme = new Lexeme(scanner.current(), Token.LEFT_PAREN);
                scanner.moveNext();
                return tempLexeme;
            case ')':
                tempLexeme = new Lexeme(scanner.current(), Token.RIGHT_PAREN);
                scanner.moveNext();
                return tempLexeme;
            case '=':
                tempLexeme = new Lexeme(scanner.current(), Token.ASSIGN_OP);
                scanner.moveNext();
                return tempLexeme;
            case ';':
                tempLexeme = new Lexeme(scanner.current(), Token.SEMICOLON);
                scanner.moveNext();
                return tempLexeme;
            case Scanner.EOF:
                tempLexeme = new Lexeme(scanner.current(), Token.EOF);
                scanner.moveNext();
                return tempLexeme;
            case Scanner.NULL:
                tempLexeme = new Lexeme(scanner.current(), Token.NULL);
                scanner.moveNext();
                return tempLexeme;

            default:
                if (Character.isLetter(scanner.current()) && scanner.current() >= 'a' && scanner.current() <= 'z') {
                    StringBuilder letterBuilder = new StringBuilder();
                    while (Character.isLetter(scanner.current())) {
                        letterBuilder.append(scanner.current());
                        scanner.moveNext();
                        //currentChar = scanner.current();
                    }
                    return new Lexeme(letterBuilder.toString(), Token.IDENT);
                } else if (Character.isDigit(scanner.current()) && scanner.current() >= '0' && scanner.current() <= '9') {
                    StringBuilder digitBuilder = new StringBuilder();
                    while (Character.isDigit(scanner.current())) {
                        digitBuilder.append(scanner.current());
                        scanner.moveNext();
                        //currentChar = scanner.current();
                    }
                    return new Lexeme(digitBuilder.toString(), Token.INT_LIT);
                }
                throw new TokenizerException("Illegal token found in file!");

        }


    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public Lexeme getCurrentLexeme() {
        return currentLexeme;
    }

    public void setCurrentLexeme(Lexeme currentLexeme) {
        this.currentLexeme = currentLexeme;
    }

    public Lexeme getNextLexeme() {
        return nextLexeme;
    }

    public void setNextLexeme(Lexeme nextLexeme) {
        this.nextLexeme = nextLexeme;
    }
}
