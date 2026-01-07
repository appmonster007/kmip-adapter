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
import org.purpleBean.kmip.common.structure.X509CertificateSubject;

import java.io.IOException;
import java.util.Map;

public class X509CertificateSubjectXmlDeserializer extends KmipDataTypeXmlDeserializer<X509CertificateSubject> {
    private final KmipTag kmipTag = X509CertificateSubject.kmipTag;
    private final EncodingType encodingType = X509CertificateSubject.encodingType;

    @Override
    public X509CertificateSubject deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(X509CertificateSubject.class, "Expected XML object for X509CertificateSubject");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(X509CertificateSubject.class, "Invalid Tag for X509CertificateSubject");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        X509CertificateSubject.X509CertificateSubjectBuilder builder = X509CertificateSubject.builder();

        // Process all fields in the XML
        for (Map.Entry<String, JsonNode> entry : node.properties()) {
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        X509CertificateSubject x509certificatesubject = builder.build();

        if (!x509certificatesubject.isSupported()) {
            ctxt.reportInputMismatch(X509CertificateSubject.class, "X509CertificateSubject not supported for spec " + spec);
            return null;
        }

        return x509certificatesubject;
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
            X509CertificateSubject.X509CertificateSubjectBuilder builder,
            KmipTag.Value nodeTag,
            JsonNode node,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.SUBJECT_DISTINGUISHED_NAME ->
                    builder.subjectDistinguishedName(p.getCodec().treeToValue(node, SubjectDistinguishedName.class));
            case KmipTag.Standard.SUBJECT_ALTERNATIVE_NAME ->
                    builder.subjectAlternativeName(p.getCodec().treeToValue(node, SubjectAlternativeName.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}