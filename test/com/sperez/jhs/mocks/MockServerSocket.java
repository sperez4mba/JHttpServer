package com.sperez.jhs.mocks;

import com.sperez.jhs.ServerSocketInterface;

public class MockServerSocket implements ServerSocketInterface {
    @Override
    public MockSocket accept() {
        return new MockSocket();
    }

    public void close() {}
}