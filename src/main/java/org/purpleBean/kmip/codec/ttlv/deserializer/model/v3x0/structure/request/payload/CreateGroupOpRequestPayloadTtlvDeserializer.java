package org.purpleBean.kmip.codec.ttlv.deserializer.model.v3x0.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.v2x1.structure.Attributes;
import org.purpleBean.kmip.model.v3x0.structure.request.payload.CreateGroupOpRequestPayload;

public class CreateGroupOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CreateGroupOpRequestPayload,
        CreateGroupOpRequestPayload.CreateGroupOpRequestPayloadBuilder> {

  public CreateGroupOpRequestPayloadTtlvDeserializer() {
    super(CreateGroupOpRequestPayload.kmipTag, CreateGroupOpRequestPayload.encodingType);
  }

  @Override
  protected CreateGroupOpRequestPayload.CreateGroupOpRequestPayloadBuilder createBuilder() {
    return CreateGroupOpRequestPayload.builder();
  }

  @Override
  protected void setValue(CreateGroupOpRequestPayload.CreateGroupOpRequestPayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.ATTRIBUTES -> builder.attributes(mapper.readValue(p, Attributes.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CreateGroupOpRequestPayload build(
      CreateGroupOpRequestPayload.CreateGroupOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}