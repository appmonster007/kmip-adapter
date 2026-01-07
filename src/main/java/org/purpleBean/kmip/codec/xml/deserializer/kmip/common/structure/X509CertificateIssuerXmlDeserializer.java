package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.structure.X509CertificateIssuer;

import java.io.IOException;
import java.util.Map;

public class X509CertificateIssuerXmlDeserializer extends KmipDataTypeXmlDeserializer<X509CertificateIssuer> {
    private final KmipTag kmipTag = X509CertificateIssuer.kmipTag;
    private final EncodingType encodingType = X509CertificateIssuer.encodingType;

    @Override
    public X509CertificateIssuer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(X509CertificateIssuer.class, "Expected XML object for X509CertificateIssuer");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(X509CertificateIssuer.class, "Invalid Tag for X509CertificateIssuer");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        X509CertificateIssuer.X509CertificateIssuerBuilder builder = X509CertificateIssuer.builder();

        // Process all fields in the XML
        for (Map.Entry<String, JsonNode> entry : node.properties()) {
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        X509CertificateIssuer x509certificateissuer = builder.build();

        if (!x509certificateissuer.isSupported()) {
            ctxt.reportInputMismatch(X509CertificateIssuer.class, "X509CertificateIssuer not supported for spec " + spec);
            return null;
        }

        return x509certificateissuer;
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
            X509CertificateIssuer.X509CertificateIssuerBuilder builder,
            KmipTag.Value nodeTag,
            JsonNode node,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ISSUER_DISTINGUISHED_NAME ->
                    builder.issuerDistinguishedName(p.getCodec().treeToValue(node, IssuerDistinguishedName.class));
            case KmipTag.Standard.ISSUER_ALTERNATIVE_NAME ->
                    builder.issuerAlternativeName(p.getCodec().treeToValue(node, IssuerAlternativeName.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}