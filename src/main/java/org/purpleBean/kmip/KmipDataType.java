package org.purpleBean.kmip;

import lombok.NonNull;

public interface KmipDataType {
    KmipTag getKmipTag();

    EncodingType getEncodingType();

    boolean isSupportedFor(@NonNull KmipSpec spec);
}
