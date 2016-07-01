package org.vsg.cusp.eventbus.impl.codes;

import org.vsg.cusp.eventbus.MessageCodec;
import org.vsg.cusp.eventbus.spi.Buffer;

public class DoubleMessageCodec implements MessageCodec<Double, Double> {

	public static final byte SYSTEMCODEC_ID = 7;
	
	@Override
	public void encodeToWire(Buffer buffer, Double d) {
		buffer.appendDouble(d);
	}

	@Override
	public Double decodeFromWire(int pos, Buffer buffer) {
		return buffer.getDouble(pos);
	}

	@Override
	public Double transform(Double d) {
		// Doubles are immutable so just return it
		return d;
	}

	@Override
	public String name() {
		return "double";
	}

	@Override
	public byte systemCodecID() {
		return SYSTEMCODEC_ID;
	}

}
