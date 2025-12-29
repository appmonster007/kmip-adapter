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
import org.purpleBean.kmip.common.CertificateSerialNumber;
import org.purpleBean.kmip.common.IssuerDistinguishedName;
import org.purpleBean.kmip.common.structure.X509CertificateIdentifier;

import java.io.IOException;
import java.util.Map;

public class X509CertificateIdentifierXmlDeserializer extends KmipDataTypeXmlDeserializer<X509CertificateIdentifier> {
    private final KmipTag kmipTag = X509CertificateIdentifier.kmipTag;
    private final EncodingType encodingType = X509CertificateIdentifier.encodingType;

    @Override
    public X509CertificateIdentifier deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(X509CertificateIdentifier.class, "Expected XML object for X509CertificateIdentifier");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(X509CertificateIdentifier.class, "Invalid Tag for X509CertificateIdentifier");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        X509CertificateIdentifier.X509CertificateIdentifierBuilder builder = X509CertificateIdentifier.builder();

        // Process all fields in the XML
        var fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        X509CertificateIdentifier x509CertificateIdentifier = builder.build();

        if (!x509CertificateIdentifier.isSupported()) {
            ctxt.reportInputMismatch(X509CertificateIdentifier.class, "X509CertificateIdentifier not supported for spec " + spec);
            return null;
        }

        return x509CertificateIdentifier;
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
            X509CertificateIdentifier.X509CertificateIdentifierBuilder builder,
            KmipTag.Value nodeTag,
            JsonNode node,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.ISSUER_DISTINGUISHED_NAME ->
                    builder.issuerDistinguishedName(p.getCodec().treeToValue(node, IssuerDistinguishedName.class));
            case KmipTag.Standard.CERTIFICATE_SERIAL_NUMBER ->
                    builder.certificateSerialNumber(p.getCodec().treeToValue(node, CertificateSerialNumber.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}
