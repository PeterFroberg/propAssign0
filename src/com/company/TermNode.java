package com.company;

import java.awt.image.TileObserver;

public class TermNode implements INode {

    private FactorNode factor;
    private Lexeme operator;
    private TermNode term;
    private int nodeAtLevel;

    public TermNode(Tokenizer tokenizer, int nodeAtLevel) throws Exception {
        this.nodeAtLevel = nodeAtLevel;
        factor = new FactorNode(tokenizer, nodeAtLevel + 1);

        if(tokenizer.getCurrentLexeme().token() == Token.MULT_OP || tokenizer.getCurrentLexeme().token() == Token.DIV_OP){
            System.out.println("TermNode: " + tokenizer.getCurrentLexeme().toString() + " - NodeLevel: " + nodeAtLevel);
            operator = tokenizer.getCurrentLexeme();
            tokenizer.moveNext();
            if(tokenizer.getCurrentLexeme().token() == Token.INT_LIT || tokenizer.getCurrentLexeme().token() == Token.IDENT || tokenizer.getCurrentLexeme().token() == Token.LEFT_PAREN){
                System.out.println("TermNode: " + tokenizer.getCurrentLexeme().toString() + " - NodeLevel: " + nodeAtLevel);
                term = new TermNode(tokenizer, nodeAtLevel + 1);
            }
        }
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        builder.append(insertTabs(tabs) + "TermNode\n");
        if(factor != null){
            factor.buildString(builder, tabs + 1);
            builder.append(insertTabs(tabs +1 ) + operator + "\n");
        }



    }

    private String insertTabs(int tabs){
        String tabsToadd = "";
        for (int i = 0; i < tabs; i++) {
            tabsToadd = tabsToadd + "\t";
        }
        return tabsToadd;
    }
}
