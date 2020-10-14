package com.basen.demoapp.model;

import java.util.Map;
import java.util.Set;

public interface Entity {
    // Returns a unique identifier
    String getID();
    // Returns the sub-entities of this entity
    Set getSubEntities();
    // Returns a set of key-value data belonging to this entity
    Map getData();
}
