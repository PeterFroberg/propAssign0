package com.company;

public class FactorNode implements INode {

    private Lexeme number_id;
    private ExpressionNode expression;

    public FactorNode(Tokenizer tokenizer) throws Exception {

        if(tokenizer.getCurrentLexeme().token() == Token.INT_LIT || tokenizer.getCurrentLexeme().token() == Token.IDENT){
            System.out.println("FactorNode: " + tokenizer.getCurrentLexeme().toString());
            tokenizer.moveNext();
        }else if (tokenizer.getCurrentLexeme().token() == Token.LEFT_PAREN) {
            System.out.println("Factor: " + tokenizer.getCurrentLexeme().toString());
            tokenizer.moveNext();
            expression = new ExpressionNode(tokenizer);
            if(tokenizer.getCurrentLexeme().token() == Token.RIGHT_PAREN){
                System.out.println("FactorNode: " + tokenizer.getCurrentLexeme().toString());
                tokenizer.moveNext();
            }
        }
        /*System.out.println(tokenizer.getCurrentLexeme().toString());
        tokenizer.moveNext();
        expression = new ExpressionNode(tokenizer);
        if (tokenizer.getCurrentLexeme().token() != Token.RIGHT_PAREN) {
            throw new Exception("Wrong token found in FactoNode RIGHT_PAREN expected! " + tokenizer.getCurrentLexeme().token().toString() + "was found");
        }
        tokenizer.moveNext();*/

    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

    }
}
