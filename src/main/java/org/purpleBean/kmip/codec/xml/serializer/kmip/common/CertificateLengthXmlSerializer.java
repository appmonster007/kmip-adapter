package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.CertificateLength;

public class CertificateLengthXmlSerializer extends AbstractKmipXmlSerializer<CertificateLength, Integer> {

    public CertificateLengthXmlSerializer() {
        super(CertificateLength::getValue);
    }
}