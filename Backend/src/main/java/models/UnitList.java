package models;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UnitList {

    private Map<String, List<String>> unitList;
}
