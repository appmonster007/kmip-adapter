package org.purpleBean.kmip.codec.ttlv.deserializer.model.v1_2.structure.response.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purpleBean.kmip.model.core.type.PublicKeyUniqueIdentifier;
import org.purpleBean.kmip.model.v1_2.structure.response.payload.ReKeyKeyPairOpResponsePayload;

public class ReKeyKeyPairOpResponsePayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ReKeyKeyPairOpResponsePayload,
        ReKeyKeyPairOpResponsePayload.ReKeyKeyPairOpResponsePayloadBuilder> {

  public ReKeyKeyPairOpResponsePayloadTtlvDeserializer() {
    super(ReKeyKeyPairOpResponsePayload.kmipTag, ReKeyKeyPairOpResponsePayload.encodingType);
  }

  @Override
  protected ReKeyKeyPairOpResponsePayload.ReKeyKeyPairOpResponsePayloadBuilder createBuilder() {
    return ReKeyKeyPairOpResponsePayload.builder();
  }

  @Override
  protected void setValue(
      ReKeyKeyPairOpResponsePayload.ReKeyKeyPairOpResponsePayloadBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.PRIVATE_KEY_UNIQUE_IDENTIFIER ->
          builder.privateKeyUniqueIdentifier(mapper.readValue(p, PrivateKeyUniqueIdentifier.class));
      case KmipTag.Standard.PUBLIC_KEY_UNIQUE_IDENTIFIER ->
          builder.publicKeyUniqueIdentifier(mapper.readValue(p, PublicKeyUniqueIdentifier.class));
      case KmipTag.Standard.PRIVATE_KEY_TEMPLATE_ATTRIBUTE -> builder.privateKeyTemplateAttribute(
          mapper.readValue(p, PrivateKeyTemplateAttribute.class));
      case KmipTag.Standard.PUBLIC_KEY_TEMPLATE_ATTRIBUTE ->
          builder.publicKeyTemplateAttribute(mapper.readValue(p, PublicKeyTemplateAttribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ReKeyKeyPairOpResponsePayload build(
      ReKeyKeyPairOpResponsePayload.ReKeyKeyPairOpResponsePayloadBuilder builder) {
    return builder.build();
  }
}
