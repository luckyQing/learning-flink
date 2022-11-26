package com.collin.learning.flink.sink;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.connector.sink2.SinkWriter;

import java.io.IOException;
import java.io.Serializable;

@Slf4j
public class RedisSinkWriter implements Serializable, SinkWriter<String> {

    @Override
    public void write(String element, Context context) throws IOException, InterruptedException {
        log.warn(element);
    }

    @Override
    public void flush(boolean endOfInput) throws IOException, InterruptedException {

    }

    @Override
    public void close() throws Exception {

    }

}