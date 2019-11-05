package com.github.groov1kk.sort;

import org.junit.Assert;
import org.junit.Test;

public class SelectionSortTest extends BaseSortTest {

    @Test
    public void testSelectionSort() {
        Sort sort = new SelectionSort();
        Assert.assertThat(sort.sort(array), isSortedAsc());
    }
}
