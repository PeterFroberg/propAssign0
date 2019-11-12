package com.company;

public class StatementNode implements INode {

    private AssignmentNode assignment;
    private StatementNode statment;

    @Override
    public Object evaluate(Object[] args) throws Exception {
        return null;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

    }
}
