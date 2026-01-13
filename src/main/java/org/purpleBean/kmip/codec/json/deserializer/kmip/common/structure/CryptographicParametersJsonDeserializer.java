package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.AbstractKmipStructureJsonDeserializer;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.CryptographicParameters;

import java.io.IOException;

public class CryptographicParametersJsonDeserializer extends AbstractKmipStructureJsonDeserializer<CryptographicParameters, CryptographicParameters.CryptographicParametersBuilder> {

    public CryptographicParametersJsonDeserializer() {
        super(CryptographicParameters.kmipTag, CryptographicParameters.encodingType);
    }

    @Override
    protected CryptographicParameters.CryptographicParametersBuilder createBuilder() {
        return CryptographicParameters.builder();
    }

    @Override
    protected void setValue(CryptographicParameters.CryptographicParametersBuilder builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.BLOCK_CIPHER_MODE ->
                    builder.blockCipherMode(ctxt.readValue(p, BlockCipherMode.class));
            case KmipTag.Standard.PADDING_METHOD -> builder.paddingMethod(ctxt.readValue(p, PaddingMethod.class));
            case KmipTag.Standard.HASHING_ALGORITHM ->
                    builder.hashingAlgorithm(ctxt.readValue(p, HashingAlgorithm.class));
            case KmipTag.Standard.KEY_ROLE_TYPE -> builder.keyRoleType(ctxt.readValue(p, KeyRoleType.class));
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
            case KmipTag.Standard.COUNTER_LENGTH -> builder.counterLength(ctxt.readValue(p, CounterLength.class));
            case KmipTag.Standard.INITIAL_COUNTER_VALUE ->
                    builder.initialCounterValue(ctxt.readValue(p, InitialCounterValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }

    @Override
    protected CryptographicParameters build(CryptographicParameters.CryptographicParametersBuilder builder) {
        return builder.build();
    }
}