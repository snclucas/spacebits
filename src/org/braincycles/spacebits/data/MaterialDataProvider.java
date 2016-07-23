package org.braincycles.spacebits.data;

import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.materials.Material;

public interface MaterialDataProvider {
	TypeInfo ALUMINUM = new TypeInfo("Aluminum");
	TypeInfo TITANIUM = new TypeInfo("Titanium");
	TypeInfo REINFORCED_TITANIUM = new TypeInfo("Reinforced titanium");
	
	Material getMaterial(TypeInfo materialType);
}
