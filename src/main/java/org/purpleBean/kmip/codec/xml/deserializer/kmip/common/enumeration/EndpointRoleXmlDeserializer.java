package org.purpleBean.kmip.codec.xml.deserializer.kmip.common.enumeration;

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
import org.purpleBean.kmip.common.enumeration.EndpointRole;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for EndpointRole.
 */
public class EndpointRoleXmlDeserializer extends KmipDataTypeXmlDeserializer<EndpointRole> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.ENDPOINT_ROLE);

    @Override
    public EndpointRole deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(EndpointRole.class, "Expected XML element object for EndpointRole");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(EndpointRole.class, "Invalid Tag for EndpointRole");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(EndpointRole.class, "Missing or invalid '@type' attribute for EndpointRole");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(EndpointRole.class, "Missing or non-text '@value' attribute for EndpointRole");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        EndpointRole endpointrole = new EndpointRole(EndpointRole.fromName(spec, description));
        if (!endpointrole.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("EndpointRole '%s' not supported for spec %s", description, spec));
        }

        return endpointrole;
    }
}
