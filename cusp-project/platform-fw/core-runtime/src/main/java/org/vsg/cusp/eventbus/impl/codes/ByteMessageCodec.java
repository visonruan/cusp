package org.vsg.cusp.eventbus.impl.codes;

import org.vsg.cusp.eventbus.MessageCodec;
import org.vsg.cusp.eventbus.spi.Buffer;

public class ByteMessageCodec implements MessageCodec<Byte, Byte> {

	  @Override
	  public void encodeToWire(Buffer buffer, Byte b) {
	    buffer.appendByte(b);
	  }

	  @Override
	  public Byte decodeFromWire(int pos, Buffer buffer) {
	    return buffer.getByte(pos);
	  }

	  @Override
	  public Byte transform(Byte b) {
	    // Bytes are immutable so just return it
	    return b;
	  }

	  @Override
	  public String name() {
	    return "byte";
	  }

	  @Override
	  public byte systemCodecID() {
	    return 1;
	  }

}
