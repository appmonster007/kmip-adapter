package org.purpleBean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.type.NonceValue;

public class NonceValueXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<NonceValue, NonceValue.NonceValueBuilder> {

  public NonceValueXmlDeserializer() {
    super(NonceValue.kmipTag, NonceValue.encodingType);
  }

  @Override
  protected NonceValue.NonceValueBuilder createBuilder() {
    return NonceValue.builder();
  }

  @Override
  protected void setValue(NonceValue.NonceValueBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected NonceValue build(NonceValue.NonceValueBuilder builder) {
    return builder.build();
  }
}