package org.purplebean.kmip.codec.json.deserializer.model.core.type;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.type.SubjectDistinguishedName;

/**
 * JSON deserializer for {@link SubjectDistinguishedName}.
 */
public class SubjectDistinguishedNameJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<SubjectDistinguishedName,
        SubjectDistinguishedName.SubjectDistinguishedNameBuilder> {

  /**
   * Constructs a new {@link SubjectDistinguishedNameJsonDeserializer}.
   */
  public SubjectDistinguishedNameJsonDeserializer() {
    super(SubjectDistinguishedName.kmipTag, SubjectDistinguishedName.encodingType);
  }

  @Override
  protected SubjectDistinguishedName.SubjectDistinguishedNameBuilder createBuilder() {
    return SubjectDistinguishedName.builder();
  }

  @Override
  protected void setValue(SubjectDistinguishedName.SubjectDistinguishedNameBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    builder.value(ctxt.readValue(p, ByteBuffer.class));
  }

  @Override
  protected SubjectDistinguishedName build(
      SubjectDistinguishedName.SubjectDistinguishedNameBuilder builder) {
    return builder.build();
  }
}
