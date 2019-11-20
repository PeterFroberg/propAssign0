package com.company;

public class FactorNode implements INode {

    private Lexeme id;
    private Lexeme rightParent;
    private ExpressionNode expression;

    public FactorNode(Tokenizer tokenizer) throws Exception {

        if (tokenizer.getCurrentLexeme().token() == Token.INT_LIT || tokenizer.getCurrentLexeme().token() == Token.IDENT) { // || tokenizer.getCurrentLexeme().token() == Token.LEFT_PAREN) {
            System.out.println("FactorNode: " + tokenizer.getCurrentLexeme().toString());
            id = tokenizer.getCurrentLexeme();
            tokenizer.moveNext();


        } else if (tokenizer.getCurrentLexeme().token() == Token.LEFT_PAREN) {
            System.out.println("Factor: " + tokenizer.getCurrentLexeme().toString());
            id = tokenizer.getCurrentLexeme();
            tokenizer.moveNext();
            expression = new ExpressionNode(tokenizer);
            if (tokenizer.getCurrentLexeme().token() == Token.RIGHT_PAREN) {
                rightParent = tokenizer.getCurrentLexeme();
                System.out.println("FactorNode: RIGHT" + tokenizer.getCurrentLexeme().toString());
                tokenizer.moveNext();
            } else {
                throw new ParserException("Wrong token, expected: RIGHT_PAREN, got: " + tokenizer.getCurrentLexeme().token().toString());
            }

        } else
            throw new ParserException("Wrong token, expected: LEFT_PAREM, got: " + tokenizer.getCurrentLexeme().token().toString());


    }

    //}

    @Override
    public Object evaluate(Object[] args) throws Exception {
        if (expression == null) {
            if (id.token() == Token.INT_LIT) {
                return id.value();
            } else if (id.token() == Token.IDENT) {
                for (int i = 0; i < args.length; i++) {
                    VarResult v = (VarResult) args[i];
                    if (v.getId().equals(id.value().toString())) {
                        return v.getValue();
                    }
                }
            }
        }
        return expression.evaluate(args);


    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

        builder.append(insertTabs(tabs) + "FactorNode\n");

        if (id != null) {
            builder.append(insertTabs(tabs + 1) + id + "\n");
        }

        if (expression != null) {
            expression.buildString(builder, tabs + 1);
            builder.append(insertTabs(tabs + 1) + rightParent + "\n");
        }


    }

    private String insertTabs(int tabs) {
        String tabsToadd = "";
        for (int i = 0; i < tabs; i++) {
            tabsToadd = tabsToadd + "\t";
        }
        return tabsToadd;
    }
}
