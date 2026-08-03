package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purplebean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purplebean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purplebean.kmip.model.core.type.Offset;
import org.purplebean.kmip.model.core.type.PrivateKeyUniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.ReKeyKeyPairOpRequestPayload;
import org.purplebean.kmip.model.v2x1.structure.CommonAttributes;
import org.purplebean.kmip.model.v2x1.structure.PrivateKeyAttributes;
import org.purplebean.kmip.model.v2x1.structure.PublicKeyAttributes;

public class ReKeyKeyPairOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<ReKeyKeyPairOpRequestPayload,
        ReKeyKeyPairOpRequestPayload.ReKeyKeyPairOpRequestPayloadBuilder> {

  public ReKeyKeyPairOpRequestPayloadTtlvDeserializer() {
    super(ReKeyKeyPairOpRequestPayload.kmipTag, ReKeyKeyPairOpRequestPayload.encodingType);
  }

  @Override
  protected ReKeyKeyPairOpRequestPayload.ReKeyKeyPairOpRequestPayloadBuilder createBuilder() {
    return ReKeyKeyPairOpRequestPayload.builder();
  }

  @Override
  protected void setValue(ReKeyKeyPairOpRequestPayload.ReKeyKeyPairOpRequestPayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.PRIVATE_KEY_UNIQUE_IDENTIFIER ->
          builder.privateKeyUniqueIdentifier(mapper.readValue(p, PrivateKeyUniqueIdentifier.class));
      case KmipTag.Standard.OFFSET -> builder.offset(mapper.readValue(p, Offset.class));
      case KmipTag.Standard.COMMON_TEMPLATE_ATTRIBUTE ->
          builder.commonTemplateAttribute(mapper.readValue(p, CommonTemplateAttribute.class));
      case KmipTag.Standard.PRIVATE_KEY_TEMPLATE_ATTRIBUTE -> builder.privateKeyTemplateAttribute(
          mapper.readValue(p, PrivateKeyTemplateAttribute.class));
      case KmipTag.Standard.PUBLIC_KEY_TEMPLATE_ATTRIBUTE ->
          builder.publicKeyTemplateAttribute(mapper.readValue(p, PublicKeyTemplateAttribute.class));
      case KmipTag.Standard.COMMON_ATTRIBUTES ->
          builder.commonAttributes(mapper.readValue(p, CommonAttributes.class));
      case KmipTag.Standard.PRIVATE_KEY_ATTRIBUTES ->
          builder.privateKeyAttributes(mapper.readValue(p, PrivateKeyAttributes.class));
      case KmipTag.Standard.PUBLIC_KEY_ATTRIBUTES ->
          builder.publicKeyAttributes(mapper.readValue(p, PublicKeyAttributes.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ReKeyKeyPairOpRequestPayload build(
      ReKeyKeyPairOpRequestPayload.ReKeyKeyPairOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
