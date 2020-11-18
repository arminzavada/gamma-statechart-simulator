package com.triad.school.gamma.simulator.query;

import java.util.Map;
import org.eclipse.xtext.xbase.lib.Extension;

@SuppressWarnings("all")
public class DummyEntry<T extends Object> implements Map.Entry<String, T> {
  @Extension
  private final String key;
  
  @Extension
  private T value;
  
  public DummyEntry(final String key, final T value) {
    this.key = key;
    this.value = value;
  }
  
  @Override
  public String getKey() {
    return this.key;
  }
  
  @Override
  public T getValue() {
    return this.value;
  }
  
  @Override
  public T setValue(final T arg0) {
    return this.value = arg0;
  }
}
