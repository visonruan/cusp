package org.vsg.cusp.eventbus.impl.codes;

import org.vsg.cusp.eventbus.MessageCodec;
import org.vsg.cusp.eventbus.spi.Buffer;

public class IntMessageCodec implements MessageCodec<Integer, Integer> {

	public static final byte SYSTEMCODEC_ID = 4;	
	
	@Override
	public void encodeToWire(Buffer buffer, Integer i) {
		buffer.appendInt(i);
	}

	@Override
	public Integer decodeFromWire(int pos, Buffer buffer) {
		return buffer.getInt(pos);
	}

	@Override
	public Integer transform(Integer i) {
		// Integers are immutable so just return it
		return i;
	}

	@Override
	public String name() {
		return "int";
	}

	@Override
	public byte systemCodecID() {
		return SYSTEMCODEC_ID;
	}

}
