package org.purplebean.kmip.codec.xml.deserializer.model.v1x2.structure.request.payload;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.structure.RevocationReason;
import org.purplebean.kmip.model.core.type.CompromiseOccurrenceDate;
import org.purplebean.kmip.model.core.type.UniqueIdentifier;
import org.purplebean.kmip.model.v1x2.structure.request.payload.RevokeOpRequestPayload;

public class RevokeOpRequestPayloadXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<RevokeOpRequestPayload,
        RevokeOpRequestPayload.RevokeOpRequestPayloadBuilder> {

  public RevokeOpRequestPayloadXmlDeserializer() {
    super(RevokeOpRequestPayload.kmipTag, RevokeOpRequestPayload.encodingType);
  }

  @Override
  protected RevokeOpRequestPayload.RevokeOpRequestPayloadBuilder createBuilder() {
    return RevokeOpRequestPayload.builder();
  }

  @Override
  protected void setValue(RevokeOpRequestPayload.RevokeOpRequestPayloadBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.UNIQUE_IDENTIFIER ->
          builder.uniqueIdentifier(ctxt.readValue(p, UniqueIdentifier.class));
      case KmipTag.Standard.REVOCATION_REASON ->
          builder.revocationReason(ctxt.readValue(p, RevocationReason.class));
      case KmipTag.Standard.COMPROMISE_OCCURRENCE_DATE ->
          builder.compromiseOccurrenceDate(ctxt.readValue(p, CompromiseOccurrenceDate.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected RevokeOpRequestPayload build(
      RevokeOpRequestPayload.RevokeOpRequestPayloadBuilder builder) {
    return builder.build();
  }
}
