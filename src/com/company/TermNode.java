package com.company;

public class TermNode implements INode {

    private FactorNode factor;
    private Lexeme operator;
    private TermNode term;

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

    }
}
