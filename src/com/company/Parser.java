package com.company;

import java.io.IOException;

public class Parser implements IParser {

    private Tokenizer tokenizer = new Tokenizer();

    @Override
    public void open(String fileName) throws IOException, TokenizerException {
        tokenizer.open(fileName);
    }

    @Override
    public INode parse() throws IOException, TokenizerException, ParserException {
        if (tokenizer == null) {
            throw new IOException("No file open");
        }
        while (tokenizer.peekNextLexeme().token() != Token.EOF) {
            tokenizer.moveNext();
        }
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
