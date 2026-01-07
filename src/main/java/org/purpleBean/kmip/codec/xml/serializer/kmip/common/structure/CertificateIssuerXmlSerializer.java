package org.purpleBean.kmip.codec.xml.serializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.structure.CertificateIssuer;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class CertificateIssuerXmlSerializer extends KmipDataTypeXmlSerializer<CertificateIssuer> {

    @Override
    public void serialize(CertificateIssuer certificateissuer, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!certificateissuer.isSupported()) {
            throw new UnsupportedEncodingException(String.format("%s not supported for KMIP spec %s", certificateissuer.getClass().getSimpleName(), spec));
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = certificateissuer.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(certificateissuer);

        // Serialize all fields
        List<KmipDataType> values = certificateissuer.getValues();
        for (KmipDataType kmipDataType : values) {
            if (kmipDataType != null && kmipDataType.getKmipTag() != null) {
                serializers.defaultSerializeField(kmipDataType.getKmipTag().getDescription(), kmipDataType, gen);
            }
        }

        xmlGen.writeEndObject();
    }
}