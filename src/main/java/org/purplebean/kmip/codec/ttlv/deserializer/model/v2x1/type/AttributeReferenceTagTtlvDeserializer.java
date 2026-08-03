package org.purplebean.kmip.codec.ttlv.deserializer.model.v2x1.type;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v2x1.type.AttributeReferenceTag;

/**
 * TTLV deserializer for {@link AttributeReferenceTag}.
 */
public class AttributeReferenceTagTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<AttributeReferenceTag,
        AttributeReferenceTag.AttributeReferenceTagBuilder> {

  /**
   * Constructs a new {@link AttributeReferenceTagTtlvDeserializer}.
   */
  public AttributeReferenceTagTtlvDeserializer() {
    super(AttributeReferenceTag.kmipTag, AttributeReferenceTag.encodingType);
  }

  @Override
  protected AttributeReferenceTag.AttributeReferenceTagBuilder createBuilder() {
    return AttributeReferenceTag.builder();
  }

  @Override
  protected void setValue(AttributeReferenceTag.AttributeReferenceTagBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    int tagValue = p.getInt();
    KmipTag.Value kmipTagValue = KmipTag.fromValue(tagValue);
    builder.tagDescription(kmipTagValue.getDescription());
  }

  @Override
  protected AttributeReferenceTag build(
      AttributeReferenceTag.AttributeReferenceTagBuilder builder) {
    return builder.build();
  }
}
