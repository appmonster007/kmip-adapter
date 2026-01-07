package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.common.structure.CryptographicParameters;

import java.io.IOException;
import java.util.NoSuchElementException;

public class CryptographicParametersJsonDeserializer extends KmipDataTypeJsonDeserializer<CryptographicParameters> {
    private final KmipTag kmipTag = CryptographicParameters.kmipTag;
    private final EncodingType encodingType = CryptographicParameters.encodingType;

    @Override
    public CryptographicParameters deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(CryptographicParameters.class, "JSON node cannot be null for CryptographicParameters deserialization");
            return null;
        }
        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(CryptographicParameters.class, "Invalid KMIP tag for CryptographicParameters");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(CryptographicParameters.class, String.format("Failed to parse KMIP tag for CryptographicParameters: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(CryptographicParameters.class,
                    String.format("Expected object with %s tag for CryptographicParameters, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }
        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(CryptographicParameters.class, String.format("Missing or non-text 'type' field for CryptographicParameters"));
            return null;
        }

        // Validation: Extract and validate fields
        JsonNode valuesNode = node.get("value");
        if (valuesNode == null || !valuesNode.isArray() || valuesNode.isEmpty()) {
            ctxt.reportInputMismatch(CryptographicParameters.class, "CryptographicParameters 'value' must be a non-empty array");
            return null;
        }

        CryptographicParameters.CryptographicParametersBuilder builder = CryptographicParameters.builder();

        for (JsonNode valueNode : valuesNode) {
            if (valueNode == null || !valueNode.has("tag")) {
                continue;
            }
            try {
                KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
                setValue(builder, nodeTag, valueNode, p, ctxt);
            } catch (Exception e) {
                ctxt.reportInputMismatch(CryptographicParameters.class, String.format("Failed to process field in CryptographicParameters: %s", e.getMessage()));
                return null;
            }
        }

        CryptographicParameters cryptographicparameters = builder.build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!cryptographicparameters.isSupported()) {
            throw new NoSuchElementException(String.format("CryptographicParameters is not supported for KMIP spec %s", spec));
        }

        return cryptographicparameters;
    }

    /**
     * Sets the appropriate field in the builder based on the tag and value.
     *
     * @param builder the builder to set the field on
     * @param nodeTag the tag identifying the field to set
     * @param node    the JSON node containing the field value
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