package org.purpleBean.kmip.codec.json.serializer.kmip.common.enumeration;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.enumeration.DerivationMethod;

public class DerivationMethodJsonSerializer extends AbstractKmipJsonSerializer<DerivationMethod, String> {

    public DerivationMethodJsonSerializer() {
        super(DerivationMethod::getDescription);
    }
}