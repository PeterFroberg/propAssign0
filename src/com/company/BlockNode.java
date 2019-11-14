package com.company;

public class BlockNode implements INode {

    private StatementNode statementNode;

    public BlockNode(Tokenizer tokenizer) throws Exception{

        if (tokenizer.getCurrentLexeme().token() != Token.LEFT_CURLY) {
            throw new ParserException("Wrong token found at begining av program { expected!");
        }
        System.out.println("BlockNode: " + tokenizer.getCurrentLexeme().toString());
        tokenizer.moveNext();
        statementNode = new StatementNode(tokenizer);

        if(tokenizer.getCurrentLexeme().token() != Token.RIGHT_CURLY){
            throw new ParserException("Wrong token found at end av program } expected!");
        }
        System.out.println("BlockNode: " + tokenizer.getCurrentLexeme().toString());
        tokenizer.moveNext();
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

    }

    public StatementNode getStatementNode() {
        return statementNode;
    }

    public void setStatementNode(StatementNode statementNode) {
        this.statementNode = statementNode;
    }
}
