package org.spacebits.navigation;

import org.spacebits.Configuration;
import org.spacebits.components.Identifiable;
import org.spacebits.components.TypeInfo;
import org.spacebits.universe.celestialobjects.CelestialObject;

import java.math.BigDecimal;

public class NavigationEntry implements Identifiable{

  private final CelestialObject celestialObject;
  private final BigDecimal distanceToCelestial;
  private final String ident =  Configuration.getUUID();

  public NavigationEntry(CelestialObject celestialObject, BigDecimal distanceToCelestial) {
    this.celestialObject = celestialObject;
    this.distanceToCelestial = distanceToCelestial;
  }


  public NavigationEntry(CelestialObject celestialObject, double distanceToCelestial) {
    this.celestialObject = celestialObject;
    this.distanceToCelestial = new BigDecimal(distanceToCelestial);
  }

  public CelestialObject getCelestialObject() {
    return celestialObject;
  }

  public BigDecimal getDistanceToCelestial() {
    return distanceToCelestial;
  }

  @Override
  public TypeInfo getType() {
    return new TypeInfo("NavigationEntry");
  }

  @Override
  public TypeInfo getCategory() {
    return new TypeInfo("NavigationEntry");
  }

  @Override
  public String getName() {
    return "NavigationEntry";
  }

  @Override
  public String getIdent() {
    return ident;
  }

  @Override
  public String describe() {
    return "NavigationEntry";
  }
}
