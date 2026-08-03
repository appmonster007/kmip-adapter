package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.IVCounterNonce;

public class IVCounterNonceXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<IVCounterNonce, IVCounterNonce.IVCounterNonceBuilder> {

  public IVCounterNonceXmlDeserializer() {
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