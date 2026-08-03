package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.ModifyAttributeOpResponsePayload;

public class ModifyAttributeOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ModifyAttributeOpResponsePayload,
        ModifyAttributeOpResponsePayload.ModifyAttributeOpResponsePayloadBuilder> {

  public ModifyAttributeOpResponsePayloadTtlvDeserializer() {
    super(ModifyAttributeOpResponsePayload.kmipTag, ModifyAttributeOpResponsePayload.encodingType);
  }

  @Override
  protected ModifyAttributeOpResponsePayload.ModifyAttributeOpResponsePayloadBuilder createBuilder() {
    return ModifyAttributeOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      ModifyAttributeOpResponsePayload.ModifyAttributeOpResponsePayloadBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.ATTRIBUTE -> builder.attribute(mapper.readValue(p, Attribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ModifyAttributeOpResponsePayload build(
      ModifyAttributeOpResponsePayload.ModifyAttributeOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
