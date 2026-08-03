package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.type.AsynchronousCorrelationValue;
import org.purplebean.kmip.model.v1x2.structure.request.payload.CancelOpRequestPayload;

/**
 * TTLV deserializer for {@link CancelOpRequestPayload}.
 */
public class CancelOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CancelOpRequestPayload,
        CancelOpRequestPayload.CancelOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link CancelOpRequestPayloadTtlvDeserializer}.
   */
  public CancelOpRequestPayloadTtlvDeserializer() {
    super(CancelOpRequestPayload.kmipTag, CancelOpRequestPayload.encodingType);
  }

  @Override
  protected CancelOpRequestPayload.CancelOpRequestPayloadBuilder createBuilder() {
    return CancelOpRequestPayload.builder();
  }

  @Override
  protected void setValue(CancelOpRequestPayload.CancelOpRequestPayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    if (nodeTag.equals(KmipTag.Standard.ASYNCHRONOUS_CORRELATION_VALUE)) {
      builder.asynchronousCorrelationValue(mapper.readValue(p, AsynchronousCorrelationValue.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CancelOpRequestPayload build(
      CancelOpRequestPayload.CancelOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
