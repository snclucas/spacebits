package org.spacebits.components.computers;

import java.security.InvalidParameterException;

import org.spacebits.Configuration;
import org.spacebits.data.SpacecraftDataProvider;

public class DataStoreFactory {

	public final static String BASIC_DATASTORE="Basic datastore"; 

	public static DataStore getDataStore(String dataStoreType) throws InvalidParameterException{
		SpacecraftDataProvider spacecraftDataProvider = Configuration.getSpacecraftDataProvider();

		switch (dataStoreType) {

		case BASIC_DATASTORE:

			DataStore dataStore = new BasicDataStorageUnit("Data store", 
					spacecraftDataProvider.getComponentParameters(BasicDataStorageUnit.typeID.toString()).getBusComponentSpecification());


			return dataStore;
		default:
			throw new InvalidParameterException("No such data store.");

		}


	}

}
