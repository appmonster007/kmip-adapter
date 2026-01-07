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
import org.purpleBean.kmip.common.Qlength;
import org.purpleBean.kmip.common.enumeration.RecommendedCurve;
import org.purpleBean.kmip.common.structure.CryptographicDomainParameters;

import java.io.IOException;
import java.util.Map;

public class CryptographicDomainParametersXmlDeserializer extends KmipDataTypeXmlDeserializer<CryptographicDomainParameters> {
    private final KmipTag kmipTag = CryptographicDomainParameters.kmipTag;
    private final EncodingType encodingType = CryptographicDomainParameters.encodingType;

    @Override
    public CryptographicDomainParameters deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(CryptographicDomainParameters.class, "Expected XML object for CryptographicDomainParameters");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(CryptographicDomainParameters.class, "Invalid Tag for CryptographicDomainParameters");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        CryptographicDomainParameters.CryptographicDomainParametersBuilder builder = CryptographicDomainParameters.builder();

        // Process all fields in the XML
        for (Map.Entry<String, JsonNode> entry : node.properties()) {
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        CryptographicDomainParameters cryptographicdomainparameters = builder.build();

        if (!cryptographicdomainparameters.isSupported()) {
            ctxt.reportInputMismatch(CryptographicDomainParameters.class, "CryptographicDomainParameters not supported for spec " + spec);
            return null;
        }

        return cryptographicdomainparameters;
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