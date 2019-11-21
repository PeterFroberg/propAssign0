package com.company;

public class AssignmentNode implements INode {

    private Lexeme id;
    private ExpressionNode expression;


    public AssignmentNode(Tokenizer tokenizer) throws Exception {

        if(tokenizer.getCurrentLexeme().token() == Token.IDENT) {
            id = tokenizer.getCurrentLexeme();
            tokenizer.moveNext();

            if (tokenizer.getCurrentLexeme().token() == Token.ASSIGN_OP) {
                System.out.println("AssignmentNode: " + tokenizer.getCurrentLexeme().token().toString() + " - NodeLevel: ");
                //id = tokenizer.getCurrentLexeme();
                tokenizer.moveNext();
                expression = new ExpressionNode(tokenizer, null);

                if (tokenizer.getCurrentLexeme().token() == Token.SEMICOLON) {
                    System.out.println("AssignmentNode: " + tokenizer.getCurrentLexeme().token().toString() + " - NodeLevel: ");
                    tokenizer.moveNext();
                }
            }
        }
    }


    @Override
    public Object evaluate(Object[] args) throws Exception {

        return new VarResult(id.value().toString(), Double.parseDouble(expression.evaluate(args).toString()));
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

        builder.append(insertTabs(tabs) + "AssigmentNode\n" + insertTabs(tabs +1) + id + "\n" + insertTabs(tabs + 1) + Token.ASSIGN_OP + " =\n");
        /*if(id != null){
            builder.append(insertTabs(tabs) + id + "\n");
        }*/


        if(expression != null){
            expression.buildString(builder, tabs + 1);
        }

        builder.append(insertTabs(tabs + 1) + Token.SEMICOLON + " ;\n");

    }

    private String insertTabs(int tabs){
        String tabsToadd = "";
        for (int i = 0; i < tabs; i++) {
            tabsToadd = tabsToadd + "\t";
        }
        return tabsToadd;
    }
}

