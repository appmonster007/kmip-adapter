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
import org.purpleBean.kmip.common.G;
import org.purpleBean.kmip.common.P;
import org.purpleBean.kmip.common.Q;
import org.purpleBean.kmip.common.Y;
import org.purpleBean.kmip.common.structure.TransparentDsaPublicKey;

import java.io.IOException;
import java.util.Map;

public class TransparentDsaPublicKeyXmlDeserializer extends KmipDataTypeXmlDeserializer<TransparentDsaPublicKey> {
    private final KmipTag kmipTag = TransparentDsaPublicKey.kmipTag;
    private final EncodingType encodingType = TransparentDsaPublicKey.encodingType;

    @Override
    public TransparentDsaPublicKey deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(TransparentDsaPublicKey.class, "Expected XML object for TransparentDsaPublicKey");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(TransparentDsaPublicKey.class, "Invalid Tag for TransparentDsaPublicKey");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        TransparentDsaPublicKey.TransparentDsaPublicKeyBuilder builder = TransparentDsaPublicKey.builder();

        // Process all fields in the XML
        var fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        TransparentDsaPublicKey transparentDsaPublicKey = builder.build();

        if (!transparentDsaPublicKey.isSupported()) {
            ctxt.reportInputMismatch(TransparentDsaPublicKey.class, "TransparentDsaPublicKey not supported for spec " + spec);
            return null;
        }

        return transparentDsaPublicKey;
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
    private void setValue(TransparentDsaPublicKey.TransparentDsaPublicKeyBuilder builder, KmipTag.Value nodeTag, JsonNode node, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.P -> builder.p(p.getCodec().treeToValue(node, P.class));
            case KmipTag.Standard.Q -> builder.q(p.getCodec().treeToValue(node, Q.class));
            case KmipTag.Standard.G -> builder.g(p.getCodec().treeToValue(node, G.class));
            case KmipTag.Standard.Y -> builder.y(p.getCodec().treeToValue(node, Y.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}