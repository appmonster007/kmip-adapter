package org.purpleBean.kmip.codec.ttlv.deserializer.model.core.structure;

import org.purpleBean.kmip.api.EncodingType;
import org.purpleBean.kmip.api.KmipTag;
import org.purpleBean.kmip.codec.ttlv.deserializer.api.AbstractKmipStructureTtlvDeserializer;
import org.purpleBean.kmip.codec.ttlv.mapper.TtlvMapper;
import org.purpleBean.kmip.model.core.enumeration.*;
import org.purpleBean.kmip.model.core.structure.CryptographicParameters;
import org.purpleBean.kmip.model.core.type.*;

import java.io.IOException;
import java.nio.ByteBuffer;

public class CryptographicParametersTtlvDeserializer extends AbstractKmipStructureTtlvDeserializer<CryptographicParameters, CryptographicParameters.CryptographicParametersBuilder> {

    public CryptographicParametersTtlvDeserializer() {
        super(CryptographicParameters.kmipTag);
    }

    @Override
    protected CryptographicParameters.CryptographicParametersBuilder createBuilder() {
        return CryptographicParameters.builder();
    }

    @Override
    protected void setValue(CryptographicParameters.CryptographicParametersBuilder builder, KmipTag.Value nodeTag, ByteBuffer p, TtlvMapper mapper) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.BLOCK_CIPHER_MODE ->
                    builder.blockCipherMode(mapper.readValue(p, BlockCipherMode.class));
            case KmipTag.Standard.PADDING_METHOD -> builder.paddingMethod(mapper.readValue(p, PaddingMethod.class));
            case KmipTag.Standard.HASHING_ALGORITHM ->
                    builder.hashingAlgorithm(mapper.readValue(p, HashingAlgorithm.class));
            case KmipTag.Standard.KEY_ROLE_TYPE -> builder.keyRoleType(mapper.readValue(p, KeyRoleType.class));
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
            case KmipTag.Standard.COUNTER_LENGTH -> builder.counterLength(mapper.readValue(p, CounterLength.class));
            case KmipTag.Standard.INITIAL_COUNTER_VALUE ->
                    builder.initialCounterValue(mapper.readValue(p, InitialCounterValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CryptographicParameters build(CryptographicParameters.CryptographicParametersBuilder builder) {
        return builder.build();
    }

    @Override
    protected EncodingType getEncodingType() {
        return CryptographicParameters.encodingType;
    }
}