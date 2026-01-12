package org.purpleBean.kmip.codec.xml.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.dataformat.xml.deser.FromXmlParser;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipDataType;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.KmipTag;
import org.purpleBean.kmip.codec.xml.deserializer.kmip.KmipDataTypeXmlDeserializer;

import java.io.IOException;

public abstract class AbstractKmipStructureXmlDeserializer<T extends KmipDataType, B> extends KmipDataTypeXmlDeserializer<T> {

    private final KmipTag kmipTag;

    protected AbstractKmipStructureXmlDeserializer(KmipTag kmipTag) {
        this.kmipTag = kmipTag;
    }

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.currentToken() == null) {
            p.nextToken();
        }

        String currentName;
        if (p instanceof FromXmlParser xmlParser) {
            currentName = xmlParser.getStaxReader().getLocalName();
        } else {
            currentName = (String) ctxt.getAttribute("tag");
        }

        if (!kmipTag.getDescription().equalsIgnoreCase(currentName)) {
            ctxt.reportInputMismatch(handledType(), "Invalid Tag for " + handledType().getSimpleName());
            return null;
        }

        if (p.currentToken() != JsonToken.START_OBJECT) {
            p.nextToken();
        }

        KmipSpec spec = KmipContext.getSpec();
        B builder = createBuilder();

        while (p.nextToken() != null && p.currentToken() != JsonToken.END_OBJECT) {
            String fieldName = p.currentName();
            KmipTag.Value nodeTag = KmipTag.fromName(spec, fieldName);
            ctxt.setAttribute("tag", p.currentName());
            if (p.currentToken() == JsonToken.START_OBJECT) {
                p.nextToken();
                setValue(builder, nodeTag, p, ctxt);
            } else if (p.currentToken() == JsonToken.FIELD_NAME) {
                setValue(builder, nodeTag, p, ctxt);
            } else {
                ctxt.reportInputMismatch(handledType(), "Unexpected token: " + p.currentToken());
            }
        }

        T result = build(builder);

        if (!result.isSupported()) {
            ctxt.reportInputMismatch(handledType(), String.format("%s not supported for spec %s", handledType().getSimpleName(), spec));
            return null;
        }

        return result;
    }

    protected abstract B createBuilder();

    protected abstract void setValue(B builder, KmipTag.Value nodeTag, JsonParser p, DeserializationContext ctxt) throws IOException;

    protected abstract T build(B builder);
}