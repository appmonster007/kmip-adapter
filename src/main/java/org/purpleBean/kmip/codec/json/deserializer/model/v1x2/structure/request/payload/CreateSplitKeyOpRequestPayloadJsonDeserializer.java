package org.purpleBean.kmip.codec.json.deserializer.model.v1x2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.ObjectType;
import org.purpleBean.kmip.model.core.enumeration.SplitKeyMethod;
import org.purpleBean.kmip.model.core.structure.TemplateAttribute;
import org.purpleBean.kmip.model.core.type.PrimeFieldSize;
import org.purpleBean.kmip.model.core.type.SplitKeyParts;
import org.purpleBean.kmip.model.core.type.SplitKeyThreshold;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.CreateSplitKeyOpRequestPayload;

public class CreateSplitKeyOpRequestPayloadJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<CreateSplitKeyOpRequestPayload,
        CreateSplitKeyOpRequestPayload.CreateSplitKeyOpRequestPayloadBuilder> {

  public CreateSplitKeyOpRequestPayloadJsonDeserializer() {
    super(CreateSplitKeyOpRequestPayload.kmipTag, CreateSplitKeyOpRequestPayload.encodingType);
  }

  @Override
  protected CreateSplitKeyOpRequestPayload.CreateSplitKeyOpRequestPayloadBuilder createBuilder() {
    return CreateSplitKeyOpRequestPayload.builder();
  }

  @Override
  protected void setValue(
      CreateSplitKeyOpRequestPayload.CreateSplitKeyOpRequestPayloadBuilder builder, String tag,
      String type, JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.OBJECT_TYPE -> builder.objectType(ctxt.readValue(p, ObjectType.class));
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.SPLIT_KEY_PARTS ->
          builder.splitKeyParts(ctxt.readValue(p, SplitKeyParts.class));
      case KmipTag.Standard.SPLIT_KEY_THRESHOLD ->
          builder.splitKeyThreshold(ctxt.readValue(p, SplitKeyThreshold.class));
      case KmipTag.Standard.SPLIT_KEY_METHOD ->
          builder.splitKeyMethod(ctxt.readValue(p, SplitKeyMethod.class));
      case KmipTag.Standard.PRIME_FIELD_SIZE ->
          builder.primeFieldSize(ctxt.readValue(p, PrimeFieldSize.class));
      case KmipTag.Standard.TEMPLATE_ATTRIBUTE ->
          builder.templateAttribute(ctxt.readValue(p, TemplateAttribute.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CreateSplitKeyOpRequestPayload build(
      CreateSplitKeyOpRequestPayload.CreateSplitKeyOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
