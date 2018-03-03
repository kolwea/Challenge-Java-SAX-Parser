/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saxxmlparser;

import java.util.ArrayList;

/**
 *
 * @author Kolbe
 */
public class TreeNode {

    private ArrayList<TreeNode> children;
    private TreeNode parent;
    private String key;
    private ArrayList<String> attributes;
    private String value;

    public TreeNode(String key) {
        this.key = key;
    }

    public TreeNode(String key, Object Value) {
        this.key = key;
    }
    
    public void setParent(TreeNode papa){
        this.parent = papa;
    }
    
    public void setValue(String value){
        this.value = value;
    }
    
    public TreeNode getParent(){
        return this.parent;
    }

    public void addChild(TreeNode child) {
        if (children == null) {
            children = new ArrayList();
        }
        children.add(child);
//PRINTS
//    System.out.println("Adding " + child.getKey() + " to " + this.key);
    }
    
    public String getKey(){
        return this.key;
    }
    
    public String getValue(){
        return this.value;
    }
    
    public ArrayList<TreeNode> getChildren(){
        return this.children;
    }
    
    public void addAttrbute(String duh){
        if(attributes == null)
            attributes = new ArrayList();
        attributes.add(duh);
    }
    
    public ArrayList<String> getAttributes(){
        return this.attributes;
    }

}
