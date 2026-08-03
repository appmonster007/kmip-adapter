package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.AttributeName;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.GetAttributeListOpResponsePayload;

/**
 * TTLV deserializer for {@link GetAttributeListOpResponsePayload}.
 */
public class GetAttributeListOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<GetAttributeListOpResponsePayload,
        GetAttributeListOpResponsePayload.GetAttributeListOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link GetAttributeListOpResponsePayloadTtlvDeserializer}.
   */
  public GetAttributeListOpResponsePayloadTtlvDeserializer() {
    super(GetAttributeListOpResponsePayload.kmipTag,
        GetAttributeListOpResponsePayload.encodingType);
  }

  @Override
  protected GetAttributeListOpResponsePayload.GetAttributeListOpResponsePayloadBuilder
      createBuilder() {
    return GetAttributeListOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      GetAttributeListOpResponsePayload.GetAttributeListOpResponsePayloadBuilder builder,
      byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.ATTRIBUTE_NAME ->
          builder.attributeName(mapper.readValue(p, AttributeName.class));
      case KmipTag.Standard.ATTRIBUTE_REFERENCE -> builder.attributeReference(
          mapper.readValue(p, org.purplebean.kmip.api.KmipDataType.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected GetAttributeListOpResponsePayload build(
      GetAttributeListOpResponsePayload.GetAttributeListOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
