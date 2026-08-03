package org.purplebean.kmip.codec.xml.deserializer.model.v2x1.enumeration;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v2x1.enumeration.NistKeyType;

/**
 * XML deserializer for {@link NistKeyType}.
 */
public class NistKeyTypeXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<NistKeyType, NistKeyType.NistKeyTypeBuilder> {

  /**
   * Constructs a new {@link NistKeyTypeXmlDeserializer}.
   */
  public NistKeyTypeXmlDeserializer() {
    super(NistKeyType.kmipTag, NistKeyType.encodingType);
  }

  @Override
  protected NistKeyType.NistKeyTypeBuilder createBuilder() {
    return NistKeyType.builder();
  }

  @Override
  protected void setValue(NistKeyType.NistKeyTypeBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    builder.value(NistKeyType.fromName(ctxt.readValue(p, String.class)));
  }

  @Override
  protected NistKeyType build(NistKeyType.NistKeyTypeBuilder builder) {
    return builder.build();
  }
}