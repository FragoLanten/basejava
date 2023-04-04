package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ArrayStorageTest extends AbstractArrayStorageTest {

    public ArrayStorageTest() {
        super(new ArrayStorage());
    }

}