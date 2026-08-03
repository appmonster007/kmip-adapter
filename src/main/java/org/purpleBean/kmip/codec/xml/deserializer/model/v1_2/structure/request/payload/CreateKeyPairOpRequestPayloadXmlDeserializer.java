package org.purpleBean.kmip.codec.xml.deserializer.model.v1_2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.structure.CommonTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PrivateKeyTemplateAttribute;
import org.purpleBean.kmip.model.core.structure.PublicKeyTemplateAttribute;
import org.purpleBean.kmip.model.v1_2.structure.request.payload.CreateKeyPairOpRequestPayload;

public class CreateKeyPairOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CreateKeyPairOpRequestPayload,
        CreateKeyPairOpRequestPayload.CreateKeyPairOpRequestPayloadBuilder> {

  public CreateKeyPairOpRequestPayloadXmlDeserializer() {
    super(CreateKeyPairOpRequestPayload.kmipTag, CreateKeyPairOpRequestPayload.encodingType);
  }

  @Override
  protected CreateKeyPairOpRequestPayload.CreateKeyPairOpRequestPayloadBuilder createBuilder() {
    return CreateKeyPairOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      CreateKeyPairOpRequestPayload.CreateKeyPairOpRequestPayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.COMMON_TEMPLATE_ATTRIBUTE ->
          builder.commonTemplateAttribute(ctxt.readValue(p, CommonTemplateAttribute.class));
      case KmipTag.Standard.PRIVATE_KEY_TEMPLATE_ATTRIBUTE ->
          builder.privateKeyTemplateAttribute(ctxt.readValue(p, PrivateKeyTemplateAttribute.class));
      case KmipTag.Standard.PUBLIC_KEY_TEMPLATE_ATTRIBUTE ->
          builder.publicKeyTemplateAttribute(ctxt.readValue(p, PublicKeyTemplateAttribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CreateKeyPairOpRequestPayload build(
      CreateKeyPairOpRequestPayload.CreateKeyPairOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}