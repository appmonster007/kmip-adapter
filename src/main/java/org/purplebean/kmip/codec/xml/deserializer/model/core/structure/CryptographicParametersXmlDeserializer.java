package org.purplebean.kmip.codec.xml.deserializer.model.core.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import java.io.IOException;
import org.purplebean.kmip.api.KmipTag;
import org.purplebean.kmip.codec.xml.deserializer.api.AbstractKmipDataTypeXmlDeserializer;
import org.purplebean.kmip.model.core.enumeration.BlockCipherMode;
import org.purplebean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purplebean.kmip.model.core.enumeration.DigitalSignatureAlgorithm;
import org.purplebean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purplebean.kmip.model.core.enumeration.KeyRoleType;
import org.purplebean.kmip.model.core.enumeration.PaddingMethod;
import org.purplebean.kmip.model.core.structure.CryptographicParameters;
import org.purplebean.kmip.model.core.type.CounterLength;
import org.purplebean.kmip.model.core.type.FixedFieldLength;
import org.purplebean.kmip.model.core.type.InitialCounterValue;
import org.purplebean.kmip.model.core.type.InvocationFieldLength;
import org.purplebean.kmip.model.core.type.IvLength;
import org.purplebean.kmip.model.core.type.RandomIv;
import org.purplebean.kmip.model.core.type.TagLength;

public class CryptographicParametersXmlDeserializer extends
    AbstractKmipDataTypeXmlDeserializer<CryptographicParameters,
        CryptographicParameters.CryptographicParametersBuilder> {

  public CryptographicParametersXmlDeserializer() {
    super(CryptographicParameters.kmipTag, CryptographicParameters.encodingType);
  }

  @Override
  protected CryptographicParameters.CryptographicParametersBuilder createBuilder() {
    return CryptographicParameters.builder();
  }

  @Override
  protected void setValue(CryptographicParameters.CryptographicParametersBuilder builder,
                          String tag, String type, JsonParser p, DeserializationContext ctxt)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromName(tag);
    switch (nodeTag) {
      case KmipTag.Standard.BLOCK_CIPHER_MODE ->
          builder.blockCipherMode(ctxt.readValue(p, BlockCipherMode.class));
      case KmipTag.Standard.PADDING_METHOD ->
          builder.paddingMethod(ctxt.readValue(p, PaddingMethod.class));
      case KmipTag.Standard.HASHING_ALGORITHM ->
          builder.hashingAlgorithm(ctxt.readValue(p, HashingAlgorithm.class));
      case KmipTag.Standard.KEY_ROLE_TYPE ->
          builder.keyRoleType(ctxt.readValue(p, KeyRoleType.class));
      case KmipTag.Standard.DIGITAL_SIGNATURE_ALGORITHM ->
          builder.digitalSignatureAlgorithm(ctxt.readValue(p, DigitalSignatureAlgorithm.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_ALGORITHM ->
          builder.cryptographicAlgorithm(ctxt.readValue(p, CryptographicAlgorithm.class));
      case KmipTag.Standard.RANDOM_IV -> builder.randomIv(ctxt.readValue(p, RandomIv.class));
      case KmipTag.Standard.IV_LENGTH -> builder.ivLength(ctxt.readValue(p, IvLength.class));
      case KmipTag.Standard.TAG_LENGTH -> builder.tagLength(ctxt.readValue(p, TagLength.class));
      case KmipTag.Standard.FIXED_FIELD_LENGTH ->
          builder.fixedFieldLength(ctxt.readValue(p, FixedFieldLength.class));
      case KmipTag.Standard.INVOCATION_FIELD_LENGTH ->
          builder.invocationFieldLength(ctxt.readValue(p, InvocationFieldLength.class));
      case KmipTag.Standard.COUNTER_LENGTH ->
          builder.counterLength(ctxt.readValue(p, CounterLength.class));
      case KmipTag.Standard.INITIAL_COUNTER_VALUE ->
          builder.initialCounterValue(ctxt.readValue(p, InitialCounterValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CryptographicParameters build(
      CryptographicParameters.CryptographicParametersBuilder builder) {
    return builder.build();
  }
}