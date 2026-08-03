package org.purpleBean.kmip.codec.xml.deserializer.model.v1x2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.api.ManagedObject;
import org.purpleBean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.model.core.enumeration.PutFunction;
import org.purpleBean.kmip.model.core.structure.Attribute;
import org.purpleBean.kmip.model.core.type.ReplacedUniqueIdentifier;
import org.purpleBean.kmip.model.core.type.UniqueIdentifier;
import org.purpleBean.kmip.model.v1x2.structure.request.payload.PutOpRequestPayload;

public class PutOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<PutOpRequestPayload,
        PutOpRequestPayload.PutOpRequestPayloadBuilder> {

  public PutOpRequestPayloadXmlDeserializer() {
    super(PutOpRequestPayload.kmipTag, PutOpRequestPayload.encodingType);
  }

  @Override
  protected PutOpRequestPayload.PutOpRequestPayloadBuilder createBuilder() {
    return PutOpRequestPayload.builder();
  }

  @Override
  protected void setValue(PutOpRequestPayload.PutOpRequestPayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.PUT_FUNCTION ->
          builder.putFunction(ctxt.readValue(p, PutFunction.class));
      case KmipTag.Standard.REPLACED_UNIQUE_IDENTIFIER ->
          builder.replacedUniqueIdentifier(ctxt.readValue(p, ReplacedUniqueIdentifier.class));
      case KmipTag.Standard.ATTRIBUTE -> builder.attribute(ctxt.readValue(p, Attribute.class));
      default -> {
        if (ManagedObject.isManagedObject(nodeTag)) {
          builder.object(ctxt.readValue(p, ManagedObject.class));
        } else {
          throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
      }
    }
  }

  @Override
  protected PutOpRequestPayload build(PutOpRequestPayload.PutOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
