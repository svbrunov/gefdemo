package org.tigris.gefdemo.lan;

import java.util.*;
import org.tigris.gef.event.*;
import org.tigris.gef.presentation.*;


public class PropSheetListener implements GraphSelectionListener {

  public void selectionChanged(GraphSelectionEvent gse){
      Vector v = gse.getSelections();

      if( v.size() == 1 ){

          try{
              FigNode f = ( FigNode) v.get(0);
              if( f.getOwner() instanceof NodeLAN ){
                NodeLAN o = ( NodeLAN ) f.getOwner();
                PSTable.setTargetNode( o );
              }
              else{}
          }
          catch( Exception e ){   }
      }
  }
}


