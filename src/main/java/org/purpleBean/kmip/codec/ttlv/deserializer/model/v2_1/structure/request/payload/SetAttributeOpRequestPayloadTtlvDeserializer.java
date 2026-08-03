package org.purpleBean.kmip.codec.ttlv.deserializer.model.v2_1.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v2_1.structure.NewAttribute;
import org.purpleBean.kmip.model.v2_1.structure.request.payload.SetAttributeOpRequestPayload;

public class SetAttributeOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<SetAttributeOpRequestPayload,
        SetAttributeOpRequestPayload.SetAttributeOpRequestPayloadBuilder> {

  public SetAttributeOpRequestPayloadTtlvDeserializer() {
    super(SetAttributeOpRequestPayload.kmipTag, SetAttributeOpRequestPayload.encodingType);
  }

  @Override
  protected SetAttributeOpRequestPayload.SetAttributeOpRequestPayloadBuilder createBuilder() {
    return SetAttributeOpRequestPayload.builder();
  }

  @Override
  protected void setValue(SetAttributeOpRequestPayload.SetAttributeOpRequestPayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.NEW_ATTRIBUTE ->
          builder.newAttribute(mapper.readValue(p, NewAttribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected SetAttributeOpRequestPayload build(
      SetAttributeOpRequestPayload.SetAttributeOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}