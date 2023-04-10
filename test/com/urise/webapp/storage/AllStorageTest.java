package com.urise.webapp.storage;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("com.urise.webapp.storage")
@SelectClasses({
        ArrayStorageTest.class,
        SortedArrayStorageTest.class,
        ResumeMapStorageTest.class,
        MapStorageTest.class,
        ListStorageTest.class
})
public class AllStorageTest {

}
