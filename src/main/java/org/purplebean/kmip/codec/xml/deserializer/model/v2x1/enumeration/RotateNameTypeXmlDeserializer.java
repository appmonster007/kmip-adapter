package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.enumeration.RotateNameType;

/**
 * XML deserializer for {@link RotateNameType}.
 */
public class RotateNameTypeXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<RotateNameType, RotateNameType.RotateNameTypeBuilder> {

  /**
   * Constructs a new {@link RotateNameTypeXmlDeserializer}.
   */
  public RotateNameTypeXmlDeserializer() {
    super(RotateNameType.kmipTag, RotateNameType.encodingType);
  }

  @Override
  protected RotateNameType.RotateNameTypeBuilder createBuilder() {
    return RotateNameType.builder();
  }

  @Override
  protected void setValue(RotateNameType.RotateNameTypeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(RotateNameType.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected RotateNameType build(RotateNameType.RotateNameTypeBuilder builder) {
    return builder.build();
  }
}