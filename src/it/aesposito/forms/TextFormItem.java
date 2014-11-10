/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.aesposito.forms;

/**
 *
 * @author Antonio
 */
public abstract class TextFormItem extends FormItem{
    protected int textLimit;
    
    public TextFormItem(String name, Class dataMappingType, String caption, boolean required, FormItemType type, int textLimit, Object defaultValue, boolean visible){
        super(name,dataMappingType,caption,required,type,defaultValue,visible);
        this.textLimit = textLimit;
    }
    
}
