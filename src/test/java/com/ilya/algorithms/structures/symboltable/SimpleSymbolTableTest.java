package com.ilya.algorithms.structures.symboltable;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class SimpleSymbolTableTest {

  @Test
  public void testSearchTablePut() {
    SymbolTable<String, Integer> symbolTable = new SimpleSymbolTable<>();
    Assert.assertThat(symbolTable.isEmpty(), is(true));

    symbolTable.put("one", 1);
    Assert.assertThat(symbolTable.size(), is(1));
    Assert.assertThat(symbolTable.keys(), hasItem("one"));
    Assert.assertThat(symbolTable.values(), hasItem(1));
  }

  @Test
  public void testSearchTableGet() {
    SymbolTable<String, Integer> symbolTable = new SimpleSymbolTable<>();
    Assert.assertThat(symbolTable.isEmpty(), is(true));

    symbolTable.put("one", 1);
    symbolTable.put("two", 2);
    Assert.assertThat(symbolTable.get("one"), is(1));
    Assert.assertThat(symbolTable.get("two"), is(2));
  }

  @Test
  public void testSearchTableDelete() {
    SymbolTable<String, Integer> symbolTable = new SimpleSymbolTable<>();
    Assert.assertThat(symbolTable.isEmpty(), is(true));

    symbolTable.put("one", 1);
    symbolTable.put("two", 2);
    symbolTable.put("three", 3);
    Assert.assertThat(symbolTable.size(), is(3));

    symbolTable.remove("two");
    Assert.assertThat(symbolTable.size(), is(2));
    Assert.assertThat(symbolTable.get("two"), nullValue());
    Assert.assertThat(symbolTable.keys(), hasItems("one", "three"));
    Assert.assertThat(symbolTable.values(), hasItems(1, 3));
  }
}
