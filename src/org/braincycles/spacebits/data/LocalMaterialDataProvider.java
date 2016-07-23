package org.braincycles.spacebits.data;

import org.braincycles.spacebits.components.TypeInfo;
import org.braincycles.spacebits.materials.CompositeMaterial;
import org.braincycles.spacebits.materials.Element;
import org.braincycles.spacebits.materials.Material;

public class LocalMaterialDataProvider implements MaterialDataProvider {

	public Material getMaterial(TypeInfo materialType) {

		if(materialType == ALUMINUM) 
			return new CompositeMaterial(
					new Element[]{new Element(26.9815385, 13, 2700)}, 
					new double[]{1.0},
					1e6, 0.01, 0.3, 0.2);
		else if(materialType == TITANIUM)  
			return new CompositeMaterial(
					new Element[]{new Element(47.867, 22, 4506)}, 
					new double[]{1.0},
					1e9, 0.01, 0.3, 0.2);
		else if(materialType == REINFORCED_TITANIUM) 
			return new CompositeMaterial(
					new Element[]{new Element(47.867, 22, 4506), new Element(26.9815385, 13, 2700)}, 
					new double[]{0.75, 0.25},
					4e9, 0.1, 0.3, 0.2);
		else
			return getMaterial(ALUMINUM);
	}
}
