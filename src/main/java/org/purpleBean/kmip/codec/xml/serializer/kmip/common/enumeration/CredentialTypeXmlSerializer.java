package org.purpleBean.kmip.codec.xml.serializer.kmip.common.enumeration;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.purpleBean.kmip.KmipContext;
import org.purpleBean.kmip.KmipSpec;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.enumeration.CredentialType;

import java.io.IOException;

public class CredentialTypeXmlSerializer extends KmipDataTypeXmlSerializer<CredentialType> {
    @Override
    public void serialize(CredentialType value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if (value == null) return;
        KmipSpec spec = KmipContext.getSpec();
        if (!value.isSupportedFor(spec)) {
            throw new IOException(
                String.format("%s '%s' is not supported for KMIP spec %s",
                    value.getKmipTag().getDescription(), value.getDescription(), spec));
        }
        gen.writeString(value.getDescription());
    }
}
