package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purplebean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purplebean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purplebean.kmip.model.v1x2.structure.request.payload.CreateKeyPairOpRequestPayload;

/**
 * TTLV deserializer for {@link CreateKeyPairOpRequestPayload}.
 */
public class CreateKeyPairOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CreateKeyPairOpRequestPayload,
        CreateKeyPairOpRequestPayload.CreateKeyPairOpRequestPayloadBuilder> {

  /**
   * Constructs a new {@link CreateKeyPairOpRequestPayloadTtlvDeserializer}.
   */
  public CreateKeyPairOpRequestPayloadTtlvDeserializer() {
    super(CreateKeyPairOpRequestPayload.kmipTag, CreateKeyPairOpRequestPayload.encodingType);
  }

  @Override
  protected CreateKeyPairOpRequestPayload.CreateKeyPairOpRequestPayloadBuilder createBuilder() {
    return CreateKeyPairOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      CreateKeyPairOpRequestPayload.CreateKeyPairOpRequestPayloadBuilder builder, byte[] tag,
      byte type, ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.COMMON_TEMPLATE_ATTRIBUTE ->
          builder.commonTemplateAttribute(mapper.readValue(p, CommonTemplateAttribute.class));
      case KmipTag.Standard.PRIVATE_KEY_TEMPLATE_ATTRIBUTE -> builder.privateKeyTemplateAttribute(
          mapper.readValue(p, PrivateKeyTemplateAttribute.class));
      case KmipTag.Standard.PUBLIC_KEY_TEMPLATE_ATTRIBUTE ->
          builder.publicKeyTemplateAttribute(mapper.readValue(p, PublicKeyTemplateAttribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CreateKeyPairOpRequestPayload build(
      CreateKeyPairOpRequestPayload.CreateKeyPairOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
