package org.tigris.gefdemo.lan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PSTable extends JPanel{

        public static JTextField nameText = new JTextField( 15 );
        public static JTextField typeText = new JTextField( 15 );
        public static JTextField statusText = new JTextField( 15 );
        public static JTextField urlText = new JTextField( 15 );
        private JButton doneButton = new JButton( "Save Properties" );
        public static NodeLAN sn;

        public PSTable(){


           // setTitle( " " );
            //setSize( 300, 200 );
            JLabel nameLabel = new JLabel( "Node Name:" );
            nameText.setMaximumSize( nameText.getPreferredSize() );

            Box hBox1 = Box.createHorizontalBox();

            hBox1.add( nameLabel );
            hBox1.add( nameText );

            JLabel typeLabel = new JLabel( "Node Type:" );
            typeText.setMaximumSize( typeText.getPreferredSize() );

            Box hBox2 = Box.createHorizontalBox();
            hBox2.add( typeLabel );
            hBox2.add( typeText );

            JLabel statusLabel = new JLabel( "Node Status:" );
            statusText.setMaximumSize( statusText.getPreferredSize() );

            Box hBox3 = Box.createHorizontalBox();
            hBox3.add( statusLabel );
            hBox3.add( statusText );

            JLabel urlLabel = new JLabel( "Node configuration URL:" );
            urlText.setMaximumSize( urlText.getPreferredSize() );

            Box hBox4 = Box.createHorizontalBox();
            hBox4.add( urlLabel );
            hBox4.add( urlText );

            //JLabel desc = new JLabel( "If changes are made, click ''DONE'' to save" );

            Box hBox5 = Box.createHorizontalBox();
            hBox5.add( doneButton );
            //hBox5.add( desc );

            ButtonListener buttonHandler = new ButtonListener();
            doneButton.addActionListener( buttonHandler );

            Box vBox = Box.createVerticalBox();
            vBox.add( Box.createVerticalStrut( 10 ) );
            vBox.add( hBox1 );
            vBox.add( Box.createVerticalStrut( 10 ) );
            vBox.add( hBox2 );
            vBox.add( Box.createVerticalStrut( 10 ) );
            vBox.add( hBox3 );
            vBox.add( Box.createVerticalStrut( 10 ) );
            vBox.add( hBox4 );
            vBox.add( Box.createVerticalStrut( 5 ) );
            vBox.add( hBox5 );

            this.add(vBox);
        }

        private class ButtonListener implements ActionListener{

            public void actionPerformed( ActionEvent e ){

                if( e.getSource() == doneButton ){

                    //Save text fields in _myObject
                    setNewProperties();
                }
            }
        }

    public static void setTargetNode( NodeLAN s ){
        sn = s;
        nameText.setText( sn.getName() );
        typeText.setText( sn.getType() );
        statusText.setText( sn.getStatus() );
        urlText.setText( sn.getURL() );
    }

    public static void clearFields(){

        nameText.setText( "" );
        typeText.setText( "" );
        statusText.setText( "" );
        urlText.setText( "" );
    }


    private void setNewProperties(){

        sn.setName( nameText.getText().trim() );
        sn.setType( typeText.getText().trim() );
        sn.setStatus( statusText.getText().trim() );
        sn.setURL( urlText.getText().trim() );
    }

}

