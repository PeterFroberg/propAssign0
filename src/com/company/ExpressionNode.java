package com.company;

public class ExpressionNode implements INode {

    private TermNode term;
    private Lexeme operator;
    private ExpressionNode expression;
    private int nodeAtLevel;

    public ExpressionNode(Tokenizer tokenizer, int nodeAtLevel) throws Exception{
        this.nodeAtLevel = nodeAtLevel;

        System.out.println("ExpressionNode: " + tokenizer.getCurrentLexeme().toString() + " - NodeLevel: " + nodeAtLevel);
        term = new TermNode(tokenizer, nodeAtLevel + 1);

        if(tokenizer.getCurrentLexeme().token() == Token.SUB_OP || tokenizer.getCurrentLexeme().token() == Token.ADD_OP){
            System.out.println("ExpressionNode: " + tokenizer.getCurrentLexeme().toString() + " - NodeLevel: " + nodeAtLevel);
            operator = tokenizer.getCurrentLexeme();
            tokenizer.moveNext();
            expression = new ExpressionNode(tokenizer, nodeAtLevel + 1);
        }
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        builder.append(insertTabs(tabs) + "ExpressionNode\n");
        if(term != null){
            term.buildString(builder, tabs + 1);
        }

        if(expression != null){
            expression.buildString(builder, tabs + 2);
        }

        if(operator != null){
            builder.append(insertTabs(tabs + 1) + operator + "\n");
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
