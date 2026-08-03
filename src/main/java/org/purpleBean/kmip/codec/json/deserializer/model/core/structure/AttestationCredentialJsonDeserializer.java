package org.purpleBean.kmip.codec.json.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.api.AbstractKmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.model.core.enumeration.AttestationType;
import org.purpleBean.kmip.model.core.structure.AttestationCredential;
import org.purpleBean.kmip.model.core.structure.Nonce;
import org.purpleBean.kmip.model.core.type.AttestationAssertion;
import org.purpleBean.kmip.model.core.type.AttestationMeasurement;

public class AttestationCredentialJsonDeserializer extends
    AbstractKmipDataTypeJsonDeserializer<AttestationCredential,
        AttestationCredential.AttestationCredentialBuilder> {

  public AttestationCredentialJsonDeserializer() {
    super(AttestationCredential.kmipTag, AttestationCredential.encodingType);
  }

  @Override
  protected AttestationCredential.AttestationCredentialBuilder createBuilder() {
    return AttestationCredential.builder();
  }

  @Override
  protected void setValue(AttestationCredential.AttestationCredentialBuilder builder, String tag,
                          String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.NONCE -> builder.nonce(ctxt.readValue(p, Nonce.class));
      case KmipTag.Standard.ATTESTATION_TYPE ->
          builder.attestationType(ctxt.readValue(p, AttestationType.class));
      case KmipTag.Standard.ATTESTATION_MEASUREMENT ->
          builder.attestationMeasurement(ctxt.readValue(p, AttestationMeasurement.class));
      case KmipTag.Standard.ATTESTATION_ASSERTION ->
          builder.attestationAssertion(ctxt.readValue(p, AttestationAssertion.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected AttestationCredential build(
      AttestationCredential.AttestationCredentialBuilder builder) {
    return builder.build();
  }
}
