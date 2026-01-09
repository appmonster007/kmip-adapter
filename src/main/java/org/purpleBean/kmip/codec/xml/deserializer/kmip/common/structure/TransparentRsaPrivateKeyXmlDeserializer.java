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
import org.purpleBean.kmip.common.structure.TransparentRsaPrivateKey;

import java.io.IOException;
import java.util.Map;

public class TransparentRsaPrivateKeyXmlDeserializer extends KmipDataTypeXmlDeserializer<TransparentRsaPrivateKey> {
    private final KmipTag kmipTag = TransparentRsaPrivateKey.kmipTag;
    private final EncodingType encodingType = TransparentRsaPrivateKey.encodingType;

    @Override
    public TransparentRsaPrivateKey deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(TransparentRsaPrivateKey.class, "Expected XML object for TransparentRsaPrivateKey");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(TransparentRsaPrivateKey.class, "Invalid Tag for TransparentRsaPrivateKey");
            return null;
        }

        KmipSpec spec = KmipContext.getSpec();
        TransparentRsaPrivateKey.TransparentRsaPrivateKeyBuilder builder = TransparentRsaPrivateKey.builder();

        // Process all fields in the XML
        var fields = node.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, entry.getKey());
            setValue(builder, nodeTag, entry.getValue(), p, ctxt);
        }

        TransparentRsaPrivateKey transparentRsaPrivateKey = builder.build();

        if (!transparentRsaPrivateKey.isSupported()) {
            ctxt.reportInputMismatch(TransparentRsaPrivateKey.class, "TransparentRsaPrivateKey not supported for spec " + spec);
            return null;
        }

        return transparentRsaPrivateKey;
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
    private void setValue(TransparentRsaPrivateKey.TransparentRsaPrivateKeyBuilder builder, KmipTag.Value nodeTag, JsonNode node, JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (nodeTag) {
            case KmipTag.Standard.MODULUS -> builder.modulus(p.getCodec().treeToValue(node, Modulus.class));
            case KmipTag.Standard.PRIVATE_EXPONENT ->
                    builder.privateExponent(p.getCodec().treeToValue(node, PrivateExponent.class));
            case KmipTag.Standard.PUBLIC_EXPONENT ->
                    builder.publicExponent(p.getCodec().treeToValue(node, PublicExponent.class));
            case KmipTag.Standard.P -> builder.p(p.getCodec().treeToValue(node, P.class));
            case KmipTag.Standard.Q -> builder.q(p.getCodec().treeToValue(node, Q.class));
            case KmipTag.Standard.PRIME_EXPONENT_P ->
                    builder.primeExponentP(p.getCodec().treeToValue(node, PrimeExponentP.class));
            case KmipTag.Standard.PRIME_EXPONENT_Q ->
                    builder.primeExponentQ(p.getCodec().treeToValue(node, PrimeExponentQ.class));
            case KmipTag.Standard.CRT_COEFFICIENT ->
                    builder.crtCoefficient(p.getCodec().treeToValue(node, CRTCoefficient.class));
            default -> throw new IllegalArgumentException("Unsupported tag: " + nodeTag);
        }
    }
}