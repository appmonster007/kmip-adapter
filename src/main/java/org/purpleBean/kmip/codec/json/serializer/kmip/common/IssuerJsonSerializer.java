package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.Issuer;

public class IssuerJsonSerializer extends AbstractKmipJsonSerializer<Issuer, String> {

    public IssuerJsonSerializer() {
        super(Issuer::getValue);
    }
}