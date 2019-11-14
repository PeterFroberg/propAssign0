package com.company;

import java.awt.image.TileObserver;

public class TermNode implements INode {

    private FactorNode factor;
    private Lexeme operator;
    private TermNode term;

    public TermNode(Tokenizer tokenizer) throws Exception {
        factor = new FactorNode(tokenizer);

        if(tokenizer.getCurrentLexeme().token() == Token.MULT_OP || tokenizer.getCurrentLexeme().token() == Token.DIV_OP){
            System.out.println("TermNode: " + tokenizer.getCurrentLexeme().toString());
            tokenizer.moveNext();
            if(tokenizer.getCurrentLexeme().token() == Token.INT_LIT || tokenizer.getCurrentLexeme().token() == Token.IDENT || tokenizer.getCurrentLexeme().token() == Token.LEFT_PAREN){
                System.out.println("TermNode: " + tokenizer.getCurrentLexeme().toString());
                term = new TermNode(tokenizer);
            }
        }


        /*if(tokenizer.getCurrentLexeme().token() != Token.MULT_OP && tokenizer.getCurrentLexeme().token() != Token.DIV_OP){
            throw new ParserException("Wrong token found in TERMNODE MULT_OP or DIV_OP expected! " + tokenizer.getCurrentLexeme().token().toString() + "was found");
        }
        System.out.println(tokenizer.getCurrentLexeme().toString());
        tokenizer.moveNext();
        if(tokenizer.getCurrentLexeme().token() !=  Token.IDENT && tokenizer.getCurrentLexeme().token() != Token.INT_LIT && tokenizer.getCurrentLexeme().token() != Token.LEFT_PAREN){
            throw new ParserException("Wrong token found in TERMNODE MULT_OP or DIV_OP expected! " + tokenizer.getCurrentLexeme().token().toString() + "was found");
        }
        System.out.println(tokenizer.getCurrentLexeme().toString());
        term = new TermNode(tokenizer);*/

    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

    }
}
