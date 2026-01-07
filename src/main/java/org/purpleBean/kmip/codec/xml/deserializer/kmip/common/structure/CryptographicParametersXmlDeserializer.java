package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.CryptographicParameters;

import java.io.IOException;
import java.util.Map;

public class CryptographicParametersXmlDeserializer extends KmipDataTypeXmlDeserializer<CryptographicParameters> {
    private final KmipTag kmipTag = CryptographicParameters.kmipTag;
    private final EncodingType encodingType = CryptographicParameters.encodingType;

    @Override
    public CryptographicParameters deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(CryptographicParameters.class, "Expected XML object for CryptographicParameters");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(CryptographicParameters.class, "Invalid Tag for CryptographicParameters");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        CryptographicParameters.CryptographicParametersBuilder builder = CryptographicParameters.builder();

        // Process all fields in the XML
        for (Map.Entry<String, JsonNode> entry : node.properties()) {
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        CryptographicParameters cryptographicparameters = builder.build();

        if (!cryptographicparameters.isSupported()) {
            ctxt.reportInputMismatch(CryptographicParameters.class, "CryptographicParameters not supported for spec " + spec);
            return null;
        }

        return cryptographicparameters;
    }

    /**
     * Sets the appropriate field in the builder based on the tag and value.
     *
     * @param builder the builder to set the field on
     * @param nodeTag the tag identifying the field to set
     * @param node    the XML node containing the field value
     * @param p       the JsonParser
     * @param ctxt    the DeserializationContext
     * @throws IOException if there is an error deserializing the value
     */
    private void setValue(
            CryptographicParameters.CryptographicParametersBuilder builder,
            KmipTag.Value nodeTag,
            JsonNode node,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.BLOCK_CIPHER_MODE ->
                    builder.blockCipherMode(p.getCodec().treeToValue(node, BlockCipherMode.class));
            case KmipTag.Standard.PADDING_METHOD ->
                    builder.paddingMethod(p.getCodec().treeToValue(node, PaddingMethod.class));
            case KmipTag.Standard.HASHING_ALGORITHM ->
                    builder.hashingAlgorithm(p.getCodec().treeToValue(node, HashingAlgorithm.class));
            case KmipTag.Standard.KEY_ROLE_TYPE ->
                    builder.keyRoleType(p.getCodec().treeToValue(node, KeyRoleType.class));
            case KmipTag.Standard.DIGITAL_SIGNATURE_ALGORITHM ->
                    builder.digitalSignatureAlgorithm(p.getCodec().treeToValue(node, DigitalSignatureAlgorithm.class));
            case KmipTag.Standard.CRYPTOGRAPHIC_ALGORITHM ->
                    builder.cryptographicAlgorithm(p.getCodec().treeToValue(node, CryptographicAlgorithm.class));
            case KmipTag.Standard.RANDOM_IV -> builder.randomIv(p.getCodec().treeToValue(node, RandomIv.class));
            case KmipTag.Standard.IV_LENGTH -> builder.ivLength(p.getCodec().treeToValue(node, IvLength.class));
            case KmipTag.Standard.TAG_LENGTH -> builder.tagLength(p.getCodec().treeToValue(node, TagLength.class));
            case KmipTag.Standard.FIXED_FIELD_LENGTH ->
                    builder.fixedFieldLength(p.getCodec().treeToValue(node, FixedFieldLength.class));
            case KmipTag.Standard.INVOCATION_FIELD_LENGTH ->
                    builder.invocationFieldLength(p.getCodec().treeToValue(node, InvocationFieldLength.class));
            case KmipTag.Standard.COUNTER_LENGTH ->
                    builder.counterLength(p.getCodec().treeToValue(node, CounterLength.class));
            case KmipTag.Standard.INITIAL_COUNTER_VALUE ->
                    builder.initialCounterValue(p.getCodec().treeToValue(node, InitialCounterValue.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}