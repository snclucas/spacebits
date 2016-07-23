package org.braincycles.spacebits.components;

public class TypeInfo { 
    public final int typeId;
    public final String typeIdString;

    public TypeInfo(String typeId) {
    	this.typeIdString = typeId;
        this.typeId = typeId.hashCode();
    }
    
    
    

	@Override
	public String toString() {
		return typeIdString;
	}




	@Override
	public int hashCode() {
		//Safe to use string hashcodes
		return typeIdString.hashCode();
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TypeInfo other = (TypeInfo) obj;
		if (typeId != other.typeId)
			return false;
		if (typeIdString == null) {
			if (other.typeIdString != null)
				return false;
		} else if (!typeIdString.equals(other.typeIdString))
			return false;
		return true;
	}
    
    
}