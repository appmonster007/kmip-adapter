package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;
import org.purpleBean.kmip.common.RevocationMessage;
import org.purpleBean.kmip.common.enumeration.RevocationReasonCode;
import org.purpleBean.kmip.common.structure.RevocationReason;

import java.io.IOException;
import java.util.Map;

public class RevocationReasonXmlDeserializer extends KmipDataTypeXmlDeserializer<RevocationReason> {
    private final KmipTag kmipTag = RevocationReason.kmipTag;
    private final EncodingType encodingType = RevocationReason.encodingType;

    @Override
    public RevocationReason deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(RevocationReason.class, "Expected XML object for RevocationReason");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(RevocationReason.class, "Invalid Tag for RevocationReason");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        RevocationReason.RevocationReasonBuilder builder = RevocationReason.builder();

        // Process all fields in the XML
        for (Map.Entry<String, JsonNode> entry : node.properties()) {
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        RevocationReason revocationReason = builder.build();

        if (!revocationReason.isSupported()) {
            ctxt.reportInputMismatch(RevocationReason.class, "RevocationReason not supported for spec " + spec);
            return null;
        }

        return revocationReason;
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
            RevocationReason.RevocationReasonBuilder builder,
            KmipTag.Value nodeTag,
            JsonNode node,
            JsonParser p,
            DeserializationContext ctxt
    ) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.REVOCATION_REASON_CODE -> builder.revocationReasonCode(p.getCodec().treeToValue(node, RevocationReasonCode.class));
            case KmipTag.Standard.REVOCATION_MESSAGE -> builder.revocationMessage(p.getCodec().treeToValue(node, RevocationMessage.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}
