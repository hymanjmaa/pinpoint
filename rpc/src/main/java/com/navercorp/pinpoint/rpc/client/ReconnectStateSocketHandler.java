package com.navercorp.pinpoint.rpc.client;

import com.navercorp.pinpoint.rpc.DefaultFuture;
import com.navercorp.pinpoint.rpc.Future;
import com.navercorp.pinpoint.rpc.PinpointSocketException;
import com.navercorp.pinpoint.rpc.ResponseMessage;
import com.navercorp.pinpoint.rpc.stream.ClientStreamChannelContext;
import com.navercorp.pinpoint.rpc.stream.ClientStreamChannelMessageListener;
import com.navercorp.pinpoint.rpc.stream.StreamChannelContext;

import java.net.SocketAddress;

/**
 * @author emeroad
 * @author netspider
 */
public class ReconnectStateSocketHandler implements SocketHandler {


    @Override
    public void setConnectSocketAddress(SocketAddress connectSocketAddress) {
    }

    @Override
    public void open() {
        throw new IllegalStateException();
    }

    @Override
    public void initReconnect() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void setPinpointSocket(PinpointSocket pinpointSocket) {
    }

    @Override
    public void sendSync(byte[] bytes) {
        throw newReconnectException();
    }

    @Override
    public Future sendAsync(byte[] bytes) {
        return reconnectFailureFuture();
    }

    private DefaultFuture<ResponseMessage> reconnectFailureFuture() {
        DefaultFuture<ResponseMessage> reconnect = new DefaultFuture<ResponseMessage>();
        reconnect.setFailure(newReconnectException());
        return reconnect;
    }

    @Override
    public void close() {
    }

    @Override
    public void send(byte[] bytes) {
    }

    private PinpointSocketException newReconnectException() {
        return new PinpointSocketException("reconnecting...");
    }

    @Override
    public Future<ResponseMessage> request(byte[] bytes) {
        return reconnectFailureFuture();
    }

    @Override
    public ClientStreamChannelContext createStreamChannel(byte[] payload, ClientStreamChannelMessageListener clientStreamChannelMessageListener) {
    	throw new UnsupportedOperationException();
    }
    
    @Override
    public StreamChannelContext findStreamChannel(int streamChannelId) {
    	throw new UnsupportedOperationException();
    }
    
    @Override
    public void sendPing() {
    }

	@Override
	public boolean isConnected() {
		return false;
	}
	
	@Override
	public boolean isSupportServerMode() {
		return false;
	}
	
	@Override
	public void doHandshake() {
//        throw new UnsupportedOperationException();
	}
	
}
