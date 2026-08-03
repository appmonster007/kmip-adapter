package org.purplebean.kmip.codec.json.deserializer.model.v2x1.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipAttribute;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.v2x1.structure.PrivateKeyAttributes;

/**
 * JSON deserializer for {@link PrivateKeyAttributes}.
 */
public class PrivateKeyAttributesJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<PrivateKeyAttributes,
        PrivateKeyAttributes.PrivateKeyAttributesBuilder> {

  /**
   * Constructs a new {@link PrivateKeyAttributesJsonDeserializer}.
   */
  public PrivateKeyAttributesJsonDeserializer() {
    super(PrivateKeyAttributes.kmipTag, PrivateKeyAttributes.encodingType);
  }

  @Override
  protected PrivateKeyAttributes.PrivateKeyAttributesBuilder createBuilder() {
    return PrivateKeyAttributes.builder();
  }

  @Override
  protected void setValue(PrivateKeyAttributes.PrivateKeyAttributesBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.attribute(ctxt.readValue(p, KmipAttribute.class));
  }

  @Override
  protected PrivateKeyAttributes build(PrivateKeyAttributes.PrivateKeyAttributesBuilder builder) {
    return builder.build();
  }
}