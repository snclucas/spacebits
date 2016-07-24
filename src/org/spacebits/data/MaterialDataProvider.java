package org.spacebits.data;

import org.spacebits.components.TypeInfo;
import org.spacebits.materials.Material;

public interface MaterialDataProvider {
	TypeInfo ALUMINUM = new TypeInfo("Aluminum");
	TypeInfo TITANIUM = new TypeInfo("Titanium");
	TypeInfo REINFORCED_TITANIUM = new TypeInfo("Reinforced titanium");
	
	Material getMaterial(TypeInfo materialType);
}
