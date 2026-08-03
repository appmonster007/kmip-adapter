package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.response.payload.CertifyOpResponsePayload;

public class CertifyOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CertifyOpResponsePayload,
        CertifyOpResponsePayload.CertifyOpResponsePayloadBuilder> {

  public CertifyOpResponsePayloadTtlvDeserializer() {
    super(CertifyOpResponsePayload.kmipTag, CertifyOpResponsePayload.encodingType);
  }

  @Override
  protected CertifyOpResponsePayload.CertifyOpResponsePayloadBuilder createBuilder() {
    return CertifyOpResponsePayload.builder();
  }

  @Override
  protected void setValue(CertifyOpResponsePayload.CertifyOpResponsePayloadBuilder builder,
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
  protected CertifyOpResponsePayload build(
      CertifyOpResponsePayload.CertifyOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
