package com.sauzny.crosstable.cell;

import lombok.Data;

@Data
public class HeaderCell extends Cell{

    private int colspan = 1;
    private int rowspan = 1;

    public HeaderCell(){
        
    }
    
    public boolean equals(Cell cell){
        return this.getData().equals(cell.getData());
    }
}
