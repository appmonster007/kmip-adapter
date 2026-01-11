package org.purpleBean.kmip.codec.json.deserializer.kmip.common;

import org.purpleBean.kmip.codec.json.deserializer.AbstractKmipJsonDeserializer;
import org.purpleBean.kmip.common.Issuer;

public class IssuerJsonDeserializer extends AbstractKmipJsonDeserializer<Issuer, String> {

    public IssuerJsonDeserializer() {
        super(Issuer.kmipTag, Issuer.encodingType, String.class, value -> Issuer.builder().value(value).build());
    }
}