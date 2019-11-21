package com.company;

public class ExpressionNode implements INode {

    private TermNode term;
    private Lexeme operator;
    private Lexeme previousOperator;
    private ExpressionNode expression;

    public ExpressionNode(Tokenizer tokenizer, Lexeme previousOperator) throws Exception {
        this.previousOperator = previousOperator;

        System.out.println("ExpressionNode: " + tokenizer.getCurrentLexeme().toString() + " - NodeLevel: ");

        term = new TermNode(tokenizer);


        if (tokenizer.getCurrentLexeme().token() == Token.SUB_OP || tokenizer.getCurrentLexeme().token() == Token.ADD_OP) {
            System.out.println("ExpressionNode: " + tokenizer.getCurrentLexeme().toString() + " - NodeLevel: ");
            operator = tokenizer.getCurrentLexeme();
            tokenizer.moveNext();
            expression = new ExpressionNode(tokenizer, operator);
        } else if (tokenizer.current().token() != Token.INT_LIT && tokenizer.current().token() != Token.IDENT && tokenizer.current().token() != Token.RIGHT_PAREN && tokenizer.current().token() != Token.SEMICOLON) {
            throw new ParserException("Wrong token, expected: SUB_OP OR ADD_OP, got: " + tokenizer.getCurrentLexeme().token().toString());
        }

    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        Double termValue = Double.parseDouble(term.evaluate(args).toString());
        if(previousOperator != null && previousOperator.token() == Token.SUB_OP ){
            //double temp = -1.0;
            //termValue = termValue * temp;
            termValue = -termValue;
        }
        if(expression == null){
            return termValue;
        }else {
            Double expressionValue = Double.parseDouble(expression.evaluate(args).toString());
            return termValue + expressionValue;
        }
            /*if(operator.token() == Token.ADD_OP){
                return  termValue + expressionValue;
            }else{
                return termValue - expressionValue;
            }
        }*/
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {
        builder.append(insertTabs(tabs) + "ExpressionNode\n");

        if (term != null) {
            term.buildString(builder, tabs + 1);
        }

        if (operator != null) {
            builder.append(insertTabs(tabs + 1) + operator + "\n");
        }

        if (expression != null) {
            expression.buildString(builder, tabs + 1);
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
