package org.purpleBean.kmip.codec.xml.serializer.model.core.type;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.model.core.type.CertificateLength;

public class CertificateLengthXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CertificateLength, Integer> {

    public CertificateLengthXmlSerializer() {
        super(CertificateLength::getValue);
    }
}