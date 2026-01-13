package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.CertificateLength;

public class CertificateLengthXmlSerializer extends AbstractKmipDataTypeXmlSerializer<CertificateLength, Integer> {

    public CertificateLengthXmlSerializer() {
        super(CertificateLength::getValue);
    }
}