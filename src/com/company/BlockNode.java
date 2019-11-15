package com.company;

public class BlockNode implements INode {

    private StatementNode statementNode;
    private int nodeAtLevel;

    public BlockNode(Tokenizer tokenizer, int nodeAtLevel) throws Exception{

        this.nodeAtLevel = nodeAtLevel;

        if (tokenizer.getCurrentLexeme().token() != Token.LEFT_CURLY) {
            throw new ParserException("Wrong token found at begining av program { expected!");
        }
        System.out.println("BlockNode: " + tokenizer.getCurrentLexeme().toString() + " - NodeLevel: " + nodeAtLevel);
        tokenizer.moveNext();
        statementNode = new StatementNode(tokenizer, nodeAtLevel + 1);

        if(tokenizer.getCurrentLexeme().token() != Token.RIGHT_CURLY){
            throw new ParserException("Wrong token found at end av program } expected!");
        }
        System.out.println("BlockNode: " + tokenizer.getCurrentLexeme().toString() + " - NodeLevel: " + nodeAtLevel);
        tokenizer.moveNext();
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        if (statementNode != null){
            builder.append("BlockNode\n");
            builder.append(Token.LEFT_CURLY + "\n");
            statementNode.buildString(builder, tabs + 1);
            builder.append(Token.RIGHT_CURLY + " \n");

        }

    }

    public StatementNode getStatementNode() {
        return statementNode;
    }

    public void setStatementNode(StatementNode statementNode) {
        this.statementNode = statementNode;
    }
}
