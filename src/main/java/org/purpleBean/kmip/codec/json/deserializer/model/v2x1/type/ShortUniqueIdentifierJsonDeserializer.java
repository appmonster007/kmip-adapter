package org.purpleBean.kmip.codec.json.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.v2x1.type.ShortUniqueIdentifier;

public class ShortUniqueIdentifierJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<ShortUniqueIdentifier,
        ShortUniqueIdentifier.ShortUniqueIdentifierBuilder> {

  public ShortUniqueIdentifierJsonDeserializer() {
    super(ShortUniqueIdentifier.kmipTag, ShortUniqueIdentifier.encodingType);
  }

  @Override
  protected ShortUniqueIdentifier.ShortUniqueIdentifierBuilder createBuilder() {
    return ShortUniqueIdentifier.builder();
  }

  @Override
  protected void setValue(ShortUniqueIdentifier.ShortUniqueIdentifierBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected ShortUniqueIdentifier build(
      ShortUniqueIdentifier.ShortUniqueIdentifierBuilder builder) {
    return builder.build();
  }
}