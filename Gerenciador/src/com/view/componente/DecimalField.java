/*
 * DecimalField.java
 *
 * Criado em 21 de Maio de 2007, 10:01
 */

package com.view.componente;

import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

import com.google.gwt.user.client.ui.HasHorizontalAlignment.HorizontalAlignmentConstant;
import com.vaadin.event.FieldEvents.TextChangeEvent;
import com.vaadin.event.FieldEvents.TextChangeListener;
import com.vaadin.ui.TextField;
import com.vaadin.ui.AbstractTextField.TextChangeEventMode;


/**
 * JTextField personalizado permitindo somente n�meros inteiros ou decimais.
 *
 * @author Alcides Liberali
 */

@SuppressWarnings({"serial", "unused"})
public class DecimalField extends TextField {
    private Toolkit toolkit;
    private NumberFormat decimalFormatter;
    private int maxDigits;
    private int casasDecimais;
    
    private static final Locale BRAZIL = new Locale("pt","BR");  
    /** 
     * S�mbolos especificos do Real Brasileiro 
     */  
    private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);  
    /** 
     * Mascara de dinheiro para Real Brasileiro 
     */  
    public static final DecimalFormat DINHEIRO_REAL = new DecimalFormat("� ###,###,##0.00",REAL);  

    
    /**
     * Construtor, Constr�i um novo DecimalField com tamanho m�ximo de 99 e com
     * duas casas decimais.
     */
    public DecimalField() {
   
		setLocale(BRAZIL);
        toolkit = Toolkit.getDefaultToolkit();
        Locale BRAZIL = new Locale("pt","BR");  
        decimalFormatter = NumberFormat.getNumberInstance(BRAZIL);
        //setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        this.maxDigits = 99;
        this.casasDecimais = 2;
       
    }
    
    
    /**
     * Retorna o comprimento do DecimalField.
     *
     * @return  Um inteiro contendo o total de caracteres no campo.
     */
    public int getlength() {
        String fieldValue = getValue().toString();
        return (fieldValue.length());
    }
    
    
    /**
     * Retorna o valor do DecimalField.
     *
     * @return  Um double contendo o texto do campo.
     */
    public double getValue2() {
        if ((getValue().toString().trim().equals("")) || getValue().toString() == null){
            return 0.00;
        } else {
            String valor;
            valor = this.getValue().toString();
            valor = valor.replace(",",".");
            return Double.parseDouble(valor);
        }
    }
    
    public double getValueDinheiro() {
    	try{
        if ((getValue().toString().trim().equals("")) || getValue().toString() == null){
            return 0.00;
        } else {
            String valor;
            valor = this.getValue().toString();
            valor = valor.replace(",",".");
            valor = valor.replace("R","");
            valor = valor.replace("$","");
            valor = valor.replace(" ", "");
            valor = valor.trim();
            return Double.parseDouble(valor);
         }
    	}
        catch (Exception e) {
        	setRequiredError("erro");
        	return 0.00;
		}
    }
    /**
     * Seta o valor passado no DecimalField.
     *
     * @param value  Um inteiro contendo o valor a ser escrito no campo.
     */
    public void setValue2(double value) {
        setValue(Double.toString(value).replace('.',','));
    }
    
    public void setValueDinheiro(double valor){  
    	 setValue( DINHEIRO_REAL.format(valor));  
   
    }  
    
    /**
     * Seta o n�mero m�ximo de caracteres no DecimalField.
     *
     * @param value  um inteiro contendo a capacidade m�xima do campo.
     */
    public void setMaxDigits(int value) {
        if(value > 0) {
            maxDigits = value;
        }
    }
    
    
    /**
     * Retorna o n�mero m�ximo de caracteres no DecimalField.
     *
     * @return	Um inteiro contendo o m�ximo de caracteres suportado pelo campo.
     */
    public int getMaxDigits() {
        return maxDigits;
    }
    
    
    /**
     * Limpa o JNumberField.
     */
    public void clearField() {
        setValue("");
    }
    
    
    /**
     * Cria um novo documento padr�o para o campo.
     *
     * @return	Um documento novo que ser� aplicado no componente.
     */
    protected Document createDefaultModel() {
        return new WholeNumberDocument(this);
    }
    
    protected class WholeNumberDocument extends PlainDocument {

        DecimalField campo;
        
        public WholeNumberDocument(DecimalField campo){
            this.campo = campo;
        }
        
        /**
         * Insere o texto digitado no campo, conforme ele � digitado.
         * Caso um caracter n�o permitido seja digitado, n�o � exibido.
         *
         * @param offs	Um inteiro contendo a capacidade do campo.
         * @param str	Uma String contendo o texto a ser inserido no campo.
         * @param a	Um AttributeSet contendo os atributos.
         */
        public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
            char[] textoInicial = str.toCharArray();
            char[] textoFinal = new char[textoInicial.length];
            char[] textoCampo = campo.getValue().toString().toCharArray();
            int j = 0;
            
            for (int i = 0; i < textoFinal.length; i++) {
                //verifica se � um caractere valido.
                if ((textoInicial[i] == '-' || textoInicial[i] == ',' || Character.isDigit(textoInicial[i]))&&(offs<(maxDigits)) && (int)textoInicial[i] != 10) {
                    
                    //verifica se o caractere � virgula
                    if (textoInicial[i] == ',') {
                        if (!((existeVirgula()) || (existeVirgulaFinal(textoFinal)))){
                            if(!((posicaoAtual() == 0) && (i == 0))){
                                if (!((posicaoAtual() == 0) && (i == 1) && (textoInicial[0] == '-'))) {
                                    if(!((posicaoAtual() == 1) && (existeMenos()) && (i == 0))) {
                                        if (i > 0){
                                            if (!((textoInicial[0] == '-') && (textoInicial[1] == ',') && (i == 1))) {
                                                textoFinal[j++] = textoInicial[i];
                                            }
                                        } else {
                                            textoFinal[j++] = textoInicial[i];
                                        }
                                    }
                                } 
                            }
                        } 
                    } else if (textoInicial[i] == '-'){
                        if((posicaoAtual() == 0) && (i == 0)){
                            textoFinal[j++] = textoInicial[i];
                        }
                    } else {
                      /*  if (!((existeVirgula()) && (posicaoVirgula()+campo.getCasasDecimais()+1) == posicaoAtual())){
                            textoFinal[j++] = textoInicial[i];
                        }*/
                        
                        if ((existeVirgula()) || (existeVirgulaFinal(textoFinal))) {
                            int posicaoLimite = posicaoVirgula()+campo.getCasasDecimais()+1;
                            int posicaoLimiteString = posicaoVirgulaFinal(textoFinal)+campo.getCasasDecimais()+1;
                            int casasAposVirgula;
                            int casasDecimaisDisponiveis;
                            if (existeVirgula()) {
                                casasAposVirgula = posicaoAtual() - posicaoVirgula();
                                casasDecimaisDisponiveis = campo.getCasasDecimais() - casasAposVirgula + 1;
                                if (!((posicaoLimite == posicaoAtual()) || (i >= casasDecimaisDisponiveis))) {
                                    textoFinal[j++] = textoInicial[i];
                                }
                            } else {
                                if ((i < posicaoLimiteString)) {
                                    textoFinal[j++] = textoInicial[i];
                                }
                            }
                        } else {
                            textoFinal[j++] = textoInicial[i];
                        }
                        
                    }
                }
            }
            super.insertString(offs, new String(textoFinal, 0, j), a);
        }
        
        public boolean existeVirgula(){
            char[] textoCampo = campo.getValue().toString().toCharArray();
            for (int i = 0; i < campo.getValue().toString().length(); i++) {
                if (textoCampo[i] == ',') {
                    return true;
                }
            }
            return false;
        }
        
        public boolean existeVirgulaFinal(char[] textoFin){
            for (int i = 0; i < textoFin.length; i++) {
                if (textoFin[i] == ',') {
                    return true;
                }
            }
            return false;
        }
        
        public boolean existeMenos(){
            char[] textoCampo = campo.getValue().toString().toCharArray();
            for (int i = 0; i < campo.getValue().toString().length(); i++) {
                if (textoCampo[i] == '-') {
                    return true;
                }
            }
            return false;
        }
        
        public int posicaoAtual(){
            return campo.getValue().toString().length();
        }
        
        public int posicaoVirgula(){
            char[] textoCampo = campo.getValue().toString().toCharArray();
            
             for (int i = 0; i < campo.getValue().toString().length(); i++) {
                 if (textoCampo[i] == ',') {
                     return i;
                 }
             }
             return 0;
        }
        
        public int posicaoVirgulaFinal(char[] textoFin){
            for (int i = 0; i < textoFin.length; i++) {
                 if (textoFin[i] == ',') {
                     return i;
                 }
             }
             return 0;
        }
    }

    public int getCasasDecimais() {
        return casasDecimais;
    }
}