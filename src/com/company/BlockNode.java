package com.company;

import java.util.ArrayList;
import java.util.HashMap;


public class BlockNode implements INode {

    private StatementNode statementNode;


    public BlockNode(Tokenizer tokenizer) throws Exception{

        if (tokenizer.getCurrentLexeme().token() != Token.LEFT_CURLY) {
            throw new ParserException("Wrong token found at begining av program { expected!");
        }
        System.out.println("BlockNode: " + tokenizer.getCurrentLexeme().toString() + " - NodeLevel: ");
        tokenizer.moveNext();
        statementNode = new StatementNode(tokenizer);

        if(tokenizer.getCurrentLexeme().token() != Token.RIGHT_CURLY){
            throw new ParserException("Wrong token found at end av program } expected!");
        }
        System.out.println("BlockNode: " + tokenizer.getCurrentLexeme().toString() + " - NodeLevel: ");
        tokenizer.moveNext();
    }

    @Override
    public Object evaluate(Object[] args) throws Exception {
        if(statementNode != null) {
            VarResult[] varArray = new VarResult[100];
            return statementNode.evaluate(varArray);

           /* String finalResult = "";
            for (VarResult v : varArray){
                finalResult = finalResult + v.getId() + " = " + v.getValue() + "\n";
            }*/
        }



        //Object[] objects;

        /*ArrayList<Object> temp = new ArrayList<>(); //(Arrays.asList(args));
        temp.add(new VarResult("a", 5.0));
        temp.add(new VarResult("b", 7.0));
        //Object[] objects;
        objects = temp.toArray();


        statementNode.evaluate(objects);

        String finalResult = "";

        for(Object o : args){
            if(o instanceof VarResult){
                VarResult tempO = (VarResult)o;
                finalResult = finalResult + tempO.getId() + " = " + tempO.getValue() + "\n";



            }
            //System.out.println(o);
        }*/
        return null; //finalResult;
    }

    @Override
    public void buildString(StringBuilder builder, int tabs) {

        builder.append("BlockNode\n");
        builder.append(Token.LEFT_CURLY + " {\n");

        if (statementNode != null){
            statementNode.buildString(builder, tabs + 1);
        }

        builder.append(Token.RIGHT_CURLY + " }\n");
    }

    public StatementNode getStatementNode() {
        return statementNode;
    }

    public void setStatementNode(StatementNode statementNode) {
        this.statementNode = statementNode;
    }

}
