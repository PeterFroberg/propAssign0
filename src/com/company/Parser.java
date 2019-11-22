// Peter Fröberg, pefr7147@student.su.se
// Douglas Hammarstam, doha6991@student.su.se
package com.company;

import java.io.IOException;

public class Parser implements IParser {

    private Tokenizer tokenizer = new Tokenizer();

    @Override
    public void open(String fileName) throws IOException, TokenizerException {
        tokenizer.open(fileName);
        tokenizer.setCurrentLexeme(tokenizer.getNextLexeme());
        tokenizer.moveNext();
    }

    @Override
    public INode parse() throws Exception {

        BlockNode rootNode = new BlockNode(tokenizer);

        if (tokenizer == null) {
            throw new IOException("No file open");
        }
        return rootNode;
    }

    @Override
    public void close() throws IOException {

    }
}
