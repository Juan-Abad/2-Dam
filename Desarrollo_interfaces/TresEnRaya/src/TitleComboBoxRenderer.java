import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class TitleComboBoxRenderer extends DefaultListCellRenderer {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;

    public TitleComboBoxRenderer(String title) {
        this.title = title;
    }

    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        if (index == -1) {
            // Muestra el título visual sin ser seleccionable
            value = title;
        }
        return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
    }
}