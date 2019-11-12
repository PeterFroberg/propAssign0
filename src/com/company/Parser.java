package com.company;

import java.io.IOException;

public class Parser implements IParser {

    private Tokenizer tokenizer = new Tokenizer();

    @Override
    public void open(String fileName) throws IOException, TokenizerException {
        //tokenizer = new Tokenizer();
        tokenizer.open(fileName);

        //tokenizer.moveNext();
        while (tokenizer.getNextLexeme().token() != Token.EOF){

            tokenizer.moveNext();
        }
        System.out.println("This is the end my beautiful friend");


    }

    @Override
    public INode parse() throws IOException, TokenizerException, ParserException {
        if(tokenizer == null){
            throw new IOException("No file open");
        }

        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
