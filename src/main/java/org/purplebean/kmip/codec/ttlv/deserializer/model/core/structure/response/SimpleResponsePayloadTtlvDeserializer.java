package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure.response;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.response.SimpleResponsePayload;

/**
 * TTLV deserializer for {@link SimpleResponsePayload}.
 */
public class SimpleResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SimpleResponsePayload,
        SimpleResponsePayload.SimpleResponsePayloadBuilder> {

  /**
   * Constructs a new {@link SimpleResponsePayloadTtlvDeserializer}.
   */
  public SimpleResponsePayloadTtlvDeserializer() {
    super(SimpleResponsePayload.kmipTag, SimpleResponsePayload.encodingType);
  }

  @Override
  protected SimpleResponsePayload.SimpleResponsePayloadBuilder createBuilder() {
    return SimpleResponsePayload.builder();
  }

  @Override
  protected void setValue(SimpleResponsePayload.SimpleResponsePayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    // No fields to set
  }

  @Override
  protected SimpleResponsePayload build(
      SimpleResponsePayload.SimpleResponsePayloadBuilder builder) {
    return builder.build();
  }
}
