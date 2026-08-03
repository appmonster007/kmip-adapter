package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.DataLength;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.RngRetrieveOpRequestPayload;

public class RngRetrieveOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<RngRetrieveOpRequestPayload,
        RngRetrieveOpRequestPayload.RngRetrieveOpRequestPayloadBuilder> {

  public RngRetrieveOpRequestPayloadTtlvDeserializer() {
    super(RngRetrieveOpRequestPayload.kmipTag, RngRetrieveOpRequestPayload.encodingType);
  }

  @Override
  protected RngRetrieveOpRequestPayload.RngRetrieveOpRequestPayloadBuilder createBuilder() {
    return RngRetrieveOpRequestPayload.builder();
  }

  @Override
  protected void setValue(RngRetrieveOpRequestPayload.RngRetrieveOpRequestPayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    if (nodeTag.equals(KmipTag.Standard.DATA_LENGTH)) {
      builder.dataLength(mapper.readValue(p, DataLength.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected RngRetrieveOpRequestPayload build(
      RngRetrieveOpRequestPayload.RngRetrieveOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
