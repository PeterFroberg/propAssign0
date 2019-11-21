package com.company;

import com.sun.jdi.Value;

import java.awt.image.TileObserver;

public class TermNode implements INode {

    private FactorNode factor;
    private Lexeme operator;
    private TermNode term;

    public TermNode(Tokenizer tokenizer) throws Exception {
        factor = new FactorNode(tokenizer);

        if(tokenizer.getCurrentLexeme().token() == Token.MULT_OP || tokenizer.getCurrentLexeme().token() == Token.DIV_OP){
            System.out.println("TermNode: " + tokenizer.getCurrentLexeme().toString());
            operator = tokenizer.getCurrentLexeme();
            tokenizer.moveNext();

            term = new TermNode(tokenizer);
            /*if(tokenizer.getCurrentLexeme().token() == Token.INT_LIT || tokenizer.getCurrentLexeme().token() == Token.IDENT || tokenizer.getCurrentLexeme().token() == Token.LEFT_PAREN){
                System.out.println("TermNode: " + tokenizer.getCurrentLexeme().toString() + " - NodeLevel: " + nodeAtLevel);
                term = new TermNode(tokenizer, nodeAtLevel + 1);
            }*/
        }else if (tokenizer.current().token() != Token.SUB_OP && tokenizer.current().token() != Token.ADD_OP && tokenizer.current().token() != Token.INT_LIT && tokenizer.current().token() != Token.IDENT && tokenizer.current().token() != Token.RIGHT_PAREN && tokenizer.current().token() != Token.SEMICOLON) {
            throw new ParserException("Wrong token, expected: MULT_OP or DIV_OP, got: " + tokenizer.getCurrentLexeme().token().toString());


        }
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {


        Double factorvalue = Double.parseDouble(factor.evaluate(args).toString());
        if(term == null){
            return factorvalue;
        }else{
            Double termValue;
            if(term.operator!= null && term.operator.token() == Token.DIV_OP){
                Double secondTermFactor = Double.parseDouble(term.getFactor().evaluate(args).toString());
                factorvalue = factorvalue / secondTermFactor;
                termValue = Double.parseDouble(term.term.evaluate(args).toString());
            }else {
                termValue = Double.parseDouble(term.evaluate(args).toString());
            }
            if(operator.token() == Token.MULT_OP){
                return factorvalue * termValue;
            }else{
                return factorvalue / termValue;

            }
        }
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        builder.append(insertTabs(tabs) + "TermNode\n");

        if(factor != null){
            factor.buildString(builder, tabs + 1);
        }

        if(operator != null){
            builder.append(insertTabs(tabs + 1) + operator + "\n");
        }

        if(term != null){
            term.buildString(builder, tabs +1);
        }



    }

    private String insertTabs(int tabs){
        String tabsToadd = "";
        for (int i = 0; i < tabs; i++) {
            tabsToadd = tabsToadd + "\t";
        }
        return tabsToadd;
    }

    public FactorNode getFactor() {
        return factor;
    }
}
