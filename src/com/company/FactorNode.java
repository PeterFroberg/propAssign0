package com.company;

public class FactorNode implements INode {

    private Lexeme id;
    private ExpressionNode expression;
    private int nodeAtLevel;

    public FactorNode(Tokenizer tokenizer, int nodeAtLevel) throws Exception {
        this.nodeAtLevel = nodeAtLevel;

        if(tokenizer.getCurrentLexeme().token() == Token.INT_LIT || tokenizer.getCurrentLexeme().token() == Token.IDENT){
            System.out.println("FactorNode: " + tokenizer.getCurrentLexeme().toString() + " - NodeLevel: " + nodeAtLevel);
            id = tokenizer.getCurrentLexeme();
            tokenizer.moveNext();
        }else if (tokenizer.getCurrentLexeme().token() == Token.LEFT_PAREN) {
            System.out.println("Factor: " + tokenizer.getCurrentLexeme().toString() + " - NodeLevel: " + nodeAtLevel);
            tokenizer.moveNext();
            id = tokenizer.getCurrentLexeme();
            expression = new ExpressionNode(tokenizer, nodeAtLevel +1);
            if(tokenizer.getCurrentLexeme().token() == Token.RIGHT_PAREN){
                System.out.println("FactorNode: " + tokenizer.getCurrentLexeme().toString() + " - NodeLevel: " + nodeAtLevel);
                tokenizer.moveNext();
            }
        }

    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

        builder.append(insertTabs(tabs) + "FactorNode\n" + insertTabs(tabs + 1) + id + "\n");
    }

    private String insertTabs(int tabs){
        String tabsToadd = "";
        for (int i = 0; i < tabs; i++) {
            tabsToadd = tabsToadd + "\t";
        }
        return tabsToadd;
    }
}
