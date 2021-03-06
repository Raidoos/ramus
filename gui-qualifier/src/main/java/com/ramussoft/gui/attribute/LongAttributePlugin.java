package com.ramussoft.gui.attribute;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

import com.ramussoft.common.AccessRules;
import com.ramussoft.common.Attribute;
import com.ramussoft.common.AttributeType;
import com.ramussoft.common.Engine;
import com.ramussoft.gui.common.AbstractAttributePlugin;

public class LongAttributePlugin extends AbstractAttributePlugin {

    private DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
        /**
         *
         */
        private static final long serialVersionUID = -7922052040779840252L;

        {
            setHorizontalAlignment(SwingConstants.RIGHT);
        }
    };

    private class LongCellEditor extends DefaultCellEditor {

        /**
         *
         */
        private static final long serialVersionUID = 1864288476820280002L;

        private Object value;

        public LongCellEditor() {
            super(new JTextField());
            getComponent().setName("Table.editor");
            ((JTextField) getComponent())
                    .setHorizontalAlignment(SwingConstants.RIGHT);
        }

        @Override
        public boolean stopCellEditing() {
            final String s = (String) super.getCellEditorValue();
            if ("".equals(s)) {
                return super.stopCellEditing();
            }

            try {
                value = new Long(s);
            } catch (final Exception e) {
                ((JComponent) getComponent()).setBorder(new LineBorder(
                        Color.red));
                return false;
            }
            return super.stopCellEditing();
        }

        @Override
        public Component getTableCellEditorComponent(final JTable table,
                                                     Object value, final boolean isSelected, final int row,
                                                     final int column) {
            this.value = null;
            ((JComponent) getComponent())
                    .setBorder(new LineBorder(Color.black));
            if (value != null)
                value = String.valueOf(value);
            return super.getTableCellEditorComponent(table, value, isSelected,
                    row, column);
        }

        @Override
        public Object getCellEditorValue() {
            return value;
        }
    }

    @Override
    public AttributeType getAttributeType() {
        return new AttributeType("Core", "Long", true);
    }

    @Override
    public TableCellEditor getTableCellEditor(Engine engine, AccessRules rules,
                                              Attribute attribute) {
        return new LongCellEditor();
    }

    @Override
    public String getName() {
        return "Core";
    }

    @Override
    public TableCellRenderer getTableCellRenderer(Engine engine,
                                                  AccessRules rules, Attribute attribute) {
        return cellRenderer;
    }
}
