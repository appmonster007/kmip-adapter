package org.purpleBean.kmip.common.structure.request;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class SimpleRequestHeader implements RequestHeaderStructure {
    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.REQUEST_HEADER);
    public static final EncodingType encodingType = EncodingType.STRUCTURE;


    @Override
    public KmipTag getKmipTag() {
        return kmipTag;
    }

    @Override
    public EncodingType getEncodingType() {
        return encodingType;
    }

    @NonNull
    private final ProtocolVersion protocolVersion;

    @Override
    public List<KmipDataType> getValues() {
        List<KmipDataType> values = new ArrayList<>();
        values.add(protocolVersion);
        return values;
    }

    @Override
    public boolean isSupportedFor(@NonNull KmipSpec spec) {
        return true;
    }
}