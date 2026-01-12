package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.common.Issuer;
import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;

public class IssuerXmlSerializer extends AbstractKmipXmlSerializer<Issuer, String> {

    public IssuerXmlSerializer() {
        super(Issuer::getValue);
    }
}