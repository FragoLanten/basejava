package com.urise.webapp.storage;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        FullNameMapStorageTest.class,
        MapStorageTest.class,
        ListStorage.class
})
public class AllStorageTest {

}
