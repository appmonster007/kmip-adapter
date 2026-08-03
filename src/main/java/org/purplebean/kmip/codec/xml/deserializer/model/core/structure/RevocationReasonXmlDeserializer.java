package org.purplebean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.RevocationReasonCode;
import org.purplebean.kmip.model.core.structure.RevocationReason;
import org.purplebean.kmip.model.core.type.RevocationMessage;

public class RevocationReasonXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<RevocationReason,
        RevocationReason.RevocationReasonBuilder> {

  public RevocationReasonXmlDeserializer() {
    super(RevocationReason.kmipTag, RevocationReason.encodingType);
  }

  @Override
  protected RevocationReason.RevocationReasonBuilder createBuilder() {
    return RevocationReason.builder();
  }

  @Override
  protected void setValue(RevocationReason.RevocationReasonBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.REVOCATION_REASON_CODE ->
          builder.revocationReasonCode(ctxt.readValue(p, RevocationReasonCode.class));
      case KmipTag.Standard.REVOCATION_MESSAGE ->
          builder.revocationMessage(ctxt.readValue(p, RevocationMessage.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected RevocationReason build(RevocationReason.RevocationReasonBuilder builder) {
    return builder.build();
  }
}