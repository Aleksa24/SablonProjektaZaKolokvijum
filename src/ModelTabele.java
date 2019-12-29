
import java.util.List;
import javax.swing.table.AbstractTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aleksa
 */
public class ModelTabele extends AbstractTableModel{
    
//    List<Object> lista;
//
//    public ModelTabele(List<Object> lista) {
//        this.lista = lista;
//    }

    @Override
    public int getRowCount() {
//        return lista.size();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getColumnCount() {
//        return fiksni broj;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
//        Object ob = lista.get(rowIndex);
//        switch(columnIndex){
//            case 0: return ob.get;
//            case 1: return ob.get;
//            default: return null;
//        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getColumnName(int column) {
//        switch(column){
//            case 0: return "naziv1";
//            case 1: return "naziv2";
//            default: return "greska";
//        }
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    
    
    
}
