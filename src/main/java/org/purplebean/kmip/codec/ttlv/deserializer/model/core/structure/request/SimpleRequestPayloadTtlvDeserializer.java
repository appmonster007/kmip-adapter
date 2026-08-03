package org.purplebean.kmip.codec.ttlv.deserializer.model.core.structure.request;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.request.SimpleRequestPayload;

/**
 * TTLV deserializer for {@link SimpleRequestPayload}.
 */
public class SimpleRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SimpleRequestPayload,
        SimpleRequestPayload.SimpleRequestPayloadBuilder> {

  /**
   * Constructs a new {@link SimpleRequestPayloadTtlvDeserializer}.
   */
  public SimpleRequestPayloadTtlvDeserializer() {
    super(SimpleRequestPayload.kmipTag, SimpleRequestPayload.encodingType);
  }

  @Override
  protected SimpleRequestPayload.SimpleRequestPayloadBuilder createBuilder() {
    return SimpleRequestPayload.builder();
  }

  @Override
  protected void setValue(SimpleRequestPayload.SimpleRequestPayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    // No fields to set
  }

  @Override
  protected SimpleRequestPayload build(SimpleRequestPayload.SimpleRequestPayloadBuilder builder) {
    return builder.build();
  }

  //    @Override
  //    public SimpleRequestPayload deserialize(ByteBuffer ttlvBuffer, TtlvMapper mapper) throws
  //    IOException {
  //        TtlvObject obj = TtlvObject.fromBuffer(ttlvBuffer);
  //        if (Arrays.equals(obj.getTag(), SimpleRequestPayload.kmipTag.getTagBytes()) && obj
  //        .getType() != getEncodingType().getTypeValue()) {
  //            throw new IllegalArgumentException(String.format("Expected %s type for %s, got %s",
  //            getEncodingType().getTypeValue(), SimpleRequestPayload.kmipTag.getDescription(),
  //            obj.getType()));
  //        }
  //
  //        SimpleRequestPayload simpleRequestPayload = SimpleRequestPayload.builder().build();
  //
  //        if (!simpleRequestPayload.isSupported()) {
  //            throw new NoSuchElementException();
  //        }
  //        return simpleRequestPayload;
  //    }
}
