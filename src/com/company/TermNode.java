package com.company;

import com.sun.jdi.Value;

import java.awt.image.TileObserver;

public class TermNode implements INode {

    private FactorNode factor;
    private Lexeme operator;
    private TermNode term;

    public TermNode(Tokenizer tokenizer) throws Exception {
        factor = new FactorNode(tokenizer);

        if (tokenizer.getCurrentLexeme().token() == Token.MULT_OP || tokenizer.getCurrentLexeme().token() == Token.DIV_OP) {
            System.out.println("TermNode: " + tokenizer.getCurrentLexeme().toString());
            operator = tokenizer.getCurrentLexeme();
            tokenizer.moveNext();

            term = new TermNode(tokenizer);
            /*if(tokenizer.getCurrentLexeme().token() == Token.INT_LIT || tokenizer.getCurrentLexeme().token() == Token.IDENT || tokenizer.getCurrentLexeme().token() == Token.LEFT_PAREN){
                System.out.println("TermNode: " + tokenizer.getCurrentLexeme().toString() + " - NodeLevel: " + nodeAtLevel);
                term = new TermNode(tokenizer, nodeAtLevel + 1);
            }*/
        } else if (tokenizer.current().token() != Token.SUB_OP && tokenizer.current().token() != Token.ADD_OP && tokenizer.current().token() != Token.INT_LIT && tokenizer.current().token() != Token.IDENT && tokenizer.current().token() != Token.RIGHT_PAREN && tokenizer.current().token() != Token.SEMICOLON) {
            throw new ParserException("Wrong token, expected: MULT_OP or DIV_OP, got: " + tokenizer.getCurrentLexeme().token().toString());


        }
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {

        Double prevDiv = 0.0;
        int i = 0;
        double termNodeValue;
        double factorNodeValue = Double.parseDouble(factor.evaluate(args).toString());

        if(args[0] != null) {
            for (i = 0; i < args.length; i++) {
                if(args[i] == null) {
                    break;
                }
                    VarResult arg = (VarResult) args[i];
                    if (arg.getId() == "/") {
                        prevDiv = arg.getValue();
                        args[i] = null;
                        break;
                    }

            }
        }


        if (term == null) {
            return factorNodeValue;
        } else {
            if (operator.token() == Token.DIV_OP && prevDiv == 0.0) {
                if (term.operator != null && term.operator.token() == Token.DIV_OP) {
                    args[i] = new VarResult("/", factorNodeValue);
                    return term.evaluate(args);

                } else {
                    termNodeValue = Double.parseDouble(term.evaluate(args).toString());
                }return factorNodeValue / termNodeValue;

                //factorNodeValue = factorNodeValue / prevDouble;
            } else if (term.operator != null && term.operator.token() == Token.DIV_OP) {
                Double temp = prevDiv / factorNodeValue;
                args[i] = new VarResult("/", temp);

                //term.setPrevDouble(temp);
                return term.evaluate(args);
            } else if(operator.token() == Token.DIV_OP){
                factorNodeValue = prevDiv / factorNodeValue;
                return factorNodeValue / Double.parseDouble(term.evaluate(args).toString());
            }else {
                termNodeValue = Double.parseDouble(term.evaluate(args).toString());
                return factorNodeValue * termNodeValue;
            }

        }

        //return factorNodeValue;





        /*Double factorvalue = Double.parseDouble(factor.evaluate(args).toString());
        if(term == null){
            return factorvalue;
        }else{
            Double termValue;
            if(operator != null && operator.token() == Token.DIV_OP && term.operator!= null && term.operator.token() == Token.DIV_OP){

                Double secondTermFactor = Double.parseDouble(term.getFactor().evaluate(args).toString());
                if(term.term.operator != null && term.term.operator.token() == Token.DIV_OP){
                    factorvalue = factorvalue / secondTermFactor;
                    term.evaluate(args);
                }

                termValue = Double.parseDouble(term.evaluate(args).toString());
            }else {
                termValue = Double.parseDouble(term.evaluate(args).toString());
            }
            if(operator.token() == Token.MULT_OP){
                return factorvalue * termValue;
            }else{
                return factorvalue / termValue;

            }
        }*/
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        builder.append(insertTabs(tabs) + "TermNode\n");

        if (factor != null) {
            factor.buildString(builder, tabs + 1);
        }

        if (operator != null) {
            builder.append(insertTabs(tabs + 1) + operator + "\n");
        }

        if (term != null) {
            term.buildString(builder, tabs + 1);
        }


    }

    private String insertTabs(int tabs) {
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
