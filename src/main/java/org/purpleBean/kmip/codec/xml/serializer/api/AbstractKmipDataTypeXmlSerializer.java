package org.purpleBean.kmip.codec.xml.serializer.api;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.api.*;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public abstract class AbstractKmipDataTypeXmlSerializer<T extends KmipDataType> extends KmipDataTypeXmlSerializer<T> {

    @Override
    public void serialize(T obj, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!obj.isSupported()) {
            throw new UnsupportedEncodingException(String.format("%s not supported for KMIP spec %s", obj.getClass().getSimpleName(), spec));
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        if (!obj.getKmipTag().getDescription().matches("^[0-9][xX].*")) {
            xmlGen.setNextName(new QName(obj.getKmipTag().getDescription()));
            xmlGen.writeStartObject();
        } else {
            xmlGen.setNextName(new QName("TTLV"));
            xmlGen.writeStartObject();
            xmlGen.setNextIsAttribute(true);
            xmlGen.writeStringField("tag", obj.getKmipTag().getDescription());
        }

        if (obj.getEncodingType() == EncodingType.STRUCTURE) {
            KmipDataType[] values = (KmipDataType[]) obj.getValue();
            if (values != null) {
                for (KmipDataType kmipDataType : values) {
                    if (kmipDataType != null && kmipDataType.getKmipTag() != null) {
                        serializers.defaultSerializeField(kmipDataType.getKmipTag().getDescription(), kmipDataType, gen);
                    }
                }
            }
        } else {
            Object value;
            if (obj.getEncodingType() == EncodingType.ENUMERATION) {
                value = ((KmipEnumeration.Value<?>) obj.getValue()).getDescription();
            } else {
                if (obj instanceof KmipMaskType mask) {
                    value = mask.getMaskString() != null ? mask.getMaskString() : obj.getValue();
                } else {
                    value = obj.getValue();
                }
            }
            xmlGen.setNextIsAttribute(true);
            xmlGen.writeStringField("type", obj.getEncodingType().getDescription());

            xmlGen.setNextIsAttribute(true);
            xmlGen.writeFieldName("value");
            serializers.defaultSerializeValue(value, gen);
        }
        xmlGen.writeEndObject();
    }
}