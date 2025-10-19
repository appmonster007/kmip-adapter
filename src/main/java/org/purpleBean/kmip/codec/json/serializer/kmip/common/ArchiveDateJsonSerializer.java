package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.json.serializer.kmip.KmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.ArchiveDate;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class ArchiveDateJsonSerializer extends KmipDataTypeJsonSerializer<ArchiveDate> {

    @Override
    public void serialize(ArchiveDate archiveDate, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        // Validation: Null check
        if (archiveDate == null) {
            return;
        }

        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!archiveDate.isSupported()) {
            throw new UnsupportedEncodingException(
                    String.format("%s is not supported for KMIP spec %s", archiveDate.getKmipTag().getDescription(), spec)
            );
        }

        gen.writeStartObject();
        gen.writeObject(archiveDate.getKmipTag());
        gen.writeStringField("type", archiveDate.getEncodingType().getDescription());
        gen.writeObjectField("value", archiveDate.getValue());
        gen.writeEndObject();
    }
}
