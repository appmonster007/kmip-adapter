package org.purpleBean.kmip.codec.xml.serializer.kmip.common;

import org.purpleBean.kmip.codec.xml.serializer.AbstractKmipXmlSerializer;
import org.purpleBean.kmip.common.Issuer;

public class IssuerXmlSerializer extends AbstractKmipXmlSerializer<Issuer, String> {

    public IssuerXmlSerializer() {
        super(Issuer::getValue);
    }
}