package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

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
import org.purpleBean.kmip.common.ArchiveDate;

import java.io.IOException;
import java.time.OffsetDateTime;

public class ArchiveDateXmlDeserializer extends KmipDataTypeXmlDeserializer<ArchiveDate> {
    private final KmipTag kmipTag = ArchiveDate.kmipTag;
    private final EncodingType encodingType = ArchiveDate.encodingType;

    @Override
    public ArchiveDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        JsonNode node = codec.readTree(p);

        if (!node.isObject()) {
            ctxt.reportInputMismatch(ArchiveDate.class, "Expected XML object for ArchiveDate");
            return null;
        }

        if (p instanceof FromXmlParser xmlParser
                && !kmipTag.getDescription().equalsIgnoreCase(xmlParser.getStaxReader().getLocalName())) {
            ctxt.reportInputMismatch(ArchiveDate.class, "Invalid Tag for ArchiveDate");
            return null;
        }

        JsonNode typeNode = node.get("type");
        if (typeNode == null || !typeNode.isTextual() ||
                !encodingType.getDescription().equals(typeNode.asText())) {
            ctxt.reportInputMismatch(ArchiveDate.class, "Missing or invalid '@type' attribute for ArchiveDate");
            return null;
        }

        JsonNode valueNode = node.get("value");
        if (valueNode == null || !valueNode.isTextual()) {
            ctxt.reportInputMismatch(ArchiveDate.class,
                    "Missing or non-text 'value' for ArchiveDate");
            return null;
        }

        OffsetDateTime dateTime = OffsetDateTime.parse(valueNode.asText());
        ArchiveDate archiveDate = ArchiveDate.builder().value(dateTime).build();

        KmipSpec spec = KmipContext.getSpec();

        if (!archiveDate.isSupported()) {
            ctxt.reportInputMismatch(ArchiveDate.class, "ArchiveDate not supported for spec " + spec);
            return null;
        }

        return archiveDate;
    }
}
