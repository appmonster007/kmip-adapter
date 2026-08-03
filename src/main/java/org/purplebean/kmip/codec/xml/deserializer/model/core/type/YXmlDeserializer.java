package org.purplebean.kmip.codec.xml.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.math.BigInteger;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.type.Y;

/**
 * XML deserializer for {@link Y}.
 */
public class YXmlDeserializer extends AbstractKmipDataTypeXmlDeserializer<Y, Y.YBuilder> {

  /**
   * Constructs a new {@link YXmlDeserializer}.
   */
  public YXmlDeserializer() {
    super(Y.kmipTag, Y.encodingType);
  }

  @Override
  protected Y.YBuilder createBuilder() {
    return Y.builder();
  }

  @Override
  protected void setValue(Y.YBuilder builder, String tag, String type, JsonParser p,
                          DeserializationContext ctxt) throws IOException {
    builder.value(ctxt.readValue(p, BigInteger.class));
  }

  @Override
  protected Y build(Y.YBuilder builder) {
    return builder.build();
  }
}