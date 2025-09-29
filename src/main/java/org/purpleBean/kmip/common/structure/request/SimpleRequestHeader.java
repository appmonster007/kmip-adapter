package org.purpleBean.kmip.common.structure.request;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.purpleBean.kmip.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data
@Builder
public class SimpleRequestHeader implements RequestHeaderStructure {
    public static final KmipTag kmipTag = new KmipTag(KmipTag.Standard.REQUEST_HEADER);
    public static final EncodingType encodingType = EncodingType.STRUCTURE;

    static {
        for (KmipSpec spec : KmipSpec.values()) {
            if (spec == KmipSpec.UnknownVersion || spec == KmipSpec.UnsupportedVersion) continue;
            KmipDataType.register(spec, kmipTag.getValue(), encodingType, SimpleRequestHeader.class);
        }
    }

    @NonNull
    private final ProtocolVersion protocolVersion;

    @Override
    public KmipTag getKmipTag() {
        return kmipTag;
    }

    @Override
    public EncodingType getEncodingType() {
        return encodingType;
    }

    @Override
    public List<KmipDataType> getValues() {
        return Stream.of(protocolVersion)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isSupported() {
        KmipSpec spec = KmipContext.getSpec();
        return true;
    }
}