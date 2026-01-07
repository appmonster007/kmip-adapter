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
import org.purpleBean.kmip.common.Issuer;
import org.purpleBean.kmip.common.SerialNumber;
import org.purpleBean.kmip.common.structure.CertificateIdentifier;

import java.io.IOException;
import java.util.Map;

public class CertificateIdentifierXmlDeserializer extends KmipDataTypeXmlDeserializer<CertificateIdentifier> {
    private final KmipTag kmipTag = CertificateIdentifier.kmipTag;
    private final EncodingType encodingType = CertificateIdentifier.encodingType;

    @Override
    public CertificateIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(CertificateIdentifier.class, "Expected XML object for CertificateIdentifier");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(CertificateIdentifier.class, "Invalid Tag for CertificateIdentifier");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        CertificateIdentifier.CertificateIdentifierBuilder builder = CertificateIdentifier.builder();

        // Process all fields in the XML
        for (Map.Entry<String, JsonNode> entry : node.properties()) {
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        CertificateIdentifier certificateidentifier = builder.build();

        if (!certificateidentifier.isSupported()) {
            ctxt.reportInputMismatch(CertificateIdentifier.class, "CertificateIdentifier not supported for spec " + spec);
            return null;
        }

        return certificateidentifier;
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
            CertificateIdentifier.CertificateIdentifierBuilder builder,
            KmipTag.Value nodeTag,
            JsonNode node,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ISSUER -> builder.issuer(p.getCodec().treeToValue(node, Issuer.class));
            case KmipTag.Standard.SERIAL_NUMBER ->
                    builder.serialNumber(p.getCodec().treeToValue(node, SerialNumber.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}