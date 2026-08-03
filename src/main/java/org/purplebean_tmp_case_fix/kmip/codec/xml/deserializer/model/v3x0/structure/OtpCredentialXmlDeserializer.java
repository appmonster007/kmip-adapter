package org.purplebean.kmip.codec.xml.deserializer.model.v3x0.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.v3x0.enumeration.OtpAlgorithm;
import org.purplebean.kmip.model.v3x0.structure.OtpCredential;
import org.purplebean.kmip.model.v3x0.type.OtpCounter;
import org.purplebean.kmip.model.v3x0.type.OtpDigest;
import org.purplebean.kmip.model.v3x0.type.OtpDigits;
import org.purplebean.kmip.model.v3x0.type.OtpInterval;
import org.purplebean.kmip.model.v3x0.type.OtpSeed;
import org.purplebean.kmip.model.v3x0.type.OtpSerial;

public class OtpCredentialXmlDeserializer
    extends AbstractKmipDataTypeXmlDeserializer<OtpCredential, OtpCredential.OtpCredentialBuilder> {

  public OtpCredentialXmlDeserializer() {
    super(OtpCredential.kmipTag, OtpCredential.encodingType);
  }

  @Override
  protected OtpCredential.OtpCredentialBuilder createBuilder() {
    return OtpCredential.builder();
  }

  @Override
  protected void setValue(OtpCredential.OtpCredentialBuilder builder, String tag, String type,
                          JsonParser p, DeserializationContext ctxt) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    if (nodeTag == OtpAlgorithm.kmipTag.getValue()) {
      builder.otpAlgorithm(ctxt.readValue(p, OtpAlgorithm.class));
    } else if (nodeTag == OtpDigest.kmipTag.getValue()) {
      builder.otpDigest(ctxt.readValue(p, OtpDigest.class));
    } else if (nodeTag == OtpSerial.kmipTag.getValue()) {
      builder.otpSerial(ctxt.readValue(p, OtpSerial.class));
    } else if (nodeTag == OtpSeed.kmipTag.getValue()) {
      builder.otpSeed(ctxt.readValue(p, OtpSeed.class));
    } else if (nodeTag == OtpInterval.kmipTag.getValue()) {
      builder.otpInterval(ctxt.readValue(p, OtpInterval.class));
    } else if (nodeTag == OtpDigits.kmipTag.getValue()) {
      builder.otpDigits(ctxt.readValue(p, OtpDigits.class));
    } else if (nodeTag == OtpCounter.kmipTag.getValue()) {
      builder.otpCounter(ctxt.readValue(p, OtpCounter.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected OtpCredential build(OtpCredential.OtpCredentialBuilder builder) {
    return builder.build();
  }
}