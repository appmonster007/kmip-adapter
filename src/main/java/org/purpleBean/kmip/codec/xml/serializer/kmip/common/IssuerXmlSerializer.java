package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.kmip.AbstractKmipDataTypeXmlSerializer;
import org.purpleBean.kmip.common.Issuer;

public class IssuerXmlSerializer extends AbstractKmipDataTypeXmlSerializer<Issuer, String> {

    public IssuerXmlSerializer() {
        super(Issuer::getValue);
    }
}