package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import java.io.IOException;
import java.nio.ByteBuffer;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipDataTypeTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.BlockCipherMode;
import org.purpleBean.kmip.model.core.enumeration.CryptographicAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.DigitalSignatureAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.HashingAlgorithm;
import org.purpleBean.kmip.model.core.enumeration.KeyRoleType;
import org.purpleBean.kmip.model.core.enumeration.PaddingMethod;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.CounterLength;
import org.purpleBean.kmip.model.core.type.FixedFieldLength;
import org.purpleBean.kmip.model.core.type.InitialCounterValue;
import org.purpleBean.kmip.model.core.type.InvocationFieldLength;
import org.purpleBean.kmip.model.core.type.IvLength;
import org.purpleBean.kmip.model.core.type.RandomIv;
import org.purpleBean.kmip.model.core.type.TagLength;

public class CryptographicParametersTtlvDeserializer extends
    AbstractKmipDataTypeTtlvDeserializer<CryptographicParameters,
        CryptographicParameters.CryptographicParametersBuilder> {

  public CryptographicParametersTtlvDeserializer() {
    super(CryptographicParameters.kmipTag, CryptographicParameters.encodingType);
  }

  @Override
  protected CryptographicParameters.CryptographicParametersBuilder createBuilder() {
    return CryptographicParameters.builder();
  }

  @Override
  protected void setValue(CryptographicParameters.CryptographicParametersBuilder builder,
                          byte[] tag, byte type, ByteBuffer p, TtlvMapper mapper)
      throws IOException {
    KmipTag.Value nodeTag = KmipTag.fromBytes(tag);
    switch (nodeTag) {
      case KmipTag.Standard.BLOCK_CIPHER_MODE ->
          builder.blockCipherMode(mapper.readValue(p, BlockCipherMode.class));
      case KmipTag.Standard.PADDING_METHOD ->
          builder.paddingMethod(mapper.readValue(p, PaddingMethod.class));
      case KmipTag.Standard.HASHING_ALGORITHM ->
          builder.hashingAlgorithm(mapper.readValue(p, HashingAlgorithm.class));
      case KmipTag.Standard.KEY_ROLE_TYPE ->
          builder.keyRoleType(mapper.readValue(p, KeyRoleType.class));
      case KmipTag.Standard.DIGITAL_SIGNATURE_ALGORITHM ->
          builder.digitalSignatureAlgorithm(mapper.readValue(p, DigitalSignatureAlgorithm.class));
      case KmipTag.Standard.CRYPTOGRAPHIC_ALGORITHM ->
          builder.cryptographicAlgorithm(mapper.readValue(p, CryptographicAlgorithm.class));
      case KmipTag.Standard.RANDOM_IV -> builder.randomIv(mapper.readValue(p, RandomIv.class));
      case KmipTag.Standard.IV_LENGTH -> builder.ivLength(mapper.readValue(p, IvLength.class));
      case KmipTag.Standard.TAG_LENGTH -> builder.tagLength(mapper.readValue(p, TagLength.class));
      case KmipTag.Standard.FIXED_FIELD_LENGTH ->
          builder.fixedFieldLength(mapper.readValue(p, FixedFieldLength.class));
      case KmipTag.Standard.INVOCATION_FIELD_LENGTH ->
          builder.invocationFieldLength(mapper.readValue(p, InvocationFieldLength.class));
      case KmipTag.Standard.COUNTER_LENGTH ->
          builder.counterLength(mapper.readValue(p, CounterLength.class));
      case KmipTag.Standard.INITIAL_COUNTER_VALUE ->
          builder.initialCounterValue(mapper.readValue(p, InitialCounterValue.class));
      default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
    }
  }

  @Override
  protected CryptographicParameters build(
      CryptographicParameters.CryptographicParametersBuilder builder) {
    return builder.build();
  }
}