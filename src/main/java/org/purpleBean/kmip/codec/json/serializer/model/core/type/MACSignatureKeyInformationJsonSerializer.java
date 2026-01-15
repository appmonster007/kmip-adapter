package org.purpleBean.kmip.codec.json.serializer.model.core.type;

import org.purpleBean.kmip.codec.json.serializer.kmip.AbstractKmipDataTypeJsonSerializer;
import org.purpleBean.kmip.model.core.type.MACSignatureKeyInformation;

public class MACSignatureKeyInformationJsonSerializer extends AbstractKmipDataTypeJsonSerializer<MACSignatureKeyInformation, String> {

    public MACSignatureKeyInformationJsonSerializer() {
        super(MACSignatureKeyInformation::getValue);
    }
}