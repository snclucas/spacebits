package org.spacebits.data;

import org.spacebits.components.TypeInfo;

public interface SpacecraftComponentDataProvider {
	SpacecraftComponentData getComponentParameters(TypeInfo componentTag);
}
