package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.DataByteString;
import org.purplebean.kmip.model.v1x2.structure.response.payload.RngRetrieveOpResponsePayload;

/**
 * TTLV deserializer for {@link RngRetrieveOpResponsePayload}.
 */
public class RngRetrieveOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<RngRetrieveOpResponsePayload,
        RngRetrieveOpResponsePayload.RngRetrieveOpResponsePayloadBuilder> {

  /**
   * Constructs a new {@link RngRetrieveOpResponsePayloadTtlvDeserializer}.
   */
  public RngRetrieveOpResponsePayloadTtlvDeserializer() {
    super(RngRetrieveOpResponsePayload.kmipTag, RngRetrieveOpResponsePayload.encodingType);
  }

  @Override
  protected RngRetrieveOpResponsePayload.RngRetrieveOpResponsePayloadBuilder createBuilder() {
    return RngRetrieveOpResponsePayload.builder();
  }

  @Override
  protected void setValue(RngRetrieveOpResponsePayload.RngRetrieveOpResponsePayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    if (nodeTag.equals(KmipTag.Standard.DATA)) {
      builder.data(mapper.readValue(p, DataByteString.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected RngRetrieveOpResponsePayload build(
      RngRetrieveOpResponsePayload.RngRetrieveOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
