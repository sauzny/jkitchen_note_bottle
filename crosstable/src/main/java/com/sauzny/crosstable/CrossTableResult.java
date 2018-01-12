package com.sauzny.crosstable;

import java.util.List;

import com.google.common.collect.Lists;
import com.sauzny.crosstable.cell.Cell;
import com.sauzny.crosstable.cell.HeaderCell;
import com.sauzny.crosstable.cell.LeftCellDesc;

import lombok.Data;

@Data
public class CrossTableResult {

    private List<List<HeaderCell>> colHearders = Lists.newArrayList();

    private List<List<Cell>> data = Lists.newArrayList();
    
    private List<LeftCellDesc> mergeCells = Lists.newArrayList();
    
}
