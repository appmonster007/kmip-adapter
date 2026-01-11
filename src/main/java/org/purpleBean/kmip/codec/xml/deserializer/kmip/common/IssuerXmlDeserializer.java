package org.purpleBean.kmip.codec.xml.deserializer.kmip.common;

import org.purpleBean.kmip.codec.xml.deserializer.AbstractKmipXmlDeserializer;
import org.purpleBean.kmip.common.Issuer;

public class IssuerXmlDeserializer extends AbstractKmipXmlDeserializer<Issuer, String> {

    public IssuerXmlDeserializer() {
        super(Issuer.kmipTag, Issuer.encodingType, String.class, value -> Issuer.builder().value(value).build());
    }
}