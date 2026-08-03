package org.purplebean.kmip.codec.json.deserializer.model.v1x2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purplebean.kmip.model.core.enumeration.DerivationMethod;
import org.purplebean.kmip.model.core.enumeration.ObjectType;
import org.purplebean.kmip.model.core.structure.DerivationParameters;
import org.purplebean.kmip.model.core.structure.TemplateAttribute;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.DeriveKeyOpRequestPayload;

public class DeriveKeyOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<DeriveKeyOpRequestPayload,
        DeriveKeyOpRequestPayload.DeriveKeyOpRequestPayloadBuilder> {

  public DeriveKeyOpRequestPayloadJsonDeserializer() {
    super(DeriveKeyOpRequestPayload.kmipTag, DeriveKeyOpRequestPayload.encodingType);
  }

  @Override
  protected DeriveKeyOpRequestPayload.DeriveKeyOpRequestPayloadBuilder createBuilder() {
    return DeriveKeyOpRequestPayload.builder();
  }

  @Override
  protected void setValue(DeriveKeyOpRequestPayload.DeriveKeyOpRequestPayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(ctxt.readValue(p, ObjectType.class));
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.DERIVATION_METHOD ->
          builder.derivationMethod(ctxt.readValue(p, DerivationMethod.class));
      case KmipTag.Standard.DERIVATION_PARAMETERS ->
          builder.derivationParameters(ctxt.readValue(p, DerivationParameters.class));
      case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
          builder.templateAttribute(ctxt.readValue(p, TemplateAttribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected DeriveKeyOpRequestPayload build(
      DeriveKeyOpRequestPayload.DeriveKeyOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}