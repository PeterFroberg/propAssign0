package com.company;

public class AssignmentNode implements INode {

    private Lexeme id;
    private ExpressionNode expression;

    public AssignmentNode(Tokenizer tokenizer) throws Exception{
        tokenizer.moveNext();

        /*if(tokenizer.getCurrentLexeme().token() == Token.IDENT){
            System.out.println(tokenizer.getCurrentLexeme().token().toString());
            tokenizer.moveNext();*/

            if(tokenizer.getCurrentLexeme().token() == Token.ASSIGN_OP){
                System.out.println("AssignmentNode: " + tokenizer.getCurrentLexeme().token().toString());
                tokenizer.moveNext();
                expression = new ExpressionNode(tokenizer);

                if(tokenizer.getCurrentLexeme().token() == Token.SEMICOLON){
                    System.out.println("AssignmentNode: " + tokenizer.getCurrentLexeme().token().toString());
                    tokenizer.moveNext();

                }/*else{
                    throw new ParserException("Wrong token found in AssignmentNode SEMICOLON expected! " + tokenizer.getCurrentLexeme().token().toString() + " was found");
                }*/

            }/*else{
                throw new ParserException("Wrong token found in AssignmentNode ASSIGN_OP expected! " + tokenizer.getCurrentLexeme().token().toString() + " was found");
            }*/
        /*}else {
            throw new ParserException("Wrong token found in AssignmentNode IDENT expected! " + tokenizer.getCurrentLexeme().token().toString() + " was found");
        }*/


        /*if(tokenizer.getCurrentLexeme().token()!= Token.ASSIGN_OP){
            throw new ParserException("Wrong token found in AssignmentNode ASSIGN_OP expected! " + tokenizer.getCurrentLexeme().token().toString() + "was found");
        }
        System.out.println(tokenizer.getCurrentLexeme().toString());

        tokenizer.moveNext();

        expression = new ExpressionNode(tokenizer);

        tokenizer.moveNext();

        if(tokenizer.getCurrentLexeme().token() != Token.SEMICOLON){
            throw new ParserException("Wrong token found in AssignmentNode SEMICOLON expected!"+ tokenizer.getCurrentLexeme().token().toString() + "was found");
        }*/


    }


    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

    }
}

