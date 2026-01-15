package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.Issuer;

public class IssuerJsonSerializer extends AbstractKmipDataTypeJsonSerializer<Issuer, String> {

    public IssuerJsonSerializer() {
        super(Issuer::getValue);
    }
}