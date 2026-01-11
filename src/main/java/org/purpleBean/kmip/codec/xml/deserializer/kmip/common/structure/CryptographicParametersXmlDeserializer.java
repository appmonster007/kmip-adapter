package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.CryptographicParameters;

import java.io.IOException;

public class CryptographicParametersXmlDeserializer extends KmipDataTypeXmlDeserializer<CryptographicParameters> {
    private final KmipTag kmipTag = CryptographicParameters.kmipTag;

    @Override
    public CryptographicParameters deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.currentToken() == null) {
            p.nextToken();
        }

        String currentName;
        if (p instanceof FromXmlParser xmlParser) {
            currentName = xmlParser.getStaxReader().getLocalName();
        } else {
            currentName = (String) ctxt.getAttribute("tag");
        }

        if (!kmipTag.getDescription().equalsIgnoreCase(currentName)) {
            ctxt.reportInputMismatch(CryptographicParameters.class, "Invalid Tag for CryptographicParameters");
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        CryptographicParameters.CryptographicParametersBuilder builder = CryptographicParameters.builder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken();
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(CryptographicParameters.class, "Unexpected token: " + p.currentToken());
            }
        }

        CryptographicParameters cryptographicparameters = builder.build();

        if (!cryptographicparameters.isSupported()) {
            ctxt.reportInputMismatch(CryptographicParameters.class, "CryptographicParameters not supported for spec " + spec);
            return null;
        }

        return cryptographicparameters;
    }

    private void setValue(
            CryptographicParameters.CryptographicParametersBuilder builder,
            KmipTag.Value nodeTag,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        ctxt.setAttribute("tag", p.currentName());
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
}