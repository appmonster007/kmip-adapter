package org.purplebean.kmip.codec.ttlv.deserializer.model.v1x2.structure.request.payload;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.core.enumeration.DerivationMethod;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.structure.DerivationParameters;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.DeriveKeyOpRequestPayload;

public class DeriveKeyOpRequestPayloadTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<DeriveKeyOpRequestPayload,
        DeriveKeyOpRequestPayload.DeriveKeyOpRequestPayloadBuilder> {

  public DeriveKeyOpRequestPayloadTtlvDeserializer() {
    super(DeriveKeyOpRequestPayload.kmipTag, DeriveKeyOpRequestPayload.encodingType);
  }

  @Override
  protected DeriveKeyOpRequestPayload.DeriveKeyOpRequestPayloadBuilder createBuilder() {
    return DeriveKeyOpRequestPayload.builder();
  }

  @Override
  protected void setValue(DeriveKeyOpRequestPayload.DeriveKeyOpRequestPayloadBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_TYPE ->
          builder.objectType(mapper.readValue(p, ObjectType.class));
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(mapper.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.DERIVATION_METHOD ->
          builder.derivationMethod(mapper.readValue(p, DerivationMethod.class));
      case KmipTag.Standard.DERIVATION_PARAMETERS ->
          builder.derivationParameters(mapper.readValue(p, DerivationParameters.class));
      case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
          builder.templateAttribute(mapper.readValue(p, TemplateAttribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DeriveKeyOpRequestPayload build(
      DeriveKeyOpRequestPayload.DeriveKeyOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
