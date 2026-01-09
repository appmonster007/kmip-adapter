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
import org.purpleBean.kmip.common.structure.TransparentDhPrivateKey;

import java.io.IOException;
import java.util.Map;

public class TransparentDhPrivateKeyXmlDeserializer extends KmipDataTypeXmlDeserializer<TransparentDhPrivateKey> {
    private final KmipTag kmipTag = TransparentDhPrivateKey.kmipTag;
    private final EncodingType encodingType = TransparentDhPrivateKey.encodingType;

    @Override
    public TransparentDhPrivateKey deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(TransparentDhPrivateKey.class, "Expected XML object for TransparentDhPrivateKey");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(TransparentDhPrivateKey.class, "Invalid Tag for TransparentDhPrivateKey");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        TransparentDhPrivateKey.TransparentDhPrivateKeyBuilder builder = TransparentDhPrivateKey.builder();

        // Process all fields in the XML
        var fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        TransparentDhPrivateKey transparentDhPrivateKey = builder.build();

        if (!transparentDhPrivateKey.isSupported()) {
            ctxt.reportInputMismatch(TransparentDhPrivateKey.class, "TransparentDhPrivateKey not supported for spec " + spec);
            return null;
        }

        return transparentDhPrivateKey;
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
    private void setValue(TransparentDhPrivateKey.TransparentDhPrivateKeyBuilder builder, KmipTag.Value nodeTag, JsonNode node, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.P -> builder.p(p.getCodec().treeToValue(node, P.class));
            case KmipTag.Standard.Q -> builder.q(p.getCodec().treeToValue(node, Q.class));
            case KmipTag.Standard.G -> builder.g(p.getCodec().treeToValue(node, G.class));
            case KmipTag.Standard.J -> builder.j(p.getCodec().treeToValue(node, J.class));
            case KmipTag.Standard.X -> builder.x(p.getCodec().treeToValue(node, X.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}