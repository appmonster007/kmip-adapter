package org.purpleBean.kmip.codec.ttlv.serializer.kmip.common;

import org.purpleBean.kmip.codec.ttlv.serializer.AbstractKmipTtlvSerializer;
import org.purpleBean.kmip.common.Issuer;

public class IssuerTtlvSerializer extends AbstractKmipTtlvSerializer<Issuer, String> {

    public IssuerTtlvSerializer() {
        super(Issuer::getValue);
    }
}