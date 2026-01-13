package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.Issuer;

public class IssuerJsonSerializer extends AbstractKmipDataTypeJsonSerializer<Issuer, String> {

    public IssuerJsonSerializer() {
        super(Issuer::getValue);
    }
}