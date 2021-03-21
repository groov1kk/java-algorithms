package com.github.groov1kk.structures.symboltable;

import org.junit.Test;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class BinarySymbolTableTest {

  @Test
  public void testSearchTablePut() {
    SymbolTable<String, Integer> symbolTable = new BinarySymbolTable<>();
    assertThat(symbolTable.isEmpty(), is(true));

    symbolTable.put("one", 1);
    assertThat(symbolTable.size(), is(1));
    assertThat(symbolTable.keys(), hasItem("one"));
    assertThat(symbolTable.values(), hasItem(1));
  }

  @Test
  public void testSearchTableGet() {
    SymbolTable<String, Integer> symbolTable = new BinarySymbolTable<>();
    assertThat(symbolTable.isEmpty(), is(true));

    symbolTable.put("one", 1);
    symbolTable.put("two", 2);
    assertThat(symbolTable.get("one"), is(1));
    assertThat(symbolTable.get("two"), is(2));
  }

  @Test
  public void testSearchTableDelete() {
    SymbolTable<String, Integer> symbolTable = new BinarySymbolTable<>();
    assertThat(symbolTable.isEmpty(), is(true));

    symbolTable.put("one", 1);
    symbolTable.put("two", 2);
    symbolTable.put("three", 3);
    assertThat(symbolTable.size(), is(3));

    symbolTable.remove("three");
    assertThat(symbolTable.size(), is(2));
    assertThat(symbolTable.get("three"), nullValue());
    assertThat(symbolTable.keys(), hasItems("one", "two"));
    assertThat(symbolTable.values(), hasItems(1, 2));
  }
}
