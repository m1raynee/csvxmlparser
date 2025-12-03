package com.m1raynee.csvxmlparser.parsing;

import com.m1raynee.csvxmlparser.model.AddressEntry;

import java.nio.file.Path;
import java.util.List;

public interface FileParser {
    List<AddressEntry> parse(Path path) throws Exception;
}
