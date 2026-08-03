package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.type.ShortUniqueIdentifier;

public class ShortUniqueIdentifierXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ShortUniqueIdentifier,
        ShortUniqueIdentifier.ShortUniqueIdentifierBuilder> {

  public ShortUniqueIdentifierXmlDeserializer() {
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