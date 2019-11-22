// Peter Fröberg, pefr7147@student.su.se
// Douglas Hammarstam, doha6991@student.su.se
package com.company;

public class ExpressionNode implements INode {

    private TermNode term;
    private Lexeme operator;
    private Lexeme previousOperator;
    private ExpressionNode expression;

    public ExpressionNode(Tokenizer tokenizer, Lexeme previousOperator) throws Exception {
        this.previousOperator = previousOperator;

        term = new TermNode(tokenizer);

        if (tokenizer.getCurrentLexeme().token() == Token.SUB_OP || tokenizer.getCurrentLexeme().token() == Token.ADD_OP) {
            operator = tokenizer.getCurrentLexeme();
            tokenizer.moveNext();
            expression = new ExpressionNode(tokenizer, operator);
        } else if (tokenizer.current().token() != Token.INT_LIT && tokenizer.current().token() != Token.IDENT && tokenizer.current().token() != Token.RIGHT_PAREN && tokenizer.current().token() != Token.SEMICOLON && tokenizer.getNextLexeme() != null && tokenizer.getNextLexeme().token() != Token.EOF) {
            throw new ParserException("Wrong token, expected: SUB_OP OR ADD_OP, got: " + tokenizer.getCurrentLexeme().token().toString());
        }
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {

        double termNodeValue = Double.parseDouble(term.evaluate(args).toString());

        if (expression == null) {
            return termNodeValue;
        } else {
            double exprNodeValue = Double.parseDouble(expression.evaluate(args).toString());
            if (operator.token() == Token.ADD_OP) {
                if (previousOperator != null && previousOperator.token() == Token.SUB_OP) {
                    return exprNodeValue - termNodeValue;
                }
                return termNodeValue + exprNodeValue;
            } else {
                return termNodeValue - exprNodeValue;
            }
        }
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
