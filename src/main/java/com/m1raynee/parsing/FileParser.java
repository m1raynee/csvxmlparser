package com.m1raynee.parsing;

import com.m1raynee.model.AddressEntry;

import java.nio.file.Path;
import java.util.List;


public interface FileParser {
    List<AddressEntry> parse(Path path) throws Exception;
}
