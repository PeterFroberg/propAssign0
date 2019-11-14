package com.company;

import java.sql.SQLOutput;

public class StatementNode implements INode {

    private AssignmentNode assignment;
    private StatementNode statment;

    public StatementNode(Tokenizer tokenizer) throws Exception{
        if (tokenizer.getCurrentLexeme().token() == Token.IDENT) {
            System.out.println("StementNode: " + tokenizer.getCurrentLexeme().toString());
            assignment = new AssignmentNode(tokenizer);
            statment = new StatementNode(tokenizer);
        }


    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

    }
}
