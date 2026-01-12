package org.purpleBean.kmip.codec.json.serializer.kmip.common;

import org.purpleBean.kmip.codec.json.serializer.AbstractKmipJsonSerializer;
import org.purpleBean.kmip.common.MACSignatureKeyInformation;

public class MACSignatureKeyInformationJsonSerializer extends AbstractKmipJsonSerializer<MACSignatureKeyInformation, String> {

    public MACSignatureKeyInformationJsonSerializer() {
        super(MACSignatureKeyInformation::getValue);
    }
}