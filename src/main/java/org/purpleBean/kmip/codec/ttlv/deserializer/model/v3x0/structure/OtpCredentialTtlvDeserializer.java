package org.purplebean.kmip.codec.ttlv.deserializer.model.v3x0.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purplebean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purplebean.kmip.model.v3x0.enumeration.OtpAlgorithm;
import org.purplebean.kmip.model.v3x0.structure.OtpCredential;
import org.purplebean.kmip.model.v3x0.type.OtpCounter;
import org.purplebean.kmip.model.v3x0.type.OtpDigest;
import org.purplebean.kmip.model.v3x0.type.OtpDigits;
import org.purplebean.kmip.model.v3x0.type.OtpInterval;
import org.purplebean.kmip.model.v3x0.type.OtpSeed;
import org.purplebean.kmip.model.v3x0.type.OtpSerial;

public class OtpCredentialTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<OtpCredential, OtpCredential.OtpCredentialBuilder> {

  public OtpCredentialTtlvDeserializer() {
    super(OtpCredential.kmipTag, OtpCredential.encodingType);
  }

  @Override
  protected OtpCredential.OtpCredentialBuilder createBuilder() {
    return OtpCredential.builder();
  }

  @Override
  protected void setValue(OtpCredential.OtpCredentialBuilder builder, byte[] tag, byte type,
                          ByteBuffer p, TtlvMapper mapper) throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    if (nodeTag == OtpAlgorithm.kmipTag.getValue()) {
      builder.otpAlgorithm(mapper.readValue(p, OtpAlgorithm.class));
    } else if (nodeTag == OtpDigest.kmipTag.getValue()) {
      builder.otpDigest(mapper.readValue(p, OtpDigest.class));
    } else if (nodeTag == OtpSerial.kmipTag.getValue()) {
      builder.otpSerial(mapper.readValue(p, OtpSerial.class));
    } else if (nodeTag == OtpSeed.kmipTag.getValue()) {
      builder.otpSeed(mapper.readValue(p, OtpSeed.class));
    } else if (nodeTag == OtpInterval.kmipTag.getValue()) {
      builder.otpInterval(mapper.readValue(p, OtpInterval.class));
    } else if (nodeTag == OtpDigits.kmipTag.getValue()) {
      builder.otpDigits(mapper.readValue(p, OtpDigits.class));
    } else if (nodeTag == OtpCounter.kmipTag.getValue()) {
      builder.otpCounter(mapper.readValue(p, OtpCounter.class));
    } else {
      throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected OtpCredential build(OtpCredential.OtpCredentialBuilder builder) {
    return builder.build();
  }
}