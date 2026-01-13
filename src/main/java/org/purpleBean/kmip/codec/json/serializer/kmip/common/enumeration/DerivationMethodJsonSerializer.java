package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.common.enumeration.DerivationMethod;

public class DerivationMethodJsonSerializer extends AbstractKmipDataTypeJsonSerializer<DerivationMethod, String> {

    public DerivationMethodJsonSerializer() {
        super(DerivationMethod::getDescription);
    }
}