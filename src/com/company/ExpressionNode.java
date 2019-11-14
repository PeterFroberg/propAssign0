package com.company;

public class ExpressionNode implements INode {

    private TermNode term;
    private Lexeme operator;
    private ExpressionNode expression;

    public ExpressionNode(Tokenizer tokenizer) throws Exception{


        System.out.println("ExpressionNode: " + tokenizer.getCurrentLexeme().toString());
        term = new TermNode(tokenizer);

        if(tokenizer.getCurrentLexeme().token() == Token.SUB_OP || tokenizer.getCurrentLexeme().token() == Token.ADD_OP){
            System.out.println("ExpressionNode: " + tokenizer.getCurrentLexeme().toString());
            tokenizer.moveNext();
            expression = new ExpressionNode(tokenizer);
        }/*else{
            throw new ParserException("Wrong token found in ExpressionNode IDENT, INT_LIT or LEFT_PAREN expected! " + tokenizer.getCurrentLexeme().token().toString() + "was found");
        }*/

        //tokenizer.moveNext();




    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

    }
}
