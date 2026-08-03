package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.IVCounterNonce;

public class IVCounterNonceJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<IVCounterNonce, IVCounterNonce.IVCounterNonceBuilder> {

  public IVCounterNonceJsonDeserializer() {
    super(IVCounterNonce.kmipTag, IVCounterNonce.encodingType);
  }

  @Override
  protected IVCounterNonce.IVCounterNonceBuilder createBuilder() {
    return IVCounterNonce.builder();
  }

  @Override
  protected void setValue(IVCounterNonce.IVCounterNonceBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected IVCounterNonce build(IVCounterNonce.IVCounterNonceBuilder builder) {
    return builder.build();
  }
}
