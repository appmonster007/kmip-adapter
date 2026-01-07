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
import org.purpleBean.kmip.common.structure.CertificateSubject;

import java.io.IOException;
import java.util.Map;

public class CertificateSubjectXmlDeserializer extends KmipDataTypeXmlDeserializer<CertificateSubject> {
    private final KmipTag kmipTag = CertificateSubject.kmipTag;
    private final EncodingType encodingType = CertificateSubject.encodingType;

    @Override
    public CertificateSubject deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(CertificateSubject.class, "Expected XML object for CertificateSubject");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(CertificateSubject.class, "Invalid Tag for CertificateSubject");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        CertificateSubject.CertificateSubjectBuilder builder = CertificateSubject.builder();

        // Process all fields in the XML
        for (Map.Entry<String, JsonNode> entry : node.properties()) {
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        CertificateSubject certificatesubject = builder.build();

        if (!certificatesubject.isSupported()) {
            ctxt.reportInputMismatch(CertificateSubject.class, "CertificateSubject not supported for spec " + spec);
            return null;
        }

        return certificatesubject;
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
            CertificateSubject.CertificateSubjectBuilder builder,
            KmipTag.Value nodeTag,
            JsonNode node,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.CERTIFICATE_SUBJECT_DISTINGUISHED_NAME ->
                    builder.certificateSubjectDistinguishedName(p.getCodec().treeToValue(node, CertificateSubjectDistinguishedName.class));
            case KmipTag.Standard.CERTIFICATE_SUBJECT_ALTERNATIVE_NAME ->
                    builder.certificateSubjectAlternativeName(p.getCodec().treeToValue(node, CertificateSubjectAlternativeName.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}