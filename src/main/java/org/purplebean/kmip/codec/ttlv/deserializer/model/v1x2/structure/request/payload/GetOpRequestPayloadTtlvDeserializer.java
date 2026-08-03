package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.KeyCompressionType;
import org.purplebean.kmip.model.core.enumeration.KeyFormatType;
import org.purplebean.kmip.model.core.structure.KeyWrappingSpecification;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.GetOpRequestPayload;

/**
 * TTLV deserializer for {@link GetOpRequestPayload}.
 */
public class GetOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<GetOpRequestPayload,
        GetOpRequestPayload.GetOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link GetOpRequestPayloadTtlvDeserializer}.
   */
  public GetOpRequestPayloadTtlvDeserializer() {
    super(GetOpRequestPayload.kmipTag, GetOpRequestPayload.encodingType);
  }

  @Override
  protected GetOpRequestPayload.GetOpRequestPayloadBuilder createBuilder() {
    return GetOpRequestPayload.builder();
  }

  @Override
  protected void setValue(GetOpRequestPayload.GetOpRequestPayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.KEY_FORMAT_TYPE ->
          builder.keyFormatType(mapper.readValue(p, KeyFormatType.class));
      case KmipTag.Standard.KEY_WRAP_TYPE -> builder.keyWrapType(
          mapper.readValue(p, org.purplebean.kmip.model.core.enumeration.KeyWrapType.class));
      case KmipTag.Standard.KEY_COMPRESSION_TYPE ->
          builder.keyCompressionType(mapper.readValue(p, KeyCompressionType.class));
      case KmipTag.Standard.KEY_WRAPPING_SPECIFICATION ->
          builder.keyWrappingSpecification(mapper.readValue(p, KeyWrappingSpecification.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected GetOpRequestPayload build(GetOpRequestPayload.GetOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
