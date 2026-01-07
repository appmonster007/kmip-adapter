package org.purpleBean.kmip.codec.json.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import org.purpleBean.kmip.EncodingType;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.json.deserializer.kmip.KmipDataTypeJsonDeserializer;
import org.purpleBean.kmip.common.Qlength;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.CryptographicDomainParameters;

import java.io.IOException;
import java.util.NoSuchElementException;

public class CryptographicDomainParametersJsonDeserializer extends KmipDataTypeJsonDeserializer<CryptographicDomainParameters> {
    private final KmipTag kmipTag = CryptographicDomainParameters.kmipTag;
    private final EncodingType encodingType = CryptographicDomainParameters.encodingType;

    @Override
    public CryptographicDomainParameters deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode node = p.readValueAsTree();
        if (node == null) {
            ctxt.reportInputMismatch(CryptographicDomainParameters.class, "JSON node cannot be null for CryptographicDomainParameters deserialization");
            return null;
        }
        // Validation: Extract and validate KMIP tag
        KmipTag tag;
        try {
            tag = p.getCodec().treeToValue(node, KmipTag.class);
            if (tag == null) {
                ctxt.reportInputMismatch(CryptographicDomainParameters.class, "Invalid KMIP tag for CryptographicDomainParameters");
                return null;
            }
        } catch (Exception e) {
            ctxt.reportInputMismatch(CryptographicDomainParameters.class, String.format("Failed to parse KMIP tag for CryptographicDomainParameters: %s", e.getMessage()));
            return null;
        }

        if (!node.isObject() || tag.getValue().getValue() != kmipTag.getValue().getValue()) {
            ctxt.reportInputMismatch(CryptographicDomainParameters.class,
                    String.format("Expected object with %s tag for CryptographicDomainParameters, got tag: %s", kmipTag.getValue().getValue(), tag.getValue().getValue()));
            return null;
        }
        // Validation: Extract and validate type field
        JsonNode typeNode = node.get("type");
        if (typeNode == null
                || !typeNode.isTextual()
                || EncodingType.fromName(typeNode.asText()).isEmpty()
                || EncodingType.fromName(typeNode.asText()).get() != encodingType
        ) {
            ctxt.reportInputMismatch(CryptographicDomainParameters.class, String.format("Missing or non-text 'type' field for CryptographicDomainParameters"));
            return null;
        }

        // Validation: Extract and validate fields
        JsonNode valuesNode = node.get("value");
        if (valuesNode == null || !valuesNode.isArray() || valuesNode.isEmpty()) {
            ctxt.reportInputMismatch(CryptographicDomainParameters.class, "CryptographicDomainParameters 'value' must be a non-empty array");
            return null;
        }

        CryptographicDomainParameters.CryptographicDomainParametersBuilder builder = CryptographicDomainParameters.builder();

        for (JsonNode valueNode : valuesNode) {
            if (valueNode == null || !valueNode.has("tag")) {
                continue;
            }
            try {
                KmipTag.Value nodeTag = p.getCodec().treeToValue(valueNode, KmipTag.class).getValue();
                setValue(builder, nodeTag, valueNode, p, ctxt);
            } catch (Exception e) {
                ctxt.reportInputMismatch(CryptographicDomainParameters.class, String.format("Failed to process field in CryptographicDomainParameters: %s", e.getMessage()));
                return null;
            }
        }

        CryptographicDomainParameters cryptographicdomainparameters = builder.build();

        // Validate KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!cryptographicdomainparameters.isSupported()) {
            throw new NoSuchElementException(String.format("CryptographicDomainParameters is not supported for KMIP spec %s", spec));
        }

        return cryptographicdomainparameters;
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
            CryptographicDomainParameters.CryptographicDomainParametersBuilder builder,
            KmipTag.Value nodeTag,
            JsonNode node,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.QLENGTH -> builder.qlength(p.getCodec().treeToValue(node, Qlength.class));
            case KmipTag.Standard.RECOMMENDED_CURVE ->
                    builder.recommendedCurve(p.getCodec().treeToValue(node, RecommendedCurve.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}