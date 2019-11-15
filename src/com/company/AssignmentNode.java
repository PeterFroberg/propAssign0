package com.company;

public class AssignmentNode implements INode {

    private Lexeme id;
    private ExpressionNode expression;
    private int nodeAtLevel;

    public AssignmentNode(Tokenizer tokenizer, int nodeAtLevel) throws Exception {
        this.nodeAtLevel = nodeAtLevel;

        tokenizer.moveNext();

        if (tokenizer.getCurrentLexeme().token() == Token.ASSIGN_OP) {
            System.out.println("AssignmentNode: " + tokenizer.getCurrentLexeme().token().toString() + " - NodeLevel: " + nodeAtLevel);
            tokenizer.moveNext();
            id = tokenizer.getCurrentLexeme();
            expression = new ExpressionNode(tokenizer, nodeAtLevel + 1);

            if (tokenizer.getCurrentLexeme().token() == Token.SEMICOLON) {
                System.out.println("AssignmentNode: " + tokenizer.getCurrentLexeme().token().toString() + " - NodeLevel: " + nodeAtLevel);
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

        builder.append(insertTabs(tabs) + "AssigmentNode\n" + insertTabs(tabs +1) + id + "\n" + insertTabs(tabs + 1) + Token.ASSIGN_OP + " =\n");
        if(expression != null){
            expression.buildString(builder, tabs + 1);
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

