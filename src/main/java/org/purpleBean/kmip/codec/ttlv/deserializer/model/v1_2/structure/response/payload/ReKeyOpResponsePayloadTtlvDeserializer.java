package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.ReKeyOpResponsePayload;

public class ReKeyOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ReKeyOpResponsePayload,
        ReKeyOpResponsePayload.ReKeyOpResponsePayloadBuilder> {

  public ReKeyOpResponsePayloadTtlvDeserializer() {
    super(ReKeyOpResponsePayload.kmipTag, ReKeyOpResponsePayload.encodingType);
  }

  @Override
  protected ReKeyOpResponsePayload.ReKeyOpResponsePayloadBuilder createBuilder() {
    return ReKeyOpResponsePayload.builder();
  }

  @Override
  protected void setValue(ReKeyOpResponsePayload.ReKeyOpResponsePayloadBuilder builder, byte[] tag,
                          byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
          builder.templateAttribute(mapper.readValue(p, TemplateAttribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ReKeyOpResponsePayload build(
      ReKeyOpResponsePayload.ReKeyOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
