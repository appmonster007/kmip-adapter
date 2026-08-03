package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.DeriveKeyOpResponsePayload;

public class DeriveKeyOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DeriveKeyOpResponsePayload,
        DeriveKeyOpResponsePayload.DeriveKeyOpResponsePayloadBuilder> {

  public DeriveKeyOpResponsePayloadTtlvDeserializer() {
    super(DeriveKeyOpResponsePayload.kmipTag, DeriveKeyOpResponsePayload.encodingType);
  }

  @Override
  protected DeriveKeyOpResponsePayload.DeriveKeyOpResponsePayloadBuilder createBuilder() {
    return DeriveKeyOpResponsePayload.builder();
  }

  @Override
  protected void setValue(DeriveKeyOpResponsePayload.DeriveKeyOpResponsePayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
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
  protected DeriveKeyOpResponsePayload build(
      DeriveKeyOpResponsePayload.DeriveKeyOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
