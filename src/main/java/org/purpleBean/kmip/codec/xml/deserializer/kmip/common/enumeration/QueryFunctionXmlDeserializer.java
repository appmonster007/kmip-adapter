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
import org.purpleBean.kmip.common.enumeration.QueryFunction;

import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * XML deserializer for QueryFunction.
 */
public class QueryFunctionXmlDeserializer extends KmipDataTypeXmlDeserializer<QueryFunction> {
    private final EncodingType encodingType = EncodingType.ENUMERATION;
    private final KmipTag kmipTag = new KmipTag(KmipTag.Standard.QUERY_FUNCTION);

    @Override
    public QueryFunction deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(QueryFunction.class, "Expected XML element object for QueryFunction");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(QueryFunction.class, "Invalid Tag for QueryFunction");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(QueryFunction.class, "Missing or invalid '@type' attribute for QueryFunction");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(QueryFunction.class, "Missing or non-text '@value' attribute for QueryFunction");
            return null;
        }

        String description = valueNode.asText();
        KmipSpec spec = KmipContext.getSpec();

        QueryFunction queryfunction = new QueryFunction(QueryFunction.fromName(spec, description));
        if (!queryfunction.isSupportedFor(spec)) {
            throw new NoSuchElementException(
                String.format("QueryFunction '%s' not supported for spec %s", description, spec));
        }

        return queryfunction;
    }
}
