package org.purplebean.kmip.codec.xml.deserializer.model.v1x2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.structure.Certificate;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.core.type.ValidityDate;
import org.purplebean.kmip.model.v1x2.structure.request.payload.ValidateOpRequestPayload;

public class ValidateOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<ValidateOpRequestPayload,
        ValidateOpRequestPayload.ValidateOpRequestPayloadBuilder> {

  public ValidateOpRequestPayloadXmlDeserializer() {
    super(ValidateOpRequestPayload.kmipTag, ValidateOpRequestPayload.encodingType);
  }

  @Override
  protected ValidateOpRequestPayload.ValidateOpRequestPayloadBuilder createBuilder() {
    return ValidateOpRequestPayload.builder();
  }

  @Override
  protected void setValue(ValidateOpRequestPayload.ValidateOpRequestPayloadBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.CERTIFICATE ->
          builder.certificate(ctxt.readValue(p, Certificate.class));
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.VALIDITY_DATE ->
          builder.validityDate(ctxt.readValue(p, ValidityDate.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected ValidateOpRequestPayload build(
      ValidateOpRequestPayload.ValidateOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
