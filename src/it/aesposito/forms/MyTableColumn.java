/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.forms;

/**
 *
 * @author Antonio
 */
public class MyTableColumn {
    private String name;
    private Class type;
    private String beanDataMapping;
    private String prefisso = "";
    private String suffisso = "";
    private int preferredSize = 0;
    
    public MyTableColumn(String name, Class type, String beanDataMapping,int preferredSize){
        this.name = name;
        this.type = type;
        this.beanDataMapping = beanDataMapping;
        this.preferredSize = preferredSize;
    }
    
    public MyTableColumn(String name, Class type, String beanDataMapping,String prefisso,String suffisso,int preferredSize){
        this.name = name;
        this.type = type;
        this.beanDataMapping = beanDataMapping;
        this.prefisso = prefisso;
        this.suffisso = suffisso;
        this.preferredSize = preferredSize;
    }

    public String getPrefisso() {
        return prefisso;
    }

    public void setPrefisso(String prefisso) {
        this.prefisso = prefisso;
    }

    public String getSuffisso() {
        return suffisso;
    }

    public void setSuffisso(String suffisso) {
        this.suffisso = suffisso;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getType() {
        return type;
    }

    public void setType(Class type) {
        this.type = type;
    }

    public String getBeanDataMapping() {
        return beanDataMapping;
    }

    public void setBeanDataMapping(String beanDataMapping) {
        this.beanDataMapping = beanDataMapping;
    }

    public int getPreferredSize() {
        return preferredSize;
    }

    public void setPreferredSize(int preferredSize) {
        this.preferredSize = preferredSize;
    }
    
}
