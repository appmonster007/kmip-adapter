package org.purpleBean.kmip.codec.xml.serializer.kmip.common.structure;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import org.purpleBean.kmip.*;
import org.purpleBean.kmip.common.*;
import org.purpleBean.kmip.common.structure.*;
import org.purpleBean.kmip.common.enumeration.*;
import org.purpleBean.kmip.codec.xml.serializer.kmip.KmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.structure.X509CertificateSubject;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class X509CertificateSubjectXmlSerializer extends KmipDataTypeXmlSerializer<X509CertificateSubject> {

    @Override
    public void serialize(X509CertificateSubject x509certificatesubject, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // Validation: KMIP spec compatibility
        KmipSpec spec = KmipContext.getSpec();
        if (!x509certificatesubject.isSupported()) {
            throw new UnsupportedEncodingException(String.format("%s not supported for KMIP spec %s", x509certificatesubject.getClass().getSimpleName(), spec));
        }

        if (!(gen instanceof ToXmlGenerator xmlGen)) {
            throw new IllegalStateException("Expected ToXmlGenerator");
        }

        // Start element with name from kmipTag
        String elementName = x509certificatesubject.getKmipTag().getDescription();
        xmlGen.setNextName(QName.valueOf(elementName));
        xmlGen.writeStartObject(x509certificatesubject);

        // Serialize all fields
        List<KmipDataType> values = x509certificatesubject.getValues();
        for (KmipDataType kmipDataType : values) {
            if (kmipDataType != null && kmipDataType.getKmipTag() != null) {
                serializers.defaultSerializeField(kmipDataType.getKmipTag().getDescription(), kmipDataType, gen);
            }
        }

        xmlGen.writeEndObject();
    }
}